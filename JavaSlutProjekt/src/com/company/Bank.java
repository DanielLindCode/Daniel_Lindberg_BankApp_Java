package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bank {

    //States
    private String Name;
    private List<Customer> customers;

    //Constructor
    public Bank(String bankName) throws IOException {
        this.Name = bankName;
        this.customers = new ArrayList<>();
    }

    //Getters & Setters
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Customer> getCustomersList() {
        return customers;
    }


    //Behavior

    // Vilket index i listan kunden har
    public Customer findCustomerAt(int index) {
        return customers.get(index);
    }

    // Hämtar listan av kunder
    public List<String> getCustomers() {
        List<String> cList = new ArrayList<>();

        for (Customer customer : customers) {
            cList.add(customer.getpNr()
                    + " " +
                    customer.getfName()
                    + " " +
                    customer.getlName());
        }
        return cList;
    }

    // Hämtar listan av konton för en viss kund baserat på pNr
    public List<String> getAccounts(Long pNr) {

        int targetIndexNr = findCustomer(pNr);
        if (targetIndexNr == -1) {
            return null;
        }

        List<String> accountList = new ArrayList<>();

        for (var account : getCustomersList().get(targetIndexNr).getAccounts()) {

            accountList.add(
                    "\n" + "Account Number: " + account.getAccountNumber() +
                            "\n" + "Account Type: " + account.getAccountType() +
                            "\n" + "Saldo: " + account.getSaldo() + "\n\n");

        }

        return accountList;
    }

    // Lägger till ny kund
    public boolean addCustomer(String fName, String lName, Long pNr) {
        int targetIndexNr = findCustomer(pNr);
        if (targetIndexNr == -1) {
            customers.add(new Customer(fName, lName, pNr));
        }
        return targetIndexNr == -1;
    }

    // Hämtar kund baserar på pNr
    public List<String> getCustomer(long pNr) {

        int targetIndexNr = findCustomer(pNr);
        if (targetIndexNr == -1) return null;

        List<String> cInfo = new ArrayList<>();

        cInfo.add(
                "Customer person number: " + customers.get(targetIndexNr).getpNr()
                        + "\n" +
                        "Customer Name: " + customers.get(targetIndexNr).getfName() + " " + customers.get(targetIndexNr).getlName()
                        + "\n");

        return cInfo;
    }


    // Ändrar namn på kund om den hittar kund.
    public boolean changeCustomerName(String fName, String lName, long pNr) {

        int targetIndexNr = findCustomer(pNr);
        if (targetIndexNr == -1) {
            return false;
        }
        customers.get(targetIndexNr).setfName(fName);
        customers.get(targetIndexNr).setlName(lName);

        return true;
    }

    // Tar bort en kund
    public List<String> removeCustomer(long pNr) {
        int targetIndexNr = findCustomer(pNr);
        if (targetIndexNr == -1) return null;

        List<String> removedAccountInfo = new ArrayList<>();

        for (int i = 0; i < customers.get(targetIndexNr).getAccounts().size(); i++) {
            removedAccountInfo.add(
                    "Account Removed: "
                            +"\n"+
                           "Account Type: " + customers.get(targetIndexNr).getAccounts().get(i).getAccountType()
                            +"\n"+
                            "Account Number: " + customers.get(targetIndexNr).getAccounts().get(i).getAccountNumber()
                            +"\n"+
                            "Money back: " + customers.get(targetIndexNr).getAccounts().get(i).getSaldo()
                            +"\n\n");
        }

        customers.remove(targetIndexNr);

        return removedAccountInfo;

    }

    // För att ge ett startnr till mina konton
    private long nextAccountNr = 1001;

    // Lägger till ett konto
    public long addAccount(long pNr, String AccountType) {

        int targetIndexNr = findCustomer(pNr);
        if (targetIndexNr == -1) {
            return -1;
        }

        Customer currentCustomer = customers.get(targetIndexNr);

        currentCustomer.getAccounts().add(new Account(AccountType, nextAccountNr));

        return nextAccountNr++;
    }

    // Hämtar konto
    public String getAccount(long pNr, long accountNr){
        int customerIndex = findCustomer(pNr);
        if (customerIndex == -1) return "Failed to find customer!";

        String getAccount = "No account found";

        for (int i = 0; i < customers.get(customerIndex).getAccounts().size(); i++) {
            if (customers.get(customerIndex).getAccounts().get(i).getAccountNumber()==accountNr){
                getAccount = "Account number: " + accountNr
                                + "\n" +
                                "Balance: "+ customers.get(customerIndex).getAccounts().get(i).getSaldo()
                                + "\n" +
                                "Account type: " + customers.get(customerIndex).getAccounts().get(i).getAccountType();
            }
        }

        return getAccount;
    }

    // Stänger ett konto
    public String closeAccount(long pNr, long accountNr) {

        int targetIndexNr = findCustomer(pNr);
        if (targetIndexNr == -1) { return "Cant find account"; }

        int saldoBack = customers.get(targetIndexNr).getAccount(accountNr).getSaldo();

        customers.get(targetIndexNr).getAccounts().remove(targetIndexNr);

        return "Account Closed. You get :" + saldoBack + ":- back in cash.";
    }

    // kollar om det är ok att dra pengar
    public boolean withdraw(long pNr, long accountNr, int saldo) {
        for (var customer : customers) {
            if (pNr == customer.getpNr()) {
                customer.withdrawsetsaldo(accountNr, saldo);
                return true;
            }
        }
        return false;
    }

    // Kollar om det är ok att lägga till pengar
    public boolean deposit(long pNr, long accountNr, int saldo) {
        for (var customer : customers) {
            if (pNr == customer.getpNr()) {
                customer.depositsetsaldo(accountNr, saldo);
                return true;
            }
        }
        return false;
    }

    // I need this method to use in other methods
    public int findCustomer(long pNr) {

        int targetIndexNr = -1;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getpNr() == pNr) {
                targetIndexNr = i;
            }
        }
        return targetIndexNr;
    }

    // Metod för att printa kunder till textfil
    public void printCustomers() throws IOException {

        FileWriter writer = new FileWriter("output.txt");

        for (var customer : customers) {

            writer.write(customer.getfName()
                    + " " + customer.getlName()
                    + " " + customer.getpNr()
                    + " " + System.lineSeparator());
        }
        writer.close();
    }
}
