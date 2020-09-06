package me.dwywdo.springapplicationcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        final BookService bookService = new BookService();
        // bookService.setBookRepository(bookRepository()); // with no parameter
        bookService.setBookRepository(bookRepository); // with parameter
        // 3rd way? @Autowired at BookService.java

        return bookService;
    }
}
