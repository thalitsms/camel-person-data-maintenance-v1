package br.com.runthebank.persondata.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateResponse {
    private String message;

    public CreateResponse(String message) {
        this.message = message;
    }

}
