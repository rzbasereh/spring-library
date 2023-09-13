package com.basereh.springlibrary.controller;

import com.basereh.springlibrary.model.dto.PublisherDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @GetMapping("")
    public String getAllPublishers() {
        return "GET ALL";
    }

    @GetMapping("/{id}")
    public String getSinglePublisher(@PathVariable("id") Integer id) {
        return "GET" + id;
    }

    @PostMapping("")
    public String createPublisher(@RequestBody PublisherDto publisherDto) {
        return "CREATE";
    }

    @PutMapping("/{id}")
    public String updatePublisher(@PathVariable("id") Integer id, @RequestBody PublisherDto publisherDto) {
        return "PUT";
    }

    @DeleteMapping("/{id}")
    public String deletePublisher(@PathVariable("id") Integer id) {
        return "DELETE";
    }
}
