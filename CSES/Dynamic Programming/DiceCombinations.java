import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class DiceCombinations {

    static int m = (int) Math.pow(10, 9) + 7;

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int[] count = new int[n + 1]; // Let count[i] denotes the number of ways % m we can form the sum i.
        count[0] = 1; // There is only one way to form an empty sum.

        // x indicates a sum we want to calculate the number of ways we can form it.
        for (int x = 1; x <= n; x++) {
            // c indicates an outcome of a throw of a dice.
            for (int c = 1; c <= 6; c++) {

                if (x - c >= 0) {
                    count[x] += count[x - c];
                    count[x] %= m;
                }
            }
        }

        System.out.println(count[n]);
    }

    private static void setStandardInput() {

        String input = "3";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }

}
