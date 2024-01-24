package br.com.runthebank.persondata.mapper;

import br.com.runthebank.persondata.model.dto.AccountResponseDto;
import br.com.runthebank.persondata.model.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDto toAccountResponseDto(Account account) {
        AccountResponseDto responseDto = new AccountResponseDto();
        responseDto.setBranchCode(account.getBranchCode());
        responseDto.setAccountCode(account.getAccountCode());
        responseDto.setBankCode(account.getBankCode());
        responseDto.setBalance(account.getBalance());
        responseDto.setAccountType(account.getAccountType());
        return responseDto;
    }

    // Outros métodos de mapeamento podem ser adicionados aqui
}
