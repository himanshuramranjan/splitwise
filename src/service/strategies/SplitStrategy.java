package strategies;

import models.Expense;
import models.Split;

import java.util.List;

public interface SplitStrategy<T> {
    List<Split> calculateSplit(Expense expense, T extraData);
}
