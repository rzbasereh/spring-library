package com.basereh.springlibrary.controller;

import com.basereh.springlibrary.model.dto.AuthorDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @GetMapping("")
    public String getAllAuthor() {
        return "GET ALL";
    }

    @GetMapping(value = "/{id}")
    public String getSingleAuthor(@PathVariable("id") Integer id) {
        return "GET " + id;
    }

    @PostMapping("")
    public String createAuthor(@RequestBody AuthorDto authorDto) {
        return "CREATE";
    }

    @PutMapping(value = "/{id}")
    public String updateAuthor(@PathVariable("id") Integer id, @RequestBody AuthorDto authorDto) {
        return "UPDATE";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id) {
        return "DELETE" + id;
    }
}
