package br.com.runthebank.persondata.repository;

import br.com.runthebank.persondata.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByDocumentNumber(String documentNumber);
    Optional<Person> findByDocumentNumber(String documentNumber);

}
