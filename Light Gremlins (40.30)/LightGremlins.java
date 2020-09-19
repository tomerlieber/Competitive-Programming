import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class LightGremlins {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt(); // The number of test cases.

        for (int i = 0; i < t; i++) {

            int switchesNum = in.nextInt() + 1; // The number of switches in the hallway

            boolean[] switches = new boolean[switchesNum];

            int n = in.nextInt(); // The number of gremlins who live in the hallway

            HashSet<Integer> indexes = new HashSet<>();

            for (int j = 0; j < n; j++) {

                int prime_j = in.nextInt();
                for (int k = prime_j; k < switchesNum; k += prime_j) {
                    switches[k] = !switches[k];
                    indexes.add(k);
                }
            }

            long count = 0;

            for (int index : indexes) {
                if (switches[index]) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    private static void setStandardInput() {

        String input = "3\n" +
                "21 3 7 13 3\n" +
                "20 1 31\n" +
                "30 3 2 3 5";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}