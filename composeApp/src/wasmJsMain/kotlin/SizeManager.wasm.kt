import kotlin.js.Promise

external class SizeManager {
    fun getChanges(): Promise<JsAny?>
    fun resize()
}
