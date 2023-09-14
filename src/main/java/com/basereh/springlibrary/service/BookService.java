package com.basereh.springlibrary.service;

import com.basereh.springlibrary.model.Book;
import com.basereh.springlibrary.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book prevBook = getBook(id);
        if (prevBook != null) {
            bookRepository.save(prevBook.toBuilder()
                    .name(book.getName())
                    .authors(book.getAuthors())
                    .publisher(book.getPublisher())
                    .build());
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
