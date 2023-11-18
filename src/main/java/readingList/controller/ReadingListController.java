package readingList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import readingList.config.properties.AmazonProperties;
import readingList.entity.Book;
import readingList.repository.ReadingListRepository;
import readingList.util.UserUtil;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/readingList")
public class ReadingListController {

    private final AmazonProperties amazonProperties;

    private final ReadingListRepository readingListRepository;

    private final UserUtil userUtil;

    @GetMapping
    public String readersBooks(Model model) {
        List<Book> readingList = readingListRepository.findByReader(userUtil.getCurrentUser().getUsername());

        model.addAttribute("books", readingList);
        model.addAttribute("amazonId", amazonProperties.getAssociateId());

        return "readingList";
    }

    @PostMapping
    public String addToReadingList(Book book) {
        book.setReader(userUtil.getCurrentUser().getUsername());
        readingListRepository.save(book);

        return "redirect:/readingList";
    }

}
