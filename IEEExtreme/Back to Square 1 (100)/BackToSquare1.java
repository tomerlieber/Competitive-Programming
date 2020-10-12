import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class BackToSquare1 {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        while (n != 0) {
            in.skip("\n");

            double[] prob = new double[n-1];

            for(int i = 0; i < n - 1; i++) {
                prob[i] = in.nextDouble();
            }

            double result = 1;
            double denom = 1;

            for (int i = n-2; i >= 0; i--) {

                denom *= prob[i];
                result += 1 / denom;
            }

            System.out.println(Math.round(result));

            n = in.nextInt();
        }
    }

    public static void setStandardInput() {

        String input = "2\n" +
                "0.5\n" +
                "4\n" +
                "0.3 0.2 0.1\n" +
                "0";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
