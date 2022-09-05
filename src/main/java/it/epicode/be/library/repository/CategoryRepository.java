package it.epicode.be.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.library.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
