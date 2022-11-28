package com.wildcodeschool.library.controller;


import com.wildcodeschool.library.entity.Book;
import com.wildcodeschool.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository repository;

    @GetMapping("/books")
    public List<Book> index(){
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable long id){
        return repository.findById(id).get();
    }

    @GetMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        System.out.println("SEARCH TERME => " + searchTerm);
        return repository.findByTitleContainingOrContentContaining(searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable long id, @RequestBody Book book){
        Book bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable long id){
        repository.deleteById(id);
        return true;
    }

}
