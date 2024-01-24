package br.com.runthebank.persondata.model.dto;

import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private String accountType;
    private String salesChannel;
    private String occupation;
    private Double monthlyIncome;
    private Double balance;
    private String documentNumber;
    private Boolean active;
}
