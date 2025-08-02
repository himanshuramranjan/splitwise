package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    private String name;
    private List<User> users;
    private List<String> expenseIds;
    private final BalanceSheet balanceSheet;
    private final Map<String, Expense> expenseMap;

    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.expenseIds = new ArrayList<>();
        this.balanceSheet = new BalanceSheet();
        this.expenseMap = new HashMap<>();
    }

    public List<User> getUsers() {
        return users;
    }
    public void addUsers(User user) {
        this.users.add(user);
    }
    public List<String> getExpenseIds() {
        return expenseIds;
    }
    public void addExpenseId(String expenseId) {
        this.expenseIds.add(expenseId);
    }
    public String getName() {
        return name;
    }
    public BalanceSheet getBalanceSheet() { return this.balanceSheet; }
    public Map<String, Expense> getExpenseMap() {
        return expenseMap;
    }
}
