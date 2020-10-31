import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Abbreviation {

        public static void main(String[] args) {

            // Uncomment the following line in order to simulate a specific input
            setStandardInput();

        Scanner scanner = new Scanner(System.in);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            System.out.println(result);
        }
    }

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {

        int n = a.length();
        int m = b.length();

        boolean[][] abb = new boolean[n + 1][m + 1];

        // Base case
        abb[0][0] = true;
        for (int i = 1; i < m; i++) {
            abb[0][i] = false;
        }

        boolean isUpperCase = false;
        for (int i = 1; i < n; i++) {
            if (Character.isUpperCase(a.charAt(i-1))) {
                isUpperCase = true;
            }

            if(isUpperCase) {
                abb[i][0] = false;
            }
            else {
                abb[i][0] = true;
            }
        }

        // Recursion case
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {

                boolean opt1 = false;

                char c1 = a.charAt(i-1);

                if (abb[i-1][j-1]) {
                    char c2 = b.charAt(j-1);

                    opt1 = c1 == c2 || Character.toUpperCase(c1) == c2;
                }

                boolean opt2 = false;

                if (abb[i-1][j]) {
                    opt2 = Character.isLowerCase(c1);
                }

                abb[i][j] = opt1 || opt2;
            }
        }

        return abb[n][m] ? "YES" : "NO";
    }

    private static void setStandardInput() {

        String input = "1\n" +
                "AbcDE\n" +
                "ABDE";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
