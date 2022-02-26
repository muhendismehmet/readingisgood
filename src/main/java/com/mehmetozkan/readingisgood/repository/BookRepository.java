package com.mehmetozkan.readingisgood.repository;

import com.mehmetozkan.readingisgood.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
