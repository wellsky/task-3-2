fun main() {

    val note1Id = NoteService.add(Note("Title 1", "Text 1"));
    val note2Id = NoteService.add(Note("Title 2", "Text 2"));

    val note1 = NoteService.getById(note1Id)
    val note2 = NoteService.getById(note2Id)

    CommentService.add(Comment(note1Id, 1,2, "Comment text"))
    CommentService.add(Comment(note1Id, 1,2, "Comment text"))
    CommentService.add(Comment(note2Id, 1,2, "Comment text"))

    println(NoteService.read())
    println(CommentService.getByNote(note2))
}