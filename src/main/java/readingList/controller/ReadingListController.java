package readingList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);

        model.addAttribute("books", readingList);
        model.addAttribute("amazonId", amazonProperties.getAssociateId());

        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);

        return "redirect:/readingList/{reader}";
    }

}
