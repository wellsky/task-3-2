data class Note (
    var title: String,
    var text: String,
    override var id: Long? = null,
    override var deleted: Boolean = false
): Content()