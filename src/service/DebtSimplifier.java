package service;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DebtSimplifier {

    public void simplify(Map<String, Map<String, Double>> balances) {
        PriorityQueue<UserBalance> positive = new PriorityQueue<>((a, b) -> Double.compare(b.amount, a.amount));
        PriorityQueue<UserBalance> negative = new PriorityQueue<>((a, b) -> Double.compare(a.amount, b.amount));

        Map<String, Double> netBalance = new HashMap<>();

        for (Map.Entry<String, Map<String, Double>> entry : balances.entrySet()) {
            double total = 0;
            for (double val : entry.getValue().values()) {
                total += val;
            }
            netBalance.put(entry.getKey(), total);
        }

        for (Map.Entry<String, Double> entry : netBalance.entrySet()) {
            if (entry.getValue() > 0.01) {
                positive.add(new UserBalance(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() < -0.01) {
                negative.add(new UserBalance(entry.getKey(), entry.getValue()));
            }
        }

        while (!positive.isEmpty() && !negative.isEmpty()) {
            UserBalance creditor = positive.poll();
            UserBalance debtor = negative.poll();

            double amount = Math.min(creditor.amount, -debtor.amount);
//            System.out.println(debtor. + " pays " + creditor.userId + ": " + amount);

            if (creditor.amount - amount > 0.01) {
                positive.add(new UserBalance(creditor.userId, creditor.amount - amount));
            }
            if (debtor.amount + amount < -0.01) {
                negative.add(new UserBalance(debtor.userId, debtor.amount + amount));
            }
        }
    }

    static class UserBalance {
        String userId;
        double amount;

        UserBalance(String userId, double amount) {
            this.userId = userId;
            this.amount = amount;
        }
    }
}
