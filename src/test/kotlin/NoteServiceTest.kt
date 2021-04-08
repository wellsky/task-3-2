import org.junit.Assert
import org.junit.Test

class NoteServiceTest {
    @Test
    fun addNoteTest() {
        NoteService.reset()
        CommentService.reset()

        val noteId = NoteService.add(Note("Title 1", "Text 1"));
        val note = NoteService.getById(noteId)

        Assert.assertEquals("Title 1", note.title);
    }

    @Test
    fun addCommentTest() {
        NoteService.reset()
        CommentService.reset()

        val noteId = NoteService.add(Note("Title 1", "Text 1"));
        val commentId = CommentService.add(Comment(noteId, 1,2, "Comment text"))
        val comment = CommentService.getById(commentId)
        Assert.assertEquals("Comment text", comment.text);
    }

    @Test
    fun addAndEditCommentTest() {
        NoteService.reset()
        CommentService.reset()

        val noteId = NoteService.add(Note("Title 1", "Text 1"));
        val commentId = CommentService.add(Comment(noteId, 1,2, "Comment text"))
        CommentService.edit(commentId, "New text")
        val comment = CommentService.getById(commentId)
        Assert.assertEquals("New text", comment.text);
    }

    @Test(expected = ContentNotFoundException::class)
    fun addAndDeleteNoteTest() {
        NoteService.reset()
        CommentService.reset()

        val note1Id = NoteService.add(Note("Title 1", "Text 1"));
        NoteService.delete(note1Id)
        val note = NoteService.getById(note1Id)
    }

    @Test(expected = ContentNotFoundException::class)
    fun addAndDeleteCommentTest() {
        NoteService.reset()
        CommentService.reset()

        val noteId = NoteService.add(Note("Title 1", "Text 1"));
        val commentId = CommentService.add(Comment(noteId, 1,2, "Comment text"))
        CommentService.delete(commentId)
        val comment = CommentService.getById(commentId)
    }

    @Test
    fun addAndDeleteAndRestoreCommentTest() {
        NoteService.reset()
        CommentService.reset()

        val noteId = NoteService.add(Note("Title 1", "Text 1"));
        val commentId = CommentService.add(Comment(noteId, 1,2, "Comment text"))
        CommentService.delete(commentId)
        CommentService.restore(commentId)
        val comment = CommentService.getById(commentId)
        Assert.assertEquals("Comment text", comment.text);
    }
}