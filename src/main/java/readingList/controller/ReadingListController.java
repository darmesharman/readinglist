package readingList.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import readingList.dto.BookDTO;
import readingList.service.ReadingListService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/readingList")
public class ReadingListController {

    private final ReadingListService readingListService;

    @GetMapping
    public String readersBooks(Model model) {
        readingListService.populateModelWithReadingList(model);

        return "readingList";
    }

    @PostMapping
    public String addToReadingList(BookDTO bookDTO) {
        readingListService.saveBook(bookDTO);

        return "redirect:/readingList";
    }

}
