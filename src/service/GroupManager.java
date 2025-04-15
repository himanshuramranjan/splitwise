package service;

import models.Expense;
import models.Group;
import models.User;

import java.util.HashMap;
import java.util.Map;

public class GroupManager {
    private final Map<String, Group> groups;
    private final Map<String, ExpenseService> groupExpenseServices;

    public GroupManager() {
        this.groups = new HashMap<>();
        this.groupExpenseServices = new HashMap<>();
    }

    public Group createGroup(String groupName) {
        Group group = new Group(groupName);
        groups.put(groupName, group);
        groupExpenseServices.put(groupName, new ExpenseService());
        return group;
    }

    public void addUser(String groupName, User user) {
        Group group = groups.get(groupName);
        if(group != null) {
            group.addUsers(user);
        }
    }

    public void addExpense(String groupName, Expense expense) {
        Group group = groups.get(groupName);
        ExpenseService service = groupExpenseServices.get(groupName);
        if(group != null && service != null && expense.validate()) {
            group.addExpenseId(expense.getId());
            service.addExpense(expense);
        }
    }

    public void updateExpense(String groupName, Expense updatedExpense) {
        ExpenseService service = groupExpenseServices.get(groupName);
        if(service != null && updatedExpense.validate()) {
            service.updateExpense(updatedExpense);
        }
    }

    public void showBalances(String groupName) {
        Group group = groups.get(groupName);
        ExpenseService expenseService = groupExpenseServices.get(groupName);

        if(expenseService != null && group != null) {
            Map<String, User> userMap = new HashMap<>();
            for (User user : group.getUsers()) {
                userMap.put(user.getUserId(), user);
            }
            new DisplayService().showBalances(expenseService.getBalances(), userMap);
        }
    }

    public void simplifyDebt(String groupName) {
        ExpenseService service = groupExpenseServices.get(groupName);
        if(service != null) {
            new DebtSimplifier().simplify(service.getBalances());
        }
    }
}
