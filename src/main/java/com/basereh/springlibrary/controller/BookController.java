package com.basereh.springlibrary.controller;

import com.basereh.springlibrary.controller.dto.BookDto;
import com.basereh.springlibrary.controller.mapper.BookMapper;
import com.basereh.springlibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks().stream().map(bookMapper::toDto).toList();
    }

    @GetMapping(value = "/{id}")
    public BookDto getSingleBook(@PathVariable("id") Long id) {
        return bookMapper.toDto(bookService.getBook(id));
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookMapper.toDto(bookService.createBook(bookMapper.toModel(bookDto)));
    }

    @PutMapping(value = "/{id}")
    public BookDto updateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        return bookMapper.toDto(bookService.updateBook(id, bookMapper.toModel(bookDto)));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
