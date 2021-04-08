data class Comment (
    val noteId: Long, // идентификатор поста
    val replyTo: Int, //
    val fromId: Int, // идентификатор автора комментария.
    var text: String, // текст комментария.
    val date: Int = 12345, // дата создания комментария в формате Unixtime.
    val replyToUser: Int? = null, // идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
    val replyToComment: Int? = null, // идентификатор комментария, в ответ на который оставлен текущий (если применимо).

    override var id: Long? = 0, // идентификатор комментария.
    override var deleted: Boolean = false
): Content()