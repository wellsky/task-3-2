object NoteService: CrudService<Note> {
    override var items: MutableList<Note> = mutableListOf<Note>()
    override var nextItemId: Long = 0

    override fun edit(note: Note) {

    }

    fun edit(noteId: Long, title: String, text: String) { // Редактирует заметку
        // privacy_view, privacy_comment
        val note = this.getById(noteId)
        note.title = title
        note.text = text
    }

    fun get(noteIds: Array<Long>): MutableList<Note> { //Возвращает список заметок, созданных пользователем
        // user_id, offset, count, sort
        var list: MutableList<Note> = mutableListOf<Note>()

        this.items.forEach {
            if ((it.id in noteIds) && (!it.deleted)) {
                list.add(it)
            }
        }
        return list
    }
}