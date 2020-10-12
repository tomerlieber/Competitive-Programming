import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MagicSquare {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[][] square = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                square[i][j] = in.nextInt();
            }
        }

        ArrayList<Integer> badLines = new ArrayList<>();

        int mainDiagonalSum = 0;
        for (int i = 0; i < N; i++) {
            mainDiagonalSum += square[i][i];
        }

        // Calc columns
        for (int i = N - 1; i >= 0; i--) {
            int columnSum = 0;
            for (int j = 0; j < N; j++) {
                columnSum += square[j][i];
            }

            if (mainDiagonalSum != columnSum) {
                badLines.add(-i - 1);
            }
        }

        // Calc anti diagonal
        int antiDiagonalSum = 0;
        for (int i = 0; i < N; i++) {
            antiDiagonalSum += square[i][N - i - 1];
        }

        if (mainDiagonalSum != antiDiagonalSum) {
            badLines.add(0);
        }

        // Calc rows
        for (int i = 0; i < N; i++) {
            int rowSum = 0;
            for (int j = 0; j < N; j++) {
                rowSum += square[i][j];
            }

            if (mainDiagonalSum != rowSum) {
                badLines.add(i + 1);
            }
        }

        System.out.println(badLines.size());
        for (int line : badLines) {
            System.out.println(line);
        }
    }

    private static void setStandardInput() {

        String input = "4\n" +
                "16 3 2 13\n" +
                "5 10 11 8\n" +
                "6 9 7 12\n" +
                "4 15 14 1";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}