package readingList.service;

import org.springframework.ui.Model;
import readingList.entity.Book;

import java.util.List;

public interface ReadingListService {

    List<Book> getReadingListForCurrentUser();

    void populateModelWithReadingList(Model model);

    void saveBook(Book book);

}
