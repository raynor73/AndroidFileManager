package ilapin.filemanager.domain

interface CurrentDirectoryPathStorage {

    fun getCurrentDirectoryPath(): String?

    fun putCurrentDirectoryPath(path: String)
}