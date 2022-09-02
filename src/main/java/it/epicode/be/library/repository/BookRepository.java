package it.epicode.be.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
