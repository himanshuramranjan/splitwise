package strategies;

import models.Split;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy<List<Double>> {
    @Override
    public List<Split> calculateSplit(List<User> involvedUsers, double totalAmount, List<Double> exactAmounts) {

        if (exactAmounts.size() != involvedUsers.size()) {
            throw new IllegalArgumentException("Amounts count must match users count");
        }

        double sum = 0;
        for (Double val : exactAmounts) {
            sum += val;
        }
        if (Math.abs(sum - totalAmount) > 0.01) {
            throw new IllegalArgumentException("Exact amounts do not sum up to total amount.");
        }

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < involvedUsers.size(); i++) {
            splits.add(new Split(involvedUsers.get(i), exactAmounts.get(i)));
        }
        return splits;
    }
}
