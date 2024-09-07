package br.com.rodmds.rest_with_spring_boot_and_java_erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
