package strategies;

import models.Expense;
import models.Split;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public boolean validate(Expense expense) {
        double totalPercentage = 0.0;
        for(Split split : expense.getSplits()) {
            totalPercentage += split.getAmount();
        }
        return totalPercentage == expense.getAmount();
    }
}
