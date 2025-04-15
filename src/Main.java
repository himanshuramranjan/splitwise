import models.Expense;
import models.Group;
import models.Split;
import models.User;
import service.GroupManager;
import strategies.EqualSplitStrategy;
import strategies.ExactSplitStrategy;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        GroupManager groupManager = new GroupManager();

        User u1 = new User("Alice", "alice@example.com");
        User u2 = new User("Bob", "bob@example.com");
        User u3 = new User("Charlie", "charlie@example.com");

        Group group = groupManager.createGroup("Trip to Goa");
        groupManager.addUser(group.getName(), u1);
        groupManager.addUser(group.getName(), u2);
        groupManager.addUser(group.getName(), u3);

        List<Split> splits1 = Arrays.asList(
                new Split(u1, 500),
                new Split(u2, 1000),
                new Split(u3, 0)
        );
        Expense expense1 = new Expense(u1, 1500, splits1, new ExactSplitStrategy());
        groupManager.addExpense("Trip to Goa", expense1);

        List<Split> splits2 = Arrays.asList(
                new Split(u1, 200),
                new Split(u2, 800),
                new Split(u3, 100)
        );
        Expense expense2 = new Expense(u2, 1000, splits2, new ExactSplitStrategy());
        groupManager.addExpense(group.getName(), expense2);

        groupManager.showBalances(group.getName());

        List<Split> updatedSplits = Arrays.asList(
                new Split(u1, 100),
                new Split(u2, 200),
                new Split(u3, 800)
        );
        Expense updatedExpense1 = new Expense(u2, 1100, updatedSplits, new ExactSplitStrategy());
        groupManager.updateExpense("Trip to Goa", updatedExpense1);

        System.out.println("\nUpdated Balances:");


        System.out.println("\nSimplified Debts:");
        groupManager.simplifyDebt("Trip to Goa");
        groupManager.showBalances("Trip to Goa");


    }
}