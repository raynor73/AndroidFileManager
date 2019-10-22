package ilapin.filemanager.domain

import io.reactivex.Single

interface FileSystemRepository {

    fun getFsItems(path: String): Single<List<FsItem>>
}