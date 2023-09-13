package com.basereh.springlibrary.repository;

import com.basereh.springlibrary.model.audit.AuthorAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorAudit, Integer> {
}
