package ilapin.filemanager.domain

interface HomeDirectoryRepository {

    fun getHomeDirectoryPath(): String
}