package io.nobel.myapp.dao;

import io.nobel.myapp.common.Exceptions;
import io.nobel.myapp.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by petre on 10/31/2018.
 */
public class AccountDAO {

    private final static ConcurrentMap<String, Account> accountMap = new ConcurrentHashMap<>();

    public static Account create(String email, BigDecimal balance) {
        Account account = new Account(email, balance, LocalDate.now());
        // If we get a non null value that means the user already exists in the Map.
        if (null != accountMap.putIfAbsent(account.getEmail(), account)) {
            return null;
        }
        return account;
    }

    public static Account get(String email) {
        return accountMap.get(email);
    }

    // Alternate implementation to throw exceptions instead of return nulls for not found.
    public static Account getThrowNotFound(String email) {
        Account account = accountMap.get(email);
        if (null == account) {
            throw Exceptions.notFound(String.format("Account %s not found", email));
        }
        return account;
    }

    public static Account update(String email, BigDecimal balance) {
        // This means no account existed so update failed. return null
        Account account = accountMap.get(email);
        if(account != null)
            account.setBalance(balance);
        else
            return null;

        if (null == accountMap.replace(account.getEmail(), account)) {
            return null;
        }
        // Update succeeded return the account
        return account;
    }

    public static boolean delete(String email) {
        return null != accountMap.remove(email);
    }

    public static List<Account> listUsers() {
        return accountMap.values()
                .stream()
                .sorted(Comparator.comparing((Account a) -> a.getEmail()))
                .collect(Collectors.toList());
    }

    public static List<Account> transferMoney(String from, BigDecimal amount, String to) {
        Account fromAccount = get(from);
        if(fromAccount == null)
            return null;
        int res;

        res = fromAccount.getBalance().compareTo(amount); // compare bg1 with bg2
        Account toAccount = get(to);
        if(toAccount == null)
            return null;
        if( res == 1 ) {
            double newBalanceFromAcc = fromAccount.getBalance().doubleValue()-amount.doubleValue();
            fromAccount.setBalance(new BigDecimal(newBalanceFromAcc));
            accountMap.replace(fromAccount.getEmail(), fromAccount);

            double newBalanceToAcc = toAccount.getBalance().doubleValue()+amount.doubleValue();
            toAccount.setBalance(new BigDecimal(newBalanceToAcc));
            accountMap.replace(toAccount.getEmail(), toAccount);
            return listUsers();
        }else{
            return new ArrayList<>();
        }
    }
}
