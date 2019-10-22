package ilapin.filemanager.utils

class Optional<T> private constructor(private val value: T?) {

    val isEmpty: Boolean
        get() = value == null

    fun get(): T {
        return value ?: throw IllegalStateException("No value")
    }

    companion object {

        @JvmStatic
        fun <T> create(value: T): Optional<T> {
            return Optional(value)
        }

        @JvmStatic
        fun <T> empty(): Optional<T> {
            return Optional(null)
        }
    }
}