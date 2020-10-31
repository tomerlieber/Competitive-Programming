import java.util.Arrays;

/*
    Given a set of coin values coins = {c1, c2, ..., ck} and a target sum of
    money n, our task is to form the sum n using as few coins as possible.
 */
public class CoinProblem {

    public static void main(String[] args) {

        int[] coins = {1, 3, 4};
        int sum = 11;

        // Finding an optimal solution
        iterativeSolution(coins, sum);

        // Counting the number of solutions
        System.out.println("The number of ways to produce the sum is: " + countWays(coins, sum));
    }

    private static int countWays(int[] coins, int sum) {
        int[] values = new int[sum + 1];
        return countWays(coins, sum, values);
    }

    private static int countWays(int[] coins, int sum, int[] count) {

        int m = 1000000007;
        // count[x] = the number of ways we can form the sum x
        count[0] = 1;

        for (int x = 1; x <= sum; x++) {
            for (int c : coins) {
                if (x - c >= 0) {
                    count[x] += count[x - c];
                    count[x] %= m;
                }
            }
        }

        return count[sum];
    }

    // Print the optimal solution and an example how such a solution can be constructed.
    private static void iterativeSolution(int[] coins, int sum) {

        // value[x] = the minimum number of coins required for a sum x.
        int[] values = new int[sum + 1];

        // first[x] = the first coin in an optimal solution.
        int[] first = new int[sum + 1];

        int result = iterativeSolution(coins, sum, values, first);

        System.out.println("An optimal solution: " + result);
        System.out.print("Example how such a solution can be constructed: ");
        while (sum > 0) {
            System.out.print(first[sum] + " ");
            sum -= first[sum];
        }
        System.out.println();
    }

    private static int iterativeSolution(int[] coins, int sum, int[] values, int[] first) {

        values[0] = 0;
        for (int x = 1; x <= sum; x++) {
            values[x] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (x - coin >= 0) {
                    values[x] = Math.min(values[x], values[x - coin] + 1);
                    first[x] = coin;
                }
            }
        }

        return values[sum];
    }

    // Print the optimal solution.
    private static void recursiveSolution(int[] coins, int sum) {

        int[] values = new int[sum + 1];
        Arrays.fill(values, -1);
        int result = recursiveSolution(coins, sum, values);
        System.out.println("Recursive Solution: " + result);
    }

    // This function use a technique called memorization.
    private static int recursiveSolution(int[] coins, int sum, int[] values) {

        // Base case - if n < 0, the value is very high, because it's impossible to form a negative sum of money.
        if (sum < 0) {
            return Integer.MAX_VALUE - 1;
        }

        // Base case - if n = 0, the value is 0, because no coins are needed to form empty sum.
        if (sum == 0) {
            return 0;
        }

        //
        if (values[sum] != -1) {
            return values[sum];
        }

        // Check all possibilities how to choose the first coin of the sum.
        int solution = Integer.MAX_VALUE;
        for (int coin : coins) {
            solution = Math.min(solution, recursiveSolution(coins, sum - coin, values) + 1);
        }

        values[sum] = solution;
        return solution;
    }
}
