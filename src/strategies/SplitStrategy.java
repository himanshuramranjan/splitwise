package strategies;

import models.Expense;

public interface SplitStrategy {
    boolean validate(Expense expense);
}
