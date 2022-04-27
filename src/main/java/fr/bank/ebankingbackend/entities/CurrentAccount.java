package fr.bank.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class CurrentAccount extends BankAccount {
    private double overDraft;
}
