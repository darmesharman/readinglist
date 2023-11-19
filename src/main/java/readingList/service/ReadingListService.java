package readingList.service;

import org.springframework.ui.Model;
import readingList.dto.BookDTO;

import java.util.List;

public interface ReadingListService {

    List<BookDTO> getReadingListForCurrentUser();

    void populateModelWithReadingList(Model model);

    void saveBook(BookDTO book);

}
