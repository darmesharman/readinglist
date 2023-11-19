package readingList.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import readingList.config.properties.AmazonProperties;
import readingList.dto.BookDTO;
import readingList.entity.Book;
import readingList.mapper.Mapper;
import readingList.repository.BookRepository;
import readingList.service.ReadingListService;
import readingList.util.UserUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadingListServiceImpl implements ReadingListService {

    private final BookRepository bookRepository;

    private final AmazonProperties amazonProperties;

    private final UserUtil userUtil;

    private final Mapper<Book, BookDTO> bookMapper;

    @Override
    public List<BookDTO> getReadingListForCurrentUser() {
        List<Book> books = bookRepository.findByUser(userUtil.getCurrentUser())
                .orElse(Collections.emptyList());

        return books.stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void populateModelWithReadingList(Model model) {
        List<BookDTO> readingList = getReadingListForCurrentUser();

        model.addAttribute("books", readingList);
        model.addAttribute("amazonId", amazonProperties.getAssociateId());
    }

    @Override
    public void saveBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book.setUser(userUtil.getCurrentUser());

        bookRepository.save(book);
    }

}
