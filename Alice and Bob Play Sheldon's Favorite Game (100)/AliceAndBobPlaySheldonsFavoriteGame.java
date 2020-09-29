import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class AliceAndBobPlaySheldonsFavoriteGame {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        Shape[] shapes = Shape.values();
        int len = shapes.length;

        // Let i denotes the shapes were chosen by the players and moves[i] denotes the next shapes will be chosen by the player's in next turn.
        // (i / len) indicate the enum index of Alice's shape and (i % len) indicate the enum index of Bob's shape.
        // For example: i = 7 indicates that Alice chose Paper and Bob chose Rock. move[i]=9 indicates that in the next
        // turn Alice chose Paper and Bob chose Spock.
        int[] moves = new int[len * len];
        int[] aliceGameStates = new int[len * len];

        for (Shape aliceShape : shapes) {
            for (Shape bobShape : shapes) {

                int aliceGameState = compareTo(aliceShape, bobShape);

                Shape aliceNextShape = aliceStrategy(aliceShape, bobShape, aliceGameState);
                Shape bobNextShape = bobStrategy(bobShape, aliceGameState);

                int index = aliceShape.ordinal() * len + bobShape.ordinal();
                moves[index] = aliceNextShape.ordinal() * len + bobNextShape.ordinal();
                aliceGameStates[index] = aliceGameState;
            }
        }

        // The rest of the code assume that we looked on the values of the above arrays and we notices that there are
        // two cycles in the array. One cycle contains the index 17 and another contains the index 16. In addition, in
        // the length of the first cycle is 4 where alice won one time and bob two, and the length of the second cycle
        // is 2 where alice won two times.

        int t = in.nextInt(); // The number of test cases

        for (int testNum = 0; testNum < t; testNum++) {

            Shape aliceShape = Shape.valueOf(in.next()); // The shape that Alice will choose in the first game of the series.
            Shape bobShape = Shape.valueOf(in.next()); // the shape that Bob will choose in the first game of the series.
            long n = in.nextLong(); // How many games Alice and Bob will play in the series.

            int currIndex = aliceShape.ordinal() * len + bobShape.ordinal();
            long aliceWins = 0;
            long bobWins = 0;
            long turns = 1;

            // Calculate the number of wins of each player in the turns until we arrive to one of the cycles at index 16 or 17.
            while (turns <= n && currIndex != 17 && currIndex != 16) {

                if (aliceGameStates[currIndex] == 1) {
                    aliceWins++;
                } else if (aliceGameStates[currIndex] == -1) {
                    bobWins++;
                }

                currIndex = moves[currIndex];
                turns++;
            }

            // Calculate the number of wins of each player according to the number of times we make rounds in the circles.
            if (turns < n) {

                long leftTurns = n - turns;

                if (currIndex == 17) {

                    int cycleLength = 4;
                    long factor = leftTurns / cycleLength;
                    aliceWins += factor; // Each cycle alice wins one time
                    bobWins += factor * 2; // Each cycle bob wins two times.

                    turns += (factor * cycleLength);
                }

                if (currIndex == 16) {

                    int cycleLength = 2;
                    long factor = leftTurns / cycleLength;
                    aliceWins += (factor * 2); // Each cycle alice wins one time

                    turns += (factor * cycleLength);
                }
            }

            // Calculates the number of wins of each player in the remaining turns.
            while (turns <= n) {

                if (aliceGameStates[currIndex] == 1) {
                    aliceWins++;
                } else if (aliceGameStates[currIndex] == -1) {
                    bobWins++;
                }

                currIndex = moves[currIndex];
                turns++;
            }

            if (aliceWins == bobWins) {
                System.out.println(String.format("Alice and Bob tie, each winning %d game(s) and tying %d game(s)",
                        aliceWins, n - aliceWins - bobWins));
            }
            else {

                String winningPlayer = aliceWins > bobWins ? "Alice" : "Bob";
                long wins = aliceWins > bobWins ? aliceWins : bobWins;
                System.out.println(String.format("%s wins, by winning %d game(s) and tying %d game(s)",
                        winningPlayer, wins, n - aliceWins - bobWins));
            }
        }
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "Rock Spock 4\n" +
                "Paper Paper 1";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }

    private static Shape aliceStrategy(Shape shape, Shape opponentsShape, int aliceGameState) {

        if (aliceGameState == 1) {
            return shape;
        } else {

            switch (aliceGameState == 0 ? shape : opponentsShape) {
                case Rock:
                    return Shape.Paper;
                case Paper:
                    return Shape.Scissors;
                case Spock:
                    return Shape.Lizard;
                case Lizard:
                    return Shape.Rock;
                default: // case Scissors:
                    return Shape.Spock;
            }
        }
    }

    private static Shape bobStrategy(Shape shape, int aliceGameState) {

        if (shape == Shape.Spock) {
            if (aliceGameState == -1) {
                return Shape.Rock;
            }
            else if (aliceGameState == 0) {
                return Shape.Lizard;
            }
            else {
                return Shape.Paper;
            }
        } else {
            return Shape.Spock;
        }
    }

    private static int compareTo(Shape shape1, Shape shape2) {

        if (shape1 == shape2) {
            return 0;
        }

        // Scissors cuts Paper
        if (shape1 == Shape.Scissors && shape2 == Shape.Paper) {
            return 1;
        }

        // Paper covers Rock
        if (shape1 == Shape.Paper && shape2 == Shape.Rock) {
            return 1;
        }

        // Rock crushes Lizard
        if (shape1 == Shape.Rock && shape2 == Shape.Lizard) {
            return 1;
        }

        // Lizard poisons Spock
        if (shape1 == Shape.Lizard && shape2 == Shape.Spock) {
            return 1;
        }

        // Spock smashes Scissors
        if (shape1 == Shape.Spock && shape2 == Shape.Scissors) {
            return 1;
        }

        // Scissors decapitates Lizard
        if (shape1 == Shape.Scissors && shape2 == Shape.Lizard) {
            return 1;
        }

        // Lizard eats Paper
        if (shape1 == Shape.Lizard && shape2 == Shape.Paper) {
            return 1;
        }

        // Paper disproves Spock
        if (shape1 == Shape.Paper && shape2 == Shape.Spock) {
            return 1;
        }

        // Spock vaporizes Rock
        if (shape1 == Shape.Spock && shape2 == Shape.Rock) {
            return 1;
        }

        // Rock crushes scissors
        if (shape1 == Shape.Rock && shape2 == Shape.Scissors) {
            return 1;
        }

        return -1;
    }

    enum Shape {
        Scissors,
        Paper,
        Rock,
        Lizard,
        Spock
    }
}
