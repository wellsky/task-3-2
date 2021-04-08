import org.junit.Assert
import org.junit.Test

class NoteServiceTest {
    @Test
    fun addNoteTest() {
        NoteService.reset()

        val noteId = NoteService.add("Title 1", "Text 1");
        val note = NoteService.getById(noteId)

        Assert.assertEquals("Title 1", note.title);
    }

    @Test
    fun addCommentTest() {
        NoteService.reset()

        val noteId = NoteService.add("Title 1", "Text 1");
        val commentId = NoteService.createComment(noteId, 1,2, "Comment text")
        val comment = NoteService.getCommentById(commentId)
        Assert.assertEquals("Comment text", comment.text);
    }

    @Test(expected = NoteNotFoundException::class)
    fun addAndDeleteNoteTest() {
        NoteService.reset()

        val note1Id = NoteService.add("Title 1", "Text 1");
        NoteService.delete(note1Id)
        val note = NoteService.getById(note1Id)
    }

    @Test(expected = CommentNotFoundException::class)
    fun addAndDeleteCommentTest() {
        NoteService.reset()

        val noteId = NoteService.add("Title 1", "Text 1");
        val commentId = NoteService.createComment(noteId, 1,2, "Comment text")
        NoteService.deleteComment(commentId)
        val comment = NoteService.getCommentById(commentId)
    }
}