package ilapin.filemanager.domain

import ilapin.filemanager.utils.Optional
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class FileManager(
    private val fileSystemRepository: FileSystemRepository,
    private val currentDirectoryPathStorage: CurrentDirectoryPathStorage,
    private val homeDirectoryRepository: HomeDirectoryRepository,
    private val fileManagerStateSaver: FileManagerStateSaver,
    private val separator: String
) {
    private var _currentState = State(null, emptyList(), false, Optional.empty())

    private val stateSubject = BehaviorSubject.createDefault(
        State(null, emptyList(), false, Optional.empty())
    )
    private val errorSubject = PublishSubject.create<Throwable>()

    private var stateSubscription: Disposable? = null
    private var operationSubscription: Disposable? = null

    private var currentState: State
        get() = _currentState
        set(value) {
            _currentState = value
            stateSubject.onNext(value)
        }

    val state: Observable<State> = stateSubject
    val error: Observable<Throwable> = errorSubject

    fun goHome() {
        if (currentState.isBusy) {
            return
        }

    }

    fun goUp() {
        if (currentState.isBusy) {
            return
        }

        //if (currentState.)
    }

    fun goToPath(path: String) {
        if (currentState.isBusy) {
            return
        }

        performOperation(changingDirState(path))
    }

    fun goToFsItem(fsItem: FsItem) {
        if (currentState.isBusy) {
            return
        }

        performOperation(changingDirState(fsItem.parentPath + separator + fsItem.name))
    }

    fun refresh() {
        if (currentState.isBusy) {
            return
        }

        performOperation(refreshingState(currentState))
    }

    fun cancel() {
        operationSubscription?.dispose()
    }

    fun onDestroy() {
        cancel()
        stateSubscription?.dispose()
    }

    private fun performOperation(transitionState: State) {
        currentState = transitionState

        operationSubscription = fileSystemRepository
            .getFsItems(transitionState.currentPath ?: throw IllegalArgumentException("No path"))
            .subscribe({ fsItems ->
                currentState = idleState(transitionState, fsItems)
            }, { t ->
                currentState = idleState(transitionState, t)
            })
    }

    private fun changingDirState(newPath: String) = State(
        newPath,
        emptyList(),
        true,
        Optional.empty()
    )

    private fun idleState(oldState: State, fsItems: List<FsItem>) = State(
        oldState.currentPath,
        fsItems,
        false,
        Optional.empty()
    )

    private fun idleState(oldState: State, error: Throwable) = State(
        oldState.currentPath,
        emptyList(),
        false,
        Optional.create(error)
    )

    private fun refreshingState(oldState: State) = State(
        oldState.currentPath,
        oldState.fsItems,
        true,
        Optional.empty()
    )

    class State(
        val currentPath: String?,
        val fsItems: List<FsItem>,
        val isBusy: Boolean,
        val lastError: Optional<Throwable>
    )
}