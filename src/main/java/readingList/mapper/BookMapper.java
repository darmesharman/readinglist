package readingList.mapper;

import org.springframework.stereotype.Component;
import readingList.dto.BookDTO;
import readingList.entity.Book;

@Component
public class BookMapper implements Mapper<Book, BookDTO> {

    @Override
    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setIsbn(bookDTO.isbn());
        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setDescription(bookDTO.description());

        return book;
    }

    @Override
    public BookDTO toDTO(Book book) {
        return new BookDTO(
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription());
    }
}
