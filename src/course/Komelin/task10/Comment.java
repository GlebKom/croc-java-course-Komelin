package course.Komelin.task10;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {

    private final String title;

    private final String content;

    private final LocalDateTime dateOfWriting;

    public Comment(String title, String content) {

        this.title = title;
        this.content = content;
        this.dateOfWriting = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateOfWriting() {
        return dateOfWriting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(title, comment.title) && Objects.equals(content, comment.content) && Objects.equals(dateOfWriting, comment.dateOfWriting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, dateOfWriting);
    }
}
