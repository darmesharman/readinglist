package readingList.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import readingList.entity.Book;
import readingList.entity.User;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReadingListControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails
    public void homePage() throws Exception {
        mockMvc.perform(get("/readingList").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", is(empty())));
    }

    @Test
    @WithUserDetails
    public void postBook() throws Exception {
        mockMvc.perform(post("/readingList")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Book Title")
                        .param("author", "Book Author")
                        .param("isbn", "1234567890")
                        .param("description", "Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readingList"));

        User user = new User();
        user.setUsername("user");

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setUser(user);
        expectedBook.setTitle("Book Title");
        expectedBook.setAuthor("Book Author");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("Description");

        mockMvc.perform(get("/readingList").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books",
                        contains(samePropertyValuesAs(expectedBook, "createdAt", "updatedAt"))));
    }

}
