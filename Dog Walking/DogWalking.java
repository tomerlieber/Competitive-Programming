import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class DogWalking {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {

            int N = in.nextInt(); // The number of dogs
            int K = in.nextInt(); // The number of employees

            long[] sizes = new long[N]; // The sizes of the dogs
            for (int j = 0; j < N; j++) {
                sizes[j] = in.nextLong();
            }

            Arrays.sort(sizes);

            long[] ranges = new long[N-1];
            for (int j = 0; j < ranges.length; j++) {
                ranges[j] = sizes[j+1] - sizes[j];
            }

            Arrays.sort(ranges);

            long minSum = 0;
            for (int j = 0; j < N - K; j++) {
                minSum += ranges[j];
            }

            System.out.println(minSum);
        }
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "4 2\n" +
                "3\n" +
                "5\n" +
                "1\n" +
                "1\n" +
                "5 4\n" +
                "30\n" +
                "40\n" +
                "20\n" +
                "41\n" +
                "50";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}