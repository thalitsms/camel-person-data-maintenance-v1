package br.com.runthebank.persondata.repository;

import br.com.runthebank.persondata.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}

