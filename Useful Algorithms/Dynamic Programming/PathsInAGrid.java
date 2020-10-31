/*
    Find a path from upper left corner to lower right corner of an nxn grid, such that we only move down or right.
    Each square contains a positive integer, and the path should be constructed so that the sum of the values along the
    path is as large as possible.
 */
public class PathsInAGrid {

    public static void main(String[] args) {
        int[][] grid = {{3, 7, 9, 2, 7},
                        {9, 8, 3, 5, 5},
                        {1, 7, 9, 8, 5},
                        {3, 8, 6, 4, 10},
                        {6, 3, 9, 7, 8}};

        int n = grid.length;

        // Let sum[i][j] denote the maximum sum on a path from upper left corner to square (i,j).
        int[][] sum = new int[n][n];

        int[][] prev = new int[n][n];
        prev[0][0] = -1;

        // Base case - Initialize the sum array with base values.
        sum[0][0] = grid[0][0];
        for (int i = 1; i < sum.length; i++) {
            sum[0][i] = sum[0][i - 1] + grid[0][i];
            prev[0][i] = i - 1;
        }
        for (int i = 1; i < sum.length; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
            prev[i][0] = (i - 1) * n;
        }

        // The recursive case
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (sum[i-1][j] < sum[i][j-1]) {
                    sum[i][j] = sum[i][j-1];
                    prev[i][j] = i * n + j-1;
                }
                else {
                    sum[i][j] = sum[i-1][j];
                    prev[i][j] = (i - 1) * n + j;
                }
                sum[i][j] += grid[i][j];
            }
        }


        System.out.println("The biggest sum of a path from the upper " +
                "left corner to the lower right corner is: " + sum[n - 1][n - 1]);

        System.out.print("The path in reverse order is: ");
        int prevIndex = (n - 1) * n + (n - 1);
        while (prevIndex >= 0) {
            int row = prevIndex / n;
            int col = prevIndex % n;
            System.out.print("(" + row + "," + col + "), ");

            prevIndex = prev[row][col];
        }
    }
}
