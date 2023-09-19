package com.basereh.springlibrary.repository;

import com.basereh.springlibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository  extends JpaRepository<Publisher, Long> {
}
