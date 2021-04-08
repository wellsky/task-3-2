object CommentService: CrudService<Comment> {
    override var items: MutableList<Comment> = mutableListOf<Comment>()
    override var nextItemId: Long = 0

    override fun edit(comment: Comment) {

    }

    fun edit(commentId: Long, message: String) { // Редактирует указанный комментарий
        // owner_id
        val comment = this.getById(commentId)
        comment.text = message
    }

    fun getByNote(note: Note, includingDeleted: Boolean = false): MutableList<Comment>  {
        var list: MutableList<Comment> = mutableListOf<Comment>()

        this.items.forEach {
            if ((it.noteId == note.id) && (!it.deleted)) {
                list.add(it)
            }
        }
        return list
    }
}