data class Comment(
    val id: Int? = 0, // идентификатор комментария.
    val noteId: Int, // идентификатор поста
    val replyTo: Int, //
    val fromId: Int, // идентификатор автора комментария.
    val date: Int, // дата создания комментария в формате Unixtime.
    var text: String, // текст комментария.
    val replyToUser: Int? = null, // идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
    val replyToComment: Int? = null, // идентификатор комментария, в ответ на который оставлен текущий (если применимо).
    var deleted: Boolean = false
)