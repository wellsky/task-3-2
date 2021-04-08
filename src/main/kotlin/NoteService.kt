object NoteService {
    // Коды возвращаемых значений из документации VK
    private const val CODE_SUCCESS = 1
    private const val ERROR_CODE_NOT_FOUND = 180

    private var notes: MutableList<Note> = mutableListOf<Note>()
    private var nextNoteId: Int = 0

    private var comments: MutableList<Comment> = mutableListOf<Comment>()
    private var nextCommentId: Int = 0

    fun reset() {
        notes = mutableListOf<Note>()
        nextNoteId = 0

        comments = mutableListOf<Comment>()
        nextNoteId = 0
    }

    fun add(title: String, text:String): Int { // Создает новую заметку у текущего пользователя
        // privacy_view, privacy_comment
        this.notes.add(Note(++nextNoteId, title, text))
        return nextNoteId
    }

    fun createComment(noteId: Int, fromId: Int, replyTo: Int, message: String): Int { //Добавляет новый комментарий к заметке
        // owner_id, guid
        this.comments.add(Comment(id = ++nextCommentId, noteId = noteId, replyTo = replyTo, date=1236456, text=message, fromId = fromId))
        return nextCommentId
    }

    fun delete(noteId: Int): Int { // Удаляет заметку текущего пользователя
        val note = this.getById(noteId)
        note.deleted = true
        return CODE_SUCCESS
    }

    fun deleteComment(commentId: Int): Int { // Удаляет комментарий к заметке
        // owner_id
        val comment = this.getCommentById(commentId)
        comment.deleted = true
        return CODE_SUCCESS
    }

    fun edit(noteId: Int, title: String, text: String): Int { // Редактирует заметку текущего пользователя
        // privacy_view, privacy_comment
        val note = this.getById(noteId)
        note.title = title
        note.text = text
        return CODE_SUCCESS
    }

    fun editComment(commentId: Int, message: String): Int { // Редактирует указанный комментарий у заметки
        // owner_id
        val comment = this.getCommentById(commentId)
        comment.text = message
        return CODE_SUCCESS
    }

    fun get(noteIds: Array<Int>): MutableList<Note> { //Возвращает список заметок, созданных пользователем
        // user_id, offset, count, sort
        var list: MutableList<Note> = mutableListOf<Note>()

        this.notes.forEach {
            if ((it.id in noteIds) && (!it.deleted)) {
                list.add(it)
            }
        }
        return list
    }

    fun getById(noteId: Int): Note { // Возвращает заметку по её id.
        // owner_id, need_wiki
        this.notes.forEach {
            if ((it.id == noteId) && (!it.deleted)) {
                return it
            }
        }
        throw NoteNotFoundException("Note not found")
    }

    fun getComments(noteId: Int): MutableList<Comment> { // Возвращает список комментариев к заметке.
        // owner_id, sort, offset, count
        var list: MutableList<Comment> = mutableListOf<Comment>()

        this.comments.forEach {
            if ((it.noteId == noteId) && (!it.deleted)) {
                list.add(it)
            }
        }
        return list
    }

    fun restoreComment(commentId: Int): Int { // Восстанавливает удалённый комментарий.
        // owner_id
        val comment = this.getCommentById(commentId, true)
        comment.deleted = false
        return CODE_SUCCESS
    }

    fun getCommentById(commentId: Int, includingDeleted: Boolean = false): Comment { // Возвращает комментарий по его id.
        // owner_id, need_wiki
        this.comments.forEach {
            if (it.id == commentId) {
                if ((!it.deleted) || (includingDeleted)) {
                    return it
                }
            }
        }
        throw CommentNotFoundException("Comment not found")
    }
}