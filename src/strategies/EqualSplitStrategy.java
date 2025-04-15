package strategies;

import models.Expense;
import models.Split;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public boolean validate(Expense expense) {
        int numberOfSplits = expense.getSplits().size();
        double expectedSplit = expense.getAmount() / numberOfSplits;
        for(Split split : expense.getSplits()) {
            if(Math.abs(split.getAmount() - expectedSplit) > 0.01) return false;
        }
        return true;
    }
}
