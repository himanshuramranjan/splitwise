import models.Expense;
import models.Group;
import models.User;
import service.GroupService;
import strategies.EqualSplitStrategy;
import strategies.ExactSplitStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        GroupService groupService = new GroupService();

        User u1 = new User("Alice", "alice@example.com");
        User u2 = new User("Bob", "bob@example.com");
        User u3 = new User("Charlie", "charlie@example.com");

        String tripToGoa = "Trip to Goa";
        Group group = groupService.createGroup(tripToGoa);
        groupService.addUser(group.getName(), u1);
        groupService.addUser(group.getName(), u2);
        groupService.addUser(group.getName(), u3);

        Expense expense = groupService.addExpense(tripToGoa, u1, List.of(u1, u2), 1500.0, new ExactSplitStrategy(), List.of(500.0, 1000.0));
        groupService.showBalances(group.getName());

        Expense expense2 = groupService.addExpense(group.getName(), u2, List.of(u1, u2, u3),1100.0, new ExactSplitStrategy(), List.of(200.0, 800.0, 100.0));
        groupService.showBalances(group.getName());

        groupService.updateExpense(group.getName(), expense.getId(), List.of(u1, u2, u3), 1200.0, new EqualSplitStrategy(), null);
        groupService.updateExpense(group.getName(), expense2.getId(), List.of(u1, u2, u3), 900.0, new EqualSplitStrategy(), null);


        System.out.println("\nUpdated Balances:");
        groupService.showBalances(tripToGoa);
        System.out.println("\nSimplified Debts:");

        groupService.simplifyDebt(tripToGoa);
        groupService.showBalances(tripToGoa);


    }
}