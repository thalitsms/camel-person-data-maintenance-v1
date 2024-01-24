package br.com.runthebank.persondata.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateResponse {
    private String message;

    public UpdateResponse(String message) {
        this.message = message;
    }

}
