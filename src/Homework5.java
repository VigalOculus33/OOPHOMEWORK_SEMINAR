package ru.gb.lesson1;

interface Account {
    double getAmount();
    void put(double amount);
    void take(double amount);
}


package ru.gb.lesson1;

public class SimpleAccount implements Account {
    private double balance;

    public SimpleAccount(double initialBalance) {
        this.balance = initialBalance > 0 ? initialBalance : 0;
    }

    @Override
    public double getAmount() {
        return this.balance;
    }

    @Override
    public void put(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    @Override
    public void take(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        }
    }
}

