package readingList.dto;

public record BookDTO(
        String isbn,
        String title,
        String author,
        String description
) {
}
