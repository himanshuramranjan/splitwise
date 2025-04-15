package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceSheet {

//    Map<payerId, Map<owedToId, amount>>
    private final Map<String, Map<String, Double>> balances = new HashMap<>();

    public void updateBalance(User paidBy, List<Split> splits) {
        for (Split split : splits) {
            if (split.getUser().getUserId().equals(paidBy.getUserId())) continue;
            balances.putIfAbsent(split.getUser().getUserId(), new HashMap<>());
            balances.putIfAbsent(paidBy.getUserId(), new HashMap<>());

            Map<String, Double> owedBy = balances.get(split.getUser().getUserId());
            owedBy.put(paidBy.getUserId(), owedBy.getOrDefault(paidBy.getUserId(), 0.0) + split.getAmount());

            Map<String, Double> owedTo = balances.get(paidBy.getUserId());
            owedTo.put(split.getUser().getUserId(), owedTo.getOrDefault(split.getUser().getUserId(), 0.0) - split.getAmount());
        }
    }

    public void reverseBalance(User paidBy, List<Split> splits) {
        for (Split split : splits) {
            if (split.getUser().getUserId().equals(paidBy.getUserId())) continue;

            Map<String, Double> owedBy = balances.get(split.getUser().getUserId());
            if (owedBy != null) {
                owedBy.put(paidBy.getUserId(), owedBy.getOrDefault(paidBy.getUserId(), 0.0) - split.getAmount());
            }

            Map<String, Double> owedTo = balances.get(paidBy.getUserId());
            if (owedTo != null) {
                owedTo.put(split.getUser().getUserId(), owedTo.getOrDefault(split.getUser().getUserId(), 0.0) + split.getAmount());
            }
        }
    }

    public Map<String, Map<String, Double>> getBalances() {
        return balances;
    }
}
