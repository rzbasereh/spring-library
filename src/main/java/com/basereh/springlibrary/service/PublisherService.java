package com.basereh.springlibrary.service;


import com.basereh.springlibrary.model.Publisher;
import com.basereh.springlibrary.repository.PublisherRepository;
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
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Long id, Publisher publisher) {
        Publisher prevPublisher = getSinglePublisher(id);
        if (prevPublisher != null) {
            return publisherRepository.save(publisher.toBuilder()
                    .name(publisher.getName())
                    .build());
        }
        return null;
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
