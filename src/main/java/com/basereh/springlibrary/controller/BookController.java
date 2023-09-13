package com.basereh.springlibrary.controller;

import com.basereh.springlibrary.controller.dto.BookDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping("")
    public String getAllBooks() {
        return "GET ALL";
    }

    @GetMapping(value = "/{id}")
    public String getSingleBook(@PathVariable("id") Integer id) {
        return "GET " + id;
    }

    @PostMapping("")
    public String createBook(@RequestBody BookDto bookDto) {
        return "CREATE";
    }

    @PutMapping(value = "/{id}")
    public String updateBook(@PathVariable("id") Integer id, @RequestBody BookDto bookDto) {
        return "UPDATE";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        return "DELETE" + id;
    }
}
