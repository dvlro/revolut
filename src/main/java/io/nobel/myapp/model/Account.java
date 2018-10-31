package io.nobel.myapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by petre on 10/31/2018.
 */
public class Account {

    private final String email;
    private BigDecimal balance;
    private final LocalDate dateCreated;

    public Account(
            @JsonProperty("email") String email,
            @JsonProperty("balance") BigDecimal balance,
            @JsonProperty("dateCreated") LocalDate dateCreated) {
        super();
        this.email = email;
        this.balance = balance;
        this.dateCreated = dateCreated;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    private static final TypeReference<Account> typeRef = new TypeReference<Account>() {};

    public static TypeReference<Account> typeRef() {
        return typeRef;
    }

    private static final TypeReference<List<Account>> listTypeRef = new TypeReference<List<Account>>() {};

    public static TypeReference<List<Account>> listTypeRef() {
        return listTypeRef;
    }
}
