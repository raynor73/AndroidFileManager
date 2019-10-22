package ilapin.filemanager.domain

interface FileSystemRepository {

    fun getFsItems(path: String): List<FsItem>
}