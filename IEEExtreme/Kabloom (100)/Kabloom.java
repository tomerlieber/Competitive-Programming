import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Kabloom {

    private static HashMap<String, Integer> cardPoints = new HashMap<>();

    static {
        cardPoints.put("A", 20);
        cardPoints.put("2", 2);
        cardPoints.put("3", 3);
        cardPoints.put("4", 4);
        cardPoints.put("5", 5);
        cardPoints.put("6", 6);
        cardPoints.put("7", 7);
        cardPoints.put("8", 8);
        cardPoints.put("9", 9);
        cardPoints.put("T", 10);
        cardPoints.put("J", 15);
        cardPoints.put("Q", 15);
        cardPoints.put("K", 15);
        cardPoints.put("R", 50);
    }

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        while (n > 0) {
            in.skip("\n");

            String[] row1 = in.nextLine().split(" ");
            String[] row2 = in.nextLine().split(" ");

            // Let sum[i][j] denotes the sum of points value of the cards using i+1 first cards from first row
            // and j+1 first cards from second row.
            int[][] sum = new int[n][n];

            // Base case 1 - we allow to use the first card of each row.
            sum[0][0] = calcPoints(row1[0], row2[0]);
            // Base case 2 - we allow to use the first card in one row and gradually all other cards from a second row.
            for (int i = 1; i < sum.length; i++) {

                sum[0][i] = Math.max(calcPoints(row1[i], row2[0]), sum[0][i-1]);
                sum[i][0] = Math.max(calcPoints(row1[0], row2[i]), sum[i-1][0]);
            }

            // Recursion formula - we can either use or not use the cards i and j from both rows.
            // Option 1: If we use both cards, then the remaining task is sum[i-1][j-1]
            // Option 2: If we use only card i from the first row, then the remaining task is sum[i-1][j].
            // Option 3: If we use only card j from the second row, then the remaining task is sum[i][j-1].
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {

                    int points = calcPoints(row2[i], row1[j]) + sum[i - 1][j - 1];

                    sum[i][j] = Math.max(Math.max(points, sum[i-1][j]), sum[i][j-1]);
                }
            }

            // Print the value of the best Kabloom hand.
            System.out.println(sum[n-1][n-1]);

            n = in.nextInt();
        }
    }

    private static int calcPoints(String card1, String card2) {

        if (!card1.equals(card2)) {

            if (card1.equals("R")) {
                return 2 * cardPoints.get(card2);
            }

            if (card2.equals("R")) {
                return 2 * cardPoints.get(card1);
            }

            return 0;
        }

        return 2 * cardPoints.get(card1);
    }

    private static void setStandardInput() {

        String input = "9\n" +
                "6 3 7 4 2 A K R T\n" +
                "3 5 4 7 R A Q K T\n" +
                "0";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
