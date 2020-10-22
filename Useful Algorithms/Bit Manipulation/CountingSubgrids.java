public class CountingSubgrids {

    public static void main (String[] args) {

        int n = 5;
        boolean[][] grid = new boolean[n][n];
        grid[0][1] = true;
        grid[0][4] = true;
        grid[1][1] = true;
        grid[1][2] = true;
        grid[2][0] = true;
        grid[3][1] = true;
        grid[3][2] = true;
        grid[3][4] = true;

        int N = 32;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] && grid[j][k]) {
                        count++;
                    }
                }
            }
        }


        System.out.println(Integer.bitCount(7));

        System.out.println ((count - 1) / 2);

    }
}
