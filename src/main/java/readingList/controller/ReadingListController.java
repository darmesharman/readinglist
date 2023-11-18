package readingList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import readingList.config.properties.AmazonProperties;
import readingList.entity.Book;
import readingList.repository.ReadingListRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/readingList")
public class ReadingListController {

    private final AmazonProperties amazonProperties;

    private final ReadingListRepository readingListRepository;

    @GetMapping(value = "/{reader}")
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);

        model.addAttribute("books", readingList);
        model.addAttribute("amazonId", amazonProperties.getAssociateId());

        return "readingList";
    }

    @PostMapping(value = "/{reader}")
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);

        return "redirect:/readingList/{reader}";
    }

}
