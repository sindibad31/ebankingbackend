package fr.bank.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SA")
@Data @NoArgsConstructor @AllArgsConstructor
public class SavingsAccount extends BankAccount {
    private double interesteRate;
}
