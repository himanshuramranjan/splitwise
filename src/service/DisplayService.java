package service;

import models.User;

import java.util.Map;

public class DisplayService {
    public void showBalances(Map<String, Map<String, Double>> balances, Map<String, User> userMap) {
        for (String userId1 : balances.keySet()) {
            Map<String, Double> balance = balances.get(userId1);
            if (balance == null) continue;
            for (Map.Entry<String, Double> entry : balance.entrySet()) {
                if (entry.getValue() > 0) {
                    User u1 = userMap.get(userId1);
                    User u2 = userMap.get(entry.getKey());
                    if (u1 != null && u2 != null)
                        System.out.println(u1.getName() + " owes " + u2.getName() + ": " + entry.getValue());
                }
            }
        }
    }
}
