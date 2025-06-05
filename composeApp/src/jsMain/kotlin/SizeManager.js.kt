import kotlin.js.Promise

external class SizeManager {
    fun getChanges(): Promise<Size>
    fun resize()
}
