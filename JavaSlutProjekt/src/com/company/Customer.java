package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    //States
    private Long pNr;
    private String fName, lName;
    private List<Account> accounts;

    //Constructor
    public Customer(String firstName, String lastName, Long pNumber) {
        this.fName = firstName;
        this.lName = lastName;
        this.pNr = pNumber;
        this.accounts = new ArrayList<>();
    }

    //Getters & Setters
    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }
    public long getpNr() {
        return pNr;
    }
    public List<Account> getAccounts() {
        return accounts;
    }

    //Behavior

    // +Saldo
    public long depositsetsaldo(long accountnumber, int dSaldo) {
        for (var account : accounts) {
            if (accountnumber == account.getAccountNumber())
            {
                int oldSaldo = account.getSaldo();
                int newSaldo = oldSaldo + dSaldo;
                account.setSaldo(newSaldo);
                return newSaldo;
            }
        }
        return accountnumber;

    }
    // -Saldo
    public long withdrawsetsaldo(long accountNr, int wSaldo) {
        for (var account : accounts) {
            if (accountNr == account.getAccountNumber() && wSaldo <= account.getSaldo())
            {
                int oldSaldo = account.getSaldo();
                int newSaldo = oldSaldo - wSaldo;
                account.setSaldo(newSaldo);
                return newSaldo;
            }
        }
        return accountNr;
    }

    // Hittar konto baserat på kontoNr
    public Account getAccount(Long accountNr) {

        for (int i = 0; i < accounts.size(); i++) {

            if (accounts.get(i).getAccountNumber() == accountNr) {return accounts.get(i);}
        }
        return null;
    }


}