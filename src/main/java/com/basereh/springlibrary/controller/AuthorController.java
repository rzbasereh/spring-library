package com.basereh.springlibrary.controller;

import com.basereh.springlibrary.mapper.AuthorMapper;
import com.basereh.springlibrary.model.dto.AuthorDto;
import com.basereh.springlibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final  AuthorMapper authorMapper;

    @GetMapping
    public List<AuthorDto> getAllAuthor() {
        return authorService.getAllAuthors().stream().map(authorMapper::toDto).toList();
    }

    @GetMapping(value = "/{id}")
    public AuthorDto getSingleAuthor(@PathVariable("id") Long id) {
        return authorMapper.toDto(authorService.getSingleAuthor(id));
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorMapper.toDto(authorService.createAuthor(authorMapper.toModel(authorDto)));
    }

    @PutMapping(value = "/{id}")
    public AuthorDto updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        return authorMapper.toDto(authorService.updateAuthor(id, authorMapper.toModel(authorDto)));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}
