package com.basereh.springlibrary.service;

import com.basereh.springlibrary.model.Author;
import com.basereh.springlibrary.repository.AuthorRepository;
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
        return authorRepository.findById(id).orElse(null);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author author) {
        Author prevAuthor = getSingleAuthor(id);
        if (prevAuthor != null) {
            return authorRepository.save(prevAuthor.toBuilder()
                    .firstname(author.getFirstname())
                    .lastname(author.getLastname())
                    .build());
        }
        return null;
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
