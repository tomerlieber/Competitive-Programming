/*
    Count the number of ways to tile the floor of size n x m using 1 x m size tiles.
    A tile can either be placed horizontally or vertically.
 */

public class CountingTilings {

    public static void main (String[] args) {

        int n = 7;
        int m = 4;
        System.out.println("Number of ways = " + countWays(n, m));
    }

    // function to count the total number of ways
    private static int countWays(int n, int m)
    {
        // Let count[i] denotes the number of ways to tile the floor using the first i rows.
        int count[] = new int[n + 1];

        // Base case - The number of ways to tile using 0 rows is 0.
        count[0] = 0;

        // The recursion case - Fill the table up to value n.
        int i;
        for (i = 1; i <= n; i++) {

            // recurrence relation
            if (i > m) {
                // If the number of rows is bigger than tile size, then we can place in the new row a new tile or we can
                // use the last m rows (including the new one) to place the tiles.
                count[i] = count[i - 1] + count[i - m];
            }
                // base cases
            // If the number of rows is smaller than the tile size, we can only place the tiles horizontally.
            else if (i < m || i == 1) {
                count[i] = 1;
            }
            // If the number of rows is equal to the number of column, we can place the tiles horizontally and vertically.
            else {
                count[i] = 2;
            }
        }

        // required number of ways
        return count[n];
    }
}
