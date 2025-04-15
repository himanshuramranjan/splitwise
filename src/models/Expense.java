package models;

import strategies.SplitStrategy;

import java.util.List;
import java.util.UUID;

public class Expense {
    private final String id;
    private final User paidBy;
    private double amount;
    private final List<Split> splits;
    private final SplitStrategy splitStrategy;

    public Expense(User paidBy, double amount, List<Split> splits, SplitStrategy splitStrategy) {
        this.id = UUID.randomUUID().toString();
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
        this.splitStrategy = splitStrategy;
    }

    public boolean validate() {
        return this.splitStrategy.validate(this);
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

    public SplitStrategy getSplitStrategy() {
        return splitStrategy;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
