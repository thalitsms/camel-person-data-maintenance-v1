package br.com.runthebank.persondata.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int branchCode;

    @Column(nullable = false)
    private int accountCode;

    @Column(nullable = false)
    private int bankCode;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private String salesChannel;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private Double monthlyIncome;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;


    public void setBalance(Double balance) {
        this.balance = balance;
        this.active = (balance != null && balance > 0);
    }

    public Account() {
        // Gera valores aleatórios para agência e número da conta
        Random random = new Random();
        this.bankCode = random.nextInt(10000); // Até 4 dígitos
        this.branchCode = random.nextInt(10000); // Até 4 dígitos
        this.accountCode = random.nextInt(1000000); // Até 6 dígitos
    }
}
