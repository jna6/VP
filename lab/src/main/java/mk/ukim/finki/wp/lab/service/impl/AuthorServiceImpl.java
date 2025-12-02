package mk.ukim.finki.wp.lab.service.impl;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    public static List<Author> authors = new ArrayList<>();


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = (AuthorRepository) authorRepository;
    }

    @PostConstruct
    public void init() {
        if (authorRepository.findAll().isEmpty()) {
            authors.add(new Author("Stephenie", "Meyer", "USA", "Famous for the 'Twilight' saga."));
            authors.add(new Author("Jane", "Austen", "UK", "Renowned for her romantic novels set in British society."));
            authors.add(new Author("Gillian", "Flynn", "USA", "Known for her dark psychological thrillers like 'Gone Girl'."));
            authors.add(new Author("Louisa May", "Alcott", "USA", "Best known for her novel 'Little Women'."));
            authors.add(new Author("Jojo", "Moyes", "UK", "Author of emotional romantic fiction like 'Me Before You'."));
            authors.add(new Author("Stephen", "King", "USA", "Master of horror and suspense, author of 'The Shining'."));
            authors.add(new Author("Walter", "Isaacson", "USA", "Biographer known for 'Steve Jobs' and other tech icons."));
            authors.add(new Author("Stieg", "Larsson", "Sweden", "Creator of the Millennium series, 'The Girl with the Dragon Tattoo'."));
            authors.add(new Author("Virginia", "Woolf", "UK", "Modernist author, wrote 'To the Lighthouse'."));
            authors.add(new Author("Shel", "Silverstein", "USA", "Poet, cartoonist, and author of 'A Light in the Attic'."));
            authorRepository.saveAll(authors);
        }
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public void likeAuthor(Long id) {
        Author author = findById(id);
        author.setLikes(author.getLikes() + 1);
        authorRepository.save(author);
    }
}
