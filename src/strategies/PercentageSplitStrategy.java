package strategies;

import models.Split;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy<List<Double>> {

    @Override
    public List<Split> calculateSplit(List<User> involvedUsers, double totalAmount, List<Double> percentages) {
        if (percentages.size() != involvedUsers.size()) {
            throw new IllegalArgumentException("Percentages count must match users count");
        }

        double totalPercentage = 0;
        for (Double p : percentages) {
            totalPercentage += p;
        }
        if (Math.abs(totalPercentage - 100.0) > 0.001) {
            throw new IllegalArgumentException("Percentages do not add up to 100.");
        }

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < involvedUsers.size(); i++) {
            splits.add(new Split(involvedUsers.get(i), totalAmount * (percentages.get(i) / 100)));
        }
        return splits;
    }
}
