package com.basereh.springlibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {       // todo comment serializable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();          // todo comment equals and hashcode dashte bashad
}