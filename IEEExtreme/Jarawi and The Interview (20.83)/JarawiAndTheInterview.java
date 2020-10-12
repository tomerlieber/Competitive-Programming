import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class JarawiAndTheInterview {

    public static void main (String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        String s = in.nextLine();

        int q = in.nextInt();
        in.skip("\n");

        for (int i = 0; i < q; i++) {

            String pi = in.nextLine();

            int piLength = pi.length();

            for (int j = 0; j < pi.length(); j++) {

                String piSuffix = pi.substring(j, piLength);

                if (isSubSequence(piSuffix, s)) {
                    System.out.println(piSuffix.length());
                    break;
                }
            }
        }
    }

    // Check if str1 is subsequence of str2
    private static boolean isSubSequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        return isSubSequence(str1, str2, m, n);
    }

    private static boolean isSubSequence(String str1, String str2, int m, int n) {

        // Base cases
        if (m == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }

        // If last characters of two strings are matching
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return isSubSequence(str1, str2, m - 1, n - 1);

        // If last characters are not matching
        return isSubSequence(str1, str2, m, n - 1);
    }

    private static void setStandardInput() {

        String input = "xaybaba\n" +
                "2\n" +
                "aaba\n" +
                "yx";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}