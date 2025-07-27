package strategies;

import models.Split;
import models.User;

import java.util.List;

public interface SplitStrategy<T> {
    List<Split> calculateSplit(List<User> involvedUsers, double totalAmount, T extraData);
}
