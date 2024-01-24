package br.com.runthebank.persondata.controller;

import br.com.runthebank.persondata.model.dto.AccountDto;
import br.com.runthebank.persondata.model.dto.AccountGetResponseDto;
import br.com.runthebank.persondata.model.dto.AccountResponseDto;
import br.com.runthebank.persondata.mapper.AccountMapper;
import br.com.runthebank.persondata.model.entity.Account;
import br.com.runthebank.persondata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/{documentNumber}")
    public ResponseEntity<AccountResponseDto> createAccount(@PathVariable String documentNumber, @RequestBody AccountDto accountDto) {
        Account createdAccount = accountService.createAccount(documentNumber, accountDto);
        AccountResponseDto responseDto = accountMapper.toAccountResponseDto(createdAccount);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/person/{documentNumber}")
    public ResponseEntity<List<AccountGetResponseDto>> getAccountsByPersonDocumentNumber(@PathVariable String documentNumber) {
        List<AccountGetResponseDto> accountDtos = accountService.getAccountsByPersonDocumentNumber(documentNumber);
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{branchCode}")
    public ResponseEntity<String> deleteAccount(@PathVariable int branchCode) {
        accountService.deleteAccount(branchCode);
        return ResponseEntity.ok("Account deleted successfully");
    }

}
