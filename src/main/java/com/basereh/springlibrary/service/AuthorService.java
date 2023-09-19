package com.basereh.springlibrary.service;

import com.basereh.springlibrary.model.Author;
import com.basereh.springlibrary.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getSingleAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("author not found."));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author author) {
        Author prevAuthor = getSingleAuthor(id);
        return authorRepository.save(prevAuthor.toBuilder()
                .firstname(author.getFirstname())
                .lastname(author.getLastname())
                .build());
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
