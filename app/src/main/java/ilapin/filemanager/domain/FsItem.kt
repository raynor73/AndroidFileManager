package ilapin.filemanager.domain

data class FsItem(
    val name: String,
    val type: Type,
    val parentPath: String?
) {
    enum class Type {
        FILE, DIR
    }
}