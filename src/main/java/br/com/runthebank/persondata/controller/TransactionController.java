package br.com.runthebank.persondata.controller;

import br.com.runthebank.persondata.model.dto.TransactionCreateDto;
import br.com.runthebank.persondata.model.dto.TransactionResponseDto;
import br.com.runthebank.persondata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionCreateDto dto) {
        TransactionResponseDto responseDto = transactionService.createTransaction(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/reverse/{transactionId}")
    public ResponseEntity<TransactionResponseDto> reverseTransaction(@PathVariable Long transactionId) {
        TransactionResponseDto responseDto = transactionService.reverseTransaction(transactionId);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }
    }

