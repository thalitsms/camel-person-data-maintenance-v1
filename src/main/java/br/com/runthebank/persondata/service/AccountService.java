package br.com.runthebank.persondata.service;

import br.com.runthebank.persondata.exception.NotFoundException;
import br.com.runthebank.persondata.model.dto.AccountDto;
import br.com.runthebank.persondata.model.dto.AccountGetResponseDto;
import br.com.runthebank.persondata.model.entity.Account;
import br.com.runthebank.persondata.model.entity.Person;
import br.com.runthebank.persondata.repository.AccountRepository;
import br.com.runthebank.persondata.repository.PersonRepository;
import br.com.runthebank.persondata.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    public Account createAccount(String documentNumber, AccountDto accountDto) {
        Optional<Person> optionalPerson = personRepository.findByDocumentNumber(documentNumber);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();

            Account account = new Account();

            account.setBranchCode(AccountUtils.generateRandomBranchCode());
            account.setAccountCode(AccountUtils.generateAccountCode());
            account.setBankCode(AccountUtils.generateBankCode());
            account.setBalance(accountDto.getBalance());
            account.setAccountType(accountDto.getAccountType());
            account.setSalesChannel(accountDto.getSalesChannel());
            account.setOccupation(accountDto.getOccupation());
            account.setMonthlyIncome(accountDto.getMonthlyIncome());
            account.setPerson(person);

            return accountRepository.save(account);
        } else {
            throw new NotFoundException("Client not found");
        }
    }

    public List<AccountGetResponseDto> getAccountsByPersonDocumentNumber(String documentNumber) {
        Optional<Person> optionalPerson = personRepository.findByDocumentNumber(documentNumber);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            List<Account> accounts = accountRepository.findByPerson(person);

            // Converta cada Account para AccountOnlyDto
            return accounts.stream().map(this::convertToDto).collect(Collectors.toList());
        } else {
            throw new NotFoundException("Customer deleted successfully");
        }
    }

    public void deleteAccount(int branchCode) {
        Account account = accountRepository.findByBranchCode(branchCode)
                .orElseThrow(() -> new NotFoundException("Account or branch not found"));
        accountRepository.delete(account);
    }

    private AccountGetResponseDto convertToDto(Account account) {
        AccountGetResponseDto dto = new AccountGetResponseDto();
        dto.setAccountId(account.getId());
        dto.setBranchCode(account.getBranchCode());
        dto.setAccountCode(account.getAccountCode());
        dto.setBankCode(account.getBankCode());
        dto.setBalance(account.getBalance());
        return dto;
    }

}
