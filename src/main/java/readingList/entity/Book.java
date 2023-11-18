package readingList.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String isbn;

    private String title;

    private String author;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
