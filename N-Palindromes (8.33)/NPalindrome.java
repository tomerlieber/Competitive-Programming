import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class NPalindrome {

    private static int mod = 1000000007; /// todo: maybe add final

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        in.skip("\n");

        for (int testNum = 0; testNum < t; testNum++) {

            int n = in.nextInt();

            in.skip(" ");

            String str = in.nextLine();

            int res = singleTest(n, str);

            System.out.println(res);

        }
    }

    private static int singleTest(int n, String str) {

        int mismatch = 0;
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                mismatch++;
            }
        }

        return countWays(str, mismatch, n);
    }

    private static int countWays(String str, int mismatch, int n) {

        if (n > str.length()) {
            return 0;
        }

        if (n == 0 && mismatch > 0) {
            return 0;
        }

        if (n < 0 || mismatch < 0) {
            return 0;
        }

        if (mismatch == 0 && n == 0) {
            return 1;
        }

        if (mismatch == 0 && n == 1 && str.length() % 2 == 1) {
            return 25;
        }

        String subStr = str.substring(1, str.length() - 1);

        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return ((countWays(subStr, mismatch, n) % mod) +
                    (25 * countWays(subStr, mismatch, n - 2)) % mod) % mod;
        } else {
            return ((2 * countWays(subStr, mismatch - 1, n - 1)) % mod +
                    (24 * countWays(subStr, mismatch - 1, n - 2)) % mod) % mod;
        }
    }

    private static void setStandardInput() {

        String input = "4\n" +
                "2 alice\n" +
                "1 racecar\n" +
                "3 alice\n" +
                "2 axbya";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
