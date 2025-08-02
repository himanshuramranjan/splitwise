package strategies;

import models.Expense;
import models.Split;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy<Void> {

    @Override
    public List<Split> calculateSplit(Expense expense, Void extraData) {
        double splitAmount = expense.getAmount() / expense.getInvolvedUsers().size();
        List<Split> splits = new ArrayList<>();
        for (User user : expense.getInvolvedUsers()) {
            splits.add(new Split(user, splitAmount));
        }
        return splits;
    }
}
