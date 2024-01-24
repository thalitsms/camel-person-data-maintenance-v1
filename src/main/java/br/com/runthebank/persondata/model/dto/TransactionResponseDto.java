package br.com.runthebank.persondata.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponseDto {
    private Long transactionId;
    private Double amount;
    private LocalDateTime timestamp;
    private String status;
}
