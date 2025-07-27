package strategies;

import models.Split;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy<Void> {

    @Override
    public List<Split> calculateSplit(List<User> involvedUsers, double totalAmount, Void extraData) {
        double splitAmount = totalAmount / involvedUsers.size();
        List<Split> splits = new ArrayList<>();
        for (User user : involvedUsers) {
            splits.add(new Split(user, splitAmount));
        }
        return splits;
    }
}
