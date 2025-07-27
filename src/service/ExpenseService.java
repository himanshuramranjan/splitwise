package service;

import models.BalanceSheet;
import models.Expense;
import models.Split;
import models.User;
import strategies.SplitStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseService {
    private final BalanceSheet balanceSheet;
    private final Map<String, Expense> expenseMap;

    public ExpenseService() {
        this.balanceSheet = new BalanceSheet();
        this.expenseMap = new HashMap<>();
    }

    public Expense addExpense(User paidBy, List<User> involvedUsers, double totalAmount, SplitStrategy splitStrategy, Object extraData) {
        Expense expense = new Expense(paidBy, totalAmount, involvedUsers);
        List<Split> splits = splitStrategy.calculateSplit(expense.getInvolvedUsers(), expense.getAmount(), extraData);
        expense.setSplits(splits);

        expenseMap.put(expense.getId(), expense);
        balanceSheet.updateBalance(expense.getPaidBy(), expense.getSplits());

        return expense;
    }

    public Expense updateExpense(String expenseId, List<User> involvedUsers, double newAmount, SplitStrategy splitStrategy, Object extraData) {
        Expense oldExpense = expenseMap.get(expenseId);
        if(oldExpense != null) {
            balanceSheet.reverseBalance(oldExpense.getPaidBy(), oldExpense.getSplits());

            List<Split> splits = splitStrategy.calculateSplit(involvedUsers, newAmount, extraData);
            oldExpense.setSplits(splits);
            oldExpense.setInvolvedUsers(involvedUsers);
            oldExpense.setAmount(newAmount);

            balanceSheet.updateBalance(oldExpense.getPaidBy(), splits);
            return oldExpense;
        } else {
            throw new IllegalArgumentException("Expense not found : " + expenseId);
        }
    }

    public void deleteExpense(String expenseId) {
        Expense expense = expenseMap.get(expenseId);
        balanceSheet.reverseBalance(expense.getPaidBy(), expense.getSplits());

        expenseMap.remove(expenseId);
    }

    public Map<String, Map<String, Double>> getBalances() {
        return balanceSheet.getBalances();
    }
}
