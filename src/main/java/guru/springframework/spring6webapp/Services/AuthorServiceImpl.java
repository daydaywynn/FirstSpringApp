package guru.springframework.spring6webapp.Services;

import guru.springframework.spring6webapp.Repositories.AuthorRepository;
import guru.springframework.spring6webapp.domain.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepo;
    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }
    @Override
    public Iterable<Author> findAll() {
        return authorRepo.findAll();
    }
}
