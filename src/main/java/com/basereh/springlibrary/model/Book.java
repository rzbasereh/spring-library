package com.basereh.springlibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    public String name;

    @ManyToOne
    @JoinColumn(name="publisher_id", referencedColumnName ="id")
    public Publisher publisher;

    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName ="id")
    public Author author;

    @Override
    public String toString() {
        return name + "(pub: " + publisher.toString() + " )(author: " + author.toString() + " )";
    }
}
