package com.example.bibliothequeAPI.controller;

import com.example.bibliothequeAPI.model.Book;
import com.example.bibliothequeAPI.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository repository;
    private final ValidationAutoConfiguration validationAutoConfiguration;

    public BookController(BookRepository repository, ValidationAutoConfiguration validationAutoConfiguration) {
        this.repository = repository;
        this.validationAutoConfiguration = validationAutoConfiguration;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody @Valid Book book) {
        Book savedBook = repository.save(book);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Le livre a bien été ajouté");
        response.put("livre", savedBook);

        return ResponseEntity.status(201).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody @Valid Book updatedBook) {
        return repository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setAvailable(updatedBook.isAvailable());
            Book saved = repository.save(book);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Le livre a bien été mis à jour");
            response.put("book", saved);

            return ResponseEntity.ok(response);
        }).orElseGet(() -> {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Aucun livre trouvé avec l'ID " + id);
            return ResponseEntity.status(404).body(error);
        });
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "Aucun livre trouvé avec l'ID " + id);
            return ResponseEntity.status(404).body(errors);
        }
        repository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Le livre a bien été supprimé");
        return ResponseEntity.ok(response);
    }
}
