package service;

import models.BalanceSheet;
import models.Expense;

import java.util.HashMap;
import java.util.Map;

public class ExpenseService {
    private final BalanceSheet balanceSheet;
    private final Map<String, Expense> expenseMap;

    public ExpenseService() {
        this.balanceSheet = new BalanceSheet();
        this.expenseMap = new HashMap<>();
    }

    public void addExpense(Expense expense) {
        expenseMap.put(expense.getId(), expense);
        balanceSheet.updateBalance(expense.getPaidBy(), expense.getSplits());
    }

    public void updateExpense(Expense expense) {
        Expense oldExpense = expenseMap.get(expense.getId());
        if(oldExpense != null) {
            balanceSheet.reverseBalance(oldExpense.getPaidBy(), oldExpense.getSplits());
        }

        expenseMap.put(expense.getId(), expense);
        balanceSheet.updateBalance(expense.getPaidBy(), expense.getSplits());
    }

    public Map<String, Map<String, Double>> getBalances() {
        return balanceSheet.getBalances();
    }
}
