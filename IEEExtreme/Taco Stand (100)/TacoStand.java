import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class TacoStand {
    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for (int i = 0; i < n; i++) {

            long s = in.nextLong();
            long m = in.nextLong();
            long r = in.nextLong();
            long b = in.nextLong();

            long max = Math.max(Math.max(m, r), b);
            long med = Math.max(Math.min(m, r), Math.min(Math.max(m, r), b));
            long min = Math.min(Math.min(m, r), b);

            if (max > med + min) {
                System.out.println(Math.min(s, med + Math.min(max - med, min)));
            } else {
                System.out.println(Math.min(s, (max + med + min) / 2));
            }
        }
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "5 3 4 1\n" +
                "1 9 9 9";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}