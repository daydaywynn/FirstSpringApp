package guru.springframework.spring6webapp.Services;

import guru.springframework.spring6webapp.Repositories.BookRepository;
import guru.springframework.spring6webapp.domain.Book;
import org.springframework.stereotype.Service;

@Service //Registers this as a spring component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;

    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepo.findAll();
    }
}
