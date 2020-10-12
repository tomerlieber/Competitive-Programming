public class EightQueensPuzzle {

    public static void main(String[] args) {

        int n = 8;

        System.out.println(search(n));
    }

    private static int search(int n) {

        boolean[] column = new boolean[n];
        boolean[] diag1 = new boolean[n + n - 1];
        boolean[] diag2 = new boolean[n * n + n - 1];

        return countLegalConfigurations(0, n, column, diag1, diag2);
    }

    // Example of backtracking algorithm
    private static int countLegalConfigurations(int y, int n, boolean[] column, boolean[] secDiag, boolean[] mainDiag) {

        int count = 0;

        if (y == n) {
            count++;
        }
        else {

            for (int x = 0; x < n; x++) {

                if (column[x] || secDiag[x + y] || mainDiag[x - y + n - 1]) {
                    continue;
                }

                column[x] = secDiag[x + y] = mainDiag[x - y + n - 1] = true;

                count+= countLegalConfigurations(y + 1, n, column, secDiag, mainDiag);

                column[x] = secDiag[x + y] = mainDiag[x - y + n - 1] = false;
            }
        }

        return count;
    }
}
