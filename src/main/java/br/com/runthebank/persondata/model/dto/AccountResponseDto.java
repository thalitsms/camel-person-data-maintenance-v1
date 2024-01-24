package br.com.runthebank.persondata.model.dto;

import lombok.Data;

@Data
public class AccountResponseDto {
    private int branchCode;
    private int accountCode;
    private int bankCode;
    private Double balance;
    private String accountType;
}
