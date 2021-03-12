package model;

import java.util.UUID;

public class Expense implements Comparable<Expense> {
    public String id;
    public String month;
    public int day;
    public int year;
    public String name;
    public double amount;
    public String budgetId;

    public Expense() {}

    public Expense(String id, String month, int day, int year, String name, double amount, String budgetId) {
        this.id = id;
        this.month = month;
        this.day = day;
        this.year = year;
        this.name = name;
        this.amount = amount;
        this.budgetId = budgetId;
    }

    public void createId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        String m = month.substring(0, 1).toUpperCase() + month.substring(1);
        return m + " " + day + ", " + year + " - " + name + ": " + String.format("$%.2f", amount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;

        Expense o = (Expense) other;

        return this.id.equals(o.id);
    }

    @Override
    public int compareTo(Expense o) {
        return this.day - o.day;
    }
}
