package strategies;

import models.Expense;
import models.Split;

public class ExactSplitStrategy implements SplitStrategy {
    @Override
    public boolean validate(Expense expense) {
        double total = 0.0;
        for(Split split : expense.getSplits()) {
            total += split.getAmount();
        }
        return total == expense.getAmount();
    }
}
