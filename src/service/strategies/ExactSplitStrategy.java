package service.strategies;

import models.Expense;
import models.Split;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy<List<Double>> {
    @Override
    public List<Split> calculateSplit(Expense expense, List<Double> exactAmounts) {

        if (exactAmounts.size() != expense.getInvolvedUsers().size()) {
            throw new IllegalArgumentException("Amounts count must match users count");
        }

        double sum = 0;
        for (Double val : exactAmounts) {
            sum += val;
        }
        if (Math.abs(sum - expense.getAmount()) > 0.01) {
            throw new IllegalArgumentException("Exact amounts do not sum up to total amount.");
        }

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < expense.getInvolvedUsers().size(); i++) {
            splits.add(new Split(expense.getInvolvedUsers().get(i), exactAmounts.get(i)));
        }
        return splits;
    }
}
