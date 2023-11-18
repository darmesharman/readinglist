package readingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readingList.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String> {

}
