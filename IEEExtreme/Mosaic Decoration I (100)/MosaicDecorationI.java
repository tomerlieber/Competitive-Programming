import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MosaicDecorationI {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int Cb = in.nextInt();

        int Cp = in.nextInt();

        int[] B = new int[N];
        int[] P = new int[N];

        int sumB = 0;
        int sumP = 0;
        for (int i = 0; i < N; i++) {
            sumB += in.nextInt();
            sumP += in.nextInt();
        }

        int cost = 0;

        if (sumB % 10 != 0) {
            sumB += (10 - sumB % 10);
        }

        if (sumP % 10 != 0) {
            sumP += (10 - sumP % 10);
        }

        System.out.println((sumB / 10) * Cb + (sumP / 10) * Cp);
    }

    private static void setStandardInput() {

        String input = "3 5 7\n" +
                "10 10\n" +
                "20 30\n" +
                "30 3";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
