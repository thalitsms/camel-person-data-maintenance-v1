package br.com.runthebank.persondata.model.dto;

import lombok.Data;

@Data
public class AccountGetResponseDto {
    private Long accountId;
    private int branchCode;
    private int accountCode;
    private int bankCode;
    private double balance;
    private boolean active;
}
