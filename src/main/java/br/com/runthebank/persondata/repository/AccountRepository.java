package br.com.runthebank.persondata.repository;

import br.com.runthebank.persondata.model.entity.Account;
import br.com.runthebank.persondata.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByBranchCode(int branchCode);
    List<Account> findByPerson(Person person);


}
