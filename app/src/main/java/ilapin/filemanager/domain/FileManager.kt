package ilapin.filemanager.domain

import ilapin.filemanager.utils.Optional
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class FileManager {

    private val stateSubject = BehaviorSubject.createDefault(
        State(null, emptyList(), false, Optional.empty())
    )
    private val errorSubject = PublishSubject.create<Throwable>()

    val state: Observable<State> = stateSubject
    val error: Observable<Throwable> = errorSubject

    class State(
        val currentPath: String?,
        val fsItems: List<FsItem>,
        val isBusy: Boolean,
        val lastError: Optional<Throwable>
    )
}