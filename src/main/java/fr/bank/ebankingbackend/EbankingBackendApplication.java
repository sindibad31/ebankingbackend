package fr.bank.ebankingbackend;

import fr.bank.ebankingbackend.entities.AccountOperation;
import fr.bank.ebankingbackend.entities.CurrentAccount;
import fr.bank.ebankingbackend.entities.Customer;
import fr.bank.ebankingbackend.entities.SavingsAccount;
import fr.bank.ebankingbackend.enums.AccountStatus;
import fr.bank.ebankingbackend.enums.OperationType;
import fr.bank.ebankingbackend.repositories.AccountOperationRepository;
import fr.bank.ebankingbackend.repositories.BankAccountRepository;
import fr.bank.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Aziz", "Iyad", "Ali", "Zineb").forEach(name -> {
                Customer customer = new Customer();
                customer.setNom(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingsAccount savingsAccount = new SavingsAccount();
                savingsAccount.setId(UUID.randomUUID().toString());
                savingsAccount.setBalance(Math.random() * 90000);
                savingsAccount.setCreatedAt(new Date());
                savingsAccount.setStatus(AccountStatus.CREATED);
                savingsAccount.setCustomer(customer);
                savingsAccount.setInteresteRate(Math.random() + 4);
                bankAccountRepository.save(savingsAccount);
            });
            
            bankAccountRepository.findAll().forEach(bankAccount -> {
                for(int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setBankAccount(bankAccount);
                    accountOperation.setAmount(Math.random() * 3000);
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setOperationType(Math.random() < 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperationRepository.save(accountOperation);
                }
            });
            
        };
    }

}
