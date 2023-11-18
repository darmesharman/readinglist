package readingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readingList.entity.Book;
import readingList.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReadingListRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findByUser(User user);

}
