package com.basereh.springlibrary.service;


import com.basereh.springlibrary.model.Publisher;
import com.basereh.springlibrary.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getSinglePublisher(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("publisher not found"));
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Long id, Publisher publisher) {
        Publisher prevPublisher = getSinglePublisher(id);
        return publisherRepository.save(prevPublisher.toBuilder()
                .name(publisher.getName())
                .build());

    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
