package models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<User> users;
    private List<String> expenseIds;

    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.expenseIds = new ArrayList<>();
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
}
