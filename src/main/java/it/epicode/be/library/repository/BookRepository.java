package it.epicode.be.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	Page<Book> findByAuthorsId(Long author, Pageable pageable);
	
	Page<Book> findByCategoriesName(String category, Pageable pageable);
}