fun main() {
    val note1Id = NoteService.add("Title 1", "Text 1");
    val note1 = NoteService.getById(note1Id)
    println(note1)


    val comment1Id = NoteService.createComment(note1Id, 1, 2, "Comment 1")
    val comment1 = NoteService.getCommentById(comment1Id)

    println(comment1)

    //NoteService.delete(note1Id)
    //println(note1)
}