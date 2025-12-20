package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class DataHolder{
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public DataHolder(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @PostConstruct
    public void init()  {

        if (bookRepository.findAll().isEmpty()) {
            books = new ArrayList<>();
            books.add(new Book("Twilight", "Fantasy", 3.44));
            books.add(new Book("Pride and Prejudice", "Historical", 4.3));
            books.add(new Book("Gone Girl", "Mystery", 4.12));
            books.add(new Book("Little Women", "Fiction", 4.12));
            books.add(new Book("Me Before You", "Romance", 4.2));
            books.add(new Book("The Shining", "Horror", 4.24));
            books.add(new Book("Steve Jobs", "Biography", 4.14));
            books.add(new Book("The Girl with the Dragon Tattoo", "Fiction", 4.12));
            books.add(new Book("To the Lighthouse", "Classics", 3.8));
            books.add(new Book("A Light in the Attic", "Poetry", 4.36));
            bookRepository.saveAll(books);
        }

//        if (authorRepository.count() > 0 || bookRepository.count() > 0) {
//            return; // Don't add duplicates
//        }
//        Author a1 = new Author("Stephenie", "Meyer", "USA", "Famous for the 'Twilight' saga.");
//        Author a2 = new Author("Jane", "Austen", "UK", "Renowned for her romantic novels.");
//        Author a3 = new Author("Gillian", "Flynn", "USA", "Known for 'Gone Girl'.");
//        Author a4 = new Author("Louisa May", "Alcott", "USA", "Best known for 'Little Women'.");
//        Author a5 = new Author("Jojo", "Moyes", "UK", "Author of 'Me Before You'.");
//        Author a6 = new Author("Stephen", "King", "USA", "Master of horror, 'The Shining'.");
//        Author a7 = new Author("Walter", "Isaacson", "USA", "Biographer of 'Steve Jobs'.");
//        Author a8 = new Author("Stieg", "Larsson", "Sweden", "Creator of 'The Girl with the Dragon Tattoo'.");
//        Author a9 = new Author("Virginia", "Woolf", "UK", "Modernist author, 'To the Lighthouse'.");
//        Author a10 = new Author("Shel", "Silverstein", "USA", "Poet and author of 'A Light in the Attic'.");
//
//        authorRepository.saveAll(List.of(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10));
//        authorRepository.flush();

//        Book b1 = new Book("Twilight", "Fantasy", 3.44, a1);
//        Book b2 = new Book("Pride and Prejudice", "Historical", 4.3, a2);
//        Book b3 = new Book("Gone Girl", "Mystery", 4.12, a3);
//        Book b4 = new Book("Little Women", "Fiction", 4.12, a4);
//        Book b5 = new Book("Me Before You", "Romance", 4.2, a5);
//        Book b6 = new Book("The Shining", "Horror", 4.24, a6);
//        Book b7 = new Book("Steve Jobs", "Biography", 4.14, a7);
//        Book b8 = new Book("The Girl with the Dragon Tattoo", "Fiction", 4.12, a8);
//        Book b9 = new Book("To the Lighthouse", "Classics", 3.8, a9);
//        Book b10 = new Book("A Light in the Attic", "Poetry", 4.36, a10);

//        a1.getBooks().add(b1);
//        a2.getBooks().add(b2);
//        a3.getBooks().add(b3);
//        a4.getBooks().add(b4);
//        a5.getBooks().add(b5);
//        a6.getBooks().add(b6);
//        a7.getBooks().add(b7);
//        a8.getBooks().add(b8);
//        a9.getBooks().add(b9);
//        a10.getBooks().add(b10);
//
//        bookRepository.saveAll(List.of(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10));
    }
}
