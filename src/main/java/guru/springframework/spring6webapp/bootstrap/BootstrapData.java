package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.Repositories.AuthorRepository;
import guru.springframework.spring6webapp.Repositories.BookRepository;
import guru.springframework.spring6webapp.Repositories.PublisherRepository;
import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner { // CommandLineRunner - when it detects this component, pick it up and execute the run method

    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;

    private final PublisherRepository publisherRepo;

    public BootstrapData(AuthorRepository authorRepo, BookRepository bookRepo, PublisherRepository publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override public void run(String... args) throws Exception{
        //Authors
        Author dayday = new Author();
        dayday.setFirstName("De Kwaan");
        dayday.setLastName("Wynn");

        //books
        Book firstBook = new Book();
        firstBook.setTitle("The things we leave behind");
        firstBook.setIsbn("12345");
        Book secondBook = new Book();
        secondBook.setTitle("Things Retrieved");
        secondBook.setIsbn("23455");


        Author daydaySaved = authorRepo.save(dayday); //Puts it in  the DB
        Book firstBookSaved = bookRepo.save(firstBook);
        Book secondBookSaved = bookRepo.save(secondBook);

        /**
         * Building the association
         */
        daydaySaved.getBooks().add(firstBookSaved);
        daydaySaved.getBooks().add(secondBookSaved);

        firstBookSaved.getAuthors().add(daydaySaved);
        secondBookSaved.getAuthors().add(daydaySaved);

        authorRepo.save(daydaySaved); //Saving info again because I changed daydaySaved

        System.out.println("In Bootstrap");
        System.out.println("Author Count " + authorRepo.count());
        System.out.println("Book Count " + bookRepo.count());

        //Creating a publisher
        Publisher firstPub = new Publisher();
        firstPub.setPublisherName("The Publishers");
        firstPub.setAddress("1007 Graham");
        firstPub.setCity("Odessa");
        firstPub.setZip("76767");
        firstPub.setState("Texas");
        publisherRepo.save(firstPub);

        System.out.println("Publisher Count: " + publisherRepo.count());

        firstBookSaved.setPublisher(firstPub);
        secondBookSaved.setPublisher(firstPub);
        bookRepo.save(firstBookSaved);
        bookRepo.save(secondBookSaved);
    }
}
