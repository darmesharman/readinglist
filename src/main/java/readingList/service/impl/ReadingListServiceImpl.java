package readingList.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import readingList.config.properties.AmazonProperties;
import readingList.entity.Book;
import readingList.repository.ReadingListRepository;
import readingList.service.ReadingListService;
import readingList.util.UserUtil;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingListServiceImpl implements ReadingListService {

    private final ReadingListRepository readingListRepository;

    private final AmazonProperties amazonProperties;

    private final UserUtil userUtil;

    @Override
    public List<Book> getReadingListForCurrentUser() {
        return readingListRepository.findByUser(userUtil.getCurrentUser())
                .orElse(Collections.emptyList());
    }

    @Override
    public void populateModelWithReadingList(Model model) {
        List<Book> readingList = getReadingListForCurrentUser();

        model.addAttribute("books", readingList);
        model.addAttribute("amazonId", amazonProperties.getAssociateId());
    }

    @Override
    public void saveBook(Book book) {
        book.setUser(userUtil.getCurrentUser());

        readingListRepository.save(book);
    }

}
