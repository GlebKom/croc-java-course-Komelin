package course.Komelin.task10;

import javax.xml.stream.StreamFilter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @param <T> The type in which the comment is presented
 */
public interface BlackListFilter<T> {

    /**
     * From the given sequence of comments removes ones
     * that contain words which are not acceptable due to given condition.
     *
     * @param condition if condition is true - comment will be presented in result sequence
     *                  if condition is false - comment will not be presented in result sequence
     * @param comments sequence of comments, presented in any type.
     *
     * @return the sequence of comments represented as Iterable
     */
    default Iterable<T> filterComments(Predicate<T> condition, Iterable<T> comments) {
        Objects.requireNonNull(condition);
        Objects.requireNonNull(comments);

        return StreamSupport
                .stream(comments.spliterator(), false)
                .filter(condition)
                .collect(Collectors.toList());
    }
}
