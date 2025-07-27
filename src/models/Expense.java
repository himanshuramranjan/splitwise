package models;

import java.util.List;
import java.util.UUID;

public class Expense {
    private final String id;
    private final User paidBy;
    private double amount;
    private List<User> involvedUsers;
    private List<Split> splits;

    public Expense(User paidBy, double amount, List<User> involvedUsers) {
        this.id = UUID.randomUUID().toString();
        this.paidBy = paidBy;
        this.amount = amount;
        this.involvedUsers = involvedUsers;
    }

    public double getAmount() {
        return amount;
    }
    public String getId() {
        return id;
    }
    public User getPaidBy() {
        return paidBy;
    }
    public List<Split> getSplits() {
        return splits;
    }
    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }
    public List<User> getInvolvedUsers() {
        return involvedUsers;
    }

    public void setInvolvedUsers(List<User> involvedUsers) {
        this.involvedUsers = involvedUsers;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
