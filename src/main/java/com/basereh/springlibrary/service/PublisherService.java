package com.basereh.springlibrary.service;


import com.basereh.springlibrary.model.Publisher;
import com.basereh.springlibrary.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getSinglePublisher(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Long id, Publisher publisher) {
        Optional<Publisher> prevPublisher = getSinglePublisher(id);
        if (prevPublisher.isPresent()) {
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
