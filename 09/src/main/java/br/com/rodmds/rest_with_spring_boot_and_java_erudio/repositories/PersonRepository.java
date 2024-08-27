package br.com.rodmds.rest_with_spring_boot_and_java_erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
