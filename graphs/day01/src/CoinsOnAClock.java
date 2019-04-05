import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/*
Strategy:

    We can explore the entire solution space (similar to a tree) if, at every stage,
    we attempt to place every coin into the current spot. Since there are only 12
    spots on the clock, it is computationally okay to take this approach as bad branches
    will terminate quickly.

 */


public class CoinsOnAClock {
    // Our recursive back-tracking solution:
    private static void solve(char[] clock,
                              int i,
                              Map<Character, Integer> coinsToCounts,
                              Map<Character, Integer> coinsToValues,
                              List<char[]> solutions) {

        // Check if we have a filled clock
        boolean finished = true;
        for (int v : coinsToCounts.values())
            if (v != 0)
                finished = false;

        // If we do, make a copy and exit out of the current loop iteration.
        if (finished) {
            char[] copy = new char[clock.length];
            System.arraycopy(clock, 0, copy, 0, clock.length);
            solutions.add(copy);
            return;
        }


        // Verify that we haven't overrun the clock and that the current iteration is not a repeat
        if (clock[i] != '.')
            return;

        // Explore a new bracnh of the search space by testing each coin in the current slot
        for (Map.Entry<Character, Integer> e : coinsToCounts.entrySet()) {
            if (e.getValue() > 0) {

                int coinVal = coinsToValues.get(e.getKey());
                clock[i] = e.getKey();  // Place the coin
                int index = (i + coinVal) % clock.length;  // Calculate the next index
                e.setValue(e.getValue() - 1);  // decrease the remaining number of coins
                solve(clock, index, coinsToCounts, coinsToValues, solutions);  // recursively solve the problem
                clock[i] = '.';  // Backtrack
                e.setValue(e.getValue() + 1);

            }
        }
    }




    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> result = new ArrayList<>();

        // Putting the appropriate numbers of coins into a hashmap for later use
        Map<Character, Integer> coinsToCounts = new HashMap<>();
        coinsToCounts.put('p', pennies);
        coinsToCounts.put('n', nickels);
        coinsToCounts.put('d', dimes);

        // Mapping the appropriate value to each coin.
        Map<Character, Integer> coinsToValues = new HashMap<>();
        coinsToValues.put('p', 1);
        coinsToValues.put('n', 5);
        coinsToValues.put('d', 10);

        // Fill the clock with dummy data.
        char[] clock = new char[hoursInDay];
        for (int i = 0; i < clock.length; i++)
            clock[i] = '.';

        solve(clock, 0, coinsToCounts, coinsToValues, result);

        return result;
    }
}
