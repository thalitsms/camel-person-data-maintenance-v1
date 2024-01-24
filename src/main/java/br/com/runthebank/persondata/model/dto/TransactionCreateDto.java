package br.com.runthebank.persondata.model.dto;

import lombok.Data;

@Data
public class TransactionCreateDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;

}
