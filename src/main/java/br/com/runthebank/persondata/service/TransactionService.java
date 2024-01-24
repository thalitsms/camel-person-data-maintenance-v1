package br.com.runthebank.persondata.service;

import br.com.runthebank.persondata.exception.NotFoundException;
import br.com.runthebank.persondata.model.dto.TransactionCreateDto;
import br.com.runthebank.persondata.model.dto.TransactionResponseDto;
import br.com.runthebank.persondata.model.entity.Account;
import br.com.runthebank.persondata.model.entity.Transaction;
import br.com.runthebank.persondata.repository.AccountRepository;
import br.com.runthebank.persondata.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransactionResponseDto createTransaction(TransactionCreateDto dto) {
        Account fromAccount = accountRepository.findById(dto.getFromAccountId())
                .orElseThrow(() -> new NotFoundException("Source account not found"));
        Account toAccount = accountRepository.findById(dto.getToAccountId())
                .orElseThrow(() -> new NotFoundException("Target account not found"));

        if (fromAccount.getBalance() < dto.getAmount()) {
            throw new NotFoundException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - dto.getAmount());
        toAccount.setBalance(toAccount.getBalance() + dto.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Registrar a transação
        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(dto.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setTransactionType("TRANSFER");

        Transaction savedTransaction = transactionRepository.save(transaction);

        return convertToResponseDto(savedTransaction);
    }

    @Transactional
    public TransactionResponseDto reverseTransaction(Long transactionId) {
        Transaction originalTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));

        Account fromAccount = originalTransaction.getFromAccount();
        Account toAccount = originalTransaction.getToAccount();

        if (toAccount.getBalance() < originalTransaction.getAmount()) {
            throw new NotFoundException("Insufficient balance for chargeback");
        }

        fromAccount.setBalance(fromAccount.getBalance() + originalTransaction.getAmount());
        toAccount.setBalance(toAccount.getBalance() - originalTransaction.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction reversalTransaction = new Transaction();
        reversalTransaction.setFromAccount(toAccount);
        reversalTransaction.setToAccount(fromAccount);
        reversalTransaction.setAmount(originalTransaction.getAmount());
        reversalTransaction.setTimestamp(LocalDateTime.now());
        reversalTransaction.setTransactionType("CHARGEBACK");
        reversalTransaction.setRelatedTransactionId(originalTransaction.getId());

        Transaction savedReversalTransaction = transactionRepository.save(reversalTransaction);

        return convertToResponseDto(savedReversalTransaction);
    }

    private TransactionResponseDto convertToResponseDto(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setTransactionId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setTimestamp(transaction.getTimestamp());
        dto.setStatus(transaction.getTransactionType());
        return dto;
    }


}

