package it.epicode.be.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
