package com.wildcodeschool.library.repository;


import com.wildcodeschool.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * from book WHERE title LIKE %:search% || description LIKE %:search%", nativeQuery = true)
    List<Book> findByTitleContainingOrContentContaining(@Param("search") String search);
}
