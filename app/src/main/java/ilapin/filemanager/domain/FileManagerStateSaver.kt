package ilapin.filemanager.domain

interface FileManagerStateSaver {

    fun getState(): FileManager.State?

    fun putState(state: FileManager.State)
}