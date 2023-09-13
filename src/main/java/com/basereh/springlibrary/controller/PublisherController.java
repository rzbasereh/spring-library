package com.basereh.springlibrary.controller;

import com.basereh.springlibrary.controller.dto.PublisherDto;
import com.basereh.springlibrary.controller.mapper.PublisherMapper;
import com.basereh.springlibrary.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherMapper publisherMapper;
    private final PublisherService publisherService;

    @GetMapping
    public List<PublisherDto> getAllPublishers() {
        return publisherService.getAllPublishers().stream().map(publisherMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public PublisherDto getSinglePublisher(@PathVariable("id") Long id) {
        return publisherMapper.toDto(publisherService.getSinglePublisher(id));
    }

    @PostMapping
    public PublisherDto createPublisher(@RequestBody PublisherDto publisherDto) {
        return publisherMapper.toDto(publisherService.createPublisher(publisherMapper.toModel(publisherDto)));
    }

    @PutMapping("/{id}")
    public PublisherDto updatePublisher(@PathVariable("id") Long id, @RequestBody PublisherDto publisherDto) {
        return publisherMapper.toDto(publisherService.updatePublisher(id, publisherMapper.toModel(publisherDto)));
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
    }
}
