package com.example.bibliothequeAPI.repository;

import com.example.bibliothequeAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
