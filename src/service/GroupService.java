package service;

import models.Expense;
import models.Group;
import models.Split;
import models.User;
import service.strategies.SplitStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService {
    private final Map<String, Group> groups;

    public GroupService() {
        this.groups = new HashMap<>();
    }

    public Group createGroup(String groupName) {
        Group group = new Group(groupName);
        groups.put(groupName, group);
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

        if(group != null) {
            Expense expense = new Expense(paidBy, totalAmount, involvedUsers);
            List<Split> splits = splitStrategy.calculateSplit(expense, extraData);
            expense.setSplits(splits);

            group.getBalanceSheet().updateBalance(expense.getPaidBy(), expense.getSplits());
            group.getExpenseMap().put(expense.getId(), expense);

            return expense;
        }
        return null;
    }

    public Expense updateExpense(String groupName, String expenseId, List<User> involvedUsers, double newAmount, SplitStrategy splitStrategy, Object extraData) {
        Group group = groups.get(groupName);
        Expense oldExpense = group.getExpenseMap().get(expenseId);

        if(oldExpense != null) {
            group.getBalanceSheet().reverseBalance(oldExpense.getPaidBy(), oldExpense.getSplits());

            oldExpense.setInvolvedUsers(involvedUsers);
            oldExpense.setAmount(newAmount);

            List<Split> splits = splitStrategy.calculateSplit(oldExpense, extraData);
            oldExpense.setSplits(splits);

            group.getBalanceSheet().updateBalance(oldExpense.getPaidBy(), splits);
            return oldExpense;
        } else {
            throw new IllegalArgumentException("Expense not found : " + expenseId);
        }
    }

    public void deleteExpense(String groupName, String expenseId) {
        Group group = groups.get(groupName);
        Expense expense = group.getExpenseMap().get(expenseId);

        group.getBalanceSheet().reverseBalance(expense.getPaidBy(), expense.getSplits());
        group.getExpenseMap().remove(expenseId);
    }

    public void showBalances(String groupName) {
        Group group = groups.get(groupName);

        if(group != null) {
            Map<String, User> userMap = new HashMap<>();
            for (User user : group.getUsers()) {
                userMap.put(user.getUserId(), user);
            }
            new DisplayService().showBalances(group.getBalanceSheet().getBalances(), userMap);
        }
    }

    public void simplifyDebt(String groupName) {
        Group group = groups.get(groupName);
        DebtSimplifier.simplify(group.getBalanceSheet().getBalances());
    }
}
