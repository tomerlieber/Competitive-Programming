import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class GameOfStones {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int T = in.nextInt(); // The number of test cases.

        for (int testNum = 0; testNum < T; testNum++) {

            int turns = 0;

            int G = in.nextInt(); // The number of games to played in parallel.

            for (int i = 0; i < G; i++) {

                int numPiles = in.nextInt(); // The number of piles in the game

                for (int j = 0; j < numPiles; j++) {
                    int numStones = in.nextInt(); // The number of stones in a pile
                    turns += numStones / 2;
                }
            }

            System.out.println(turns % 2 == 0 ? "Bob" : "Alice");
        }
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "2\n" +
                "3\n" +
                "1 3 5\n" +
                "2\n" +
                "3 7\n" +
                "1\n" +
                "5\n" +
                "1 3 5 7 9";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
