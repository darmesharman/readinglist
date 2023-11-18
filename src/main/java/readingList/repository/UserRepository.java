package readingList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readingList.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
