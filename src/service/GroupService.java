package service;

import models.Expense;
import models.Group;
import models.User;
import strategies.SplitStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService {
    private final Map<String, Group> groups;
    private final Map<String, ExpenseService> groupExpenseServices;

    public GroupService() {
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

    public Expense addExpense(String groupName, User paidBy, List<User> involvedUsers, double totalAmount, SplitStrategy splitStrategy, Object extraData) {
        Group group = groups.get(groupName);
        ExpenseService service = groupExpenseServices.get(groupName);
        if(group != null && service != null) {
            Expense expense = service.addExpense(paidBy, involvedUsers, totalAmount, splitStrategy, extraData);
            group.addExpenseId(expense.getId());
            return expense;
        }
        return null;
    }

    public Expense updateExpense(String groupName, String expenseId, List<User> involvedUsers, double newAmount, SplitStrategy splitStrategy, Object extraData) {
        ExpenseService service = groupExpenseServices.get(groupName);
        if(service != null) {
            return service.updateExpense(expenseId, involvedUsers, newAmount, splitStrategy, extraData);
        }
        return null;
    }

    public void deleteExpense(String groupName, String expenseId) {
        ExpenseService service = groupExpenseServices.get(groupName);
        if(service == null) {
            throw new IllegalArgumentException("Group not found " + groupName);
        }
        service.deleteExpense(expenseId);
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
