import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Pattern3 {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int T = in.nextInt(); // The number of test cases.
        in.skip("\n");

        for (int test_i = 0; test_i < T; test_i++) {

            String encodedSignal = in.nextLine();
            int n = encodedSignal.length();

            int[] z = calcZarray(encodedSignal);

            boolean printed = false;
            for (int i = 0; i < n; i++) {

                if (z[i] >= n - i) {
                    System.out.println(i);
                    printed = true;
                    break;
                }
            }

            if (!printed) {
                System.out.println(n);
            }
        }
    }

    // Constructs the Z-array in O(n) time.
    private static int[] calcZarray(String s) {

        int n = s.length();
        int[] z = new int[n];

        int x = 0, y = 0;

        for (int i = 1; i < n; i++) {
            z[i] = Math.max(0, Math.min(z[i - x], y - i + 1));
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                x = i;
                y = i + z[i];
                z[i]++;
            }
        }

        return z;
    }

    private static void setStandardInput() {

        String input = "6\n" +
                "abab\n" +
                "abababababababababab\n" +
                "abababababab\n" +
                "abc\n" +
                "aaaaaa\n" +
                "aabaabbaabaabbaabaabbaabaab";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
