import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int q = in.nextInt();
        in.skip("\n");

        for (int t = 1; t <= q; t++) {

            long a = in.nextLong();
            long b = in.nextLong();

            if (t != q) {
                in.skip("\n");
            }

            // Swap between a and b in order to make a higher than b in the tree.
            if (b < a) {
                long temp = b;
                b = a;
                a = temp;
            }

            long levelA = getLeftMostSetBit(a);
            long levelB = getLeftMostSetBit(b);

            long distance = levelB - levelA;

            b >>= distance;

            while (a != b) {

                // Raises the nodes one level in the tree
                a >>= 1;
                b >>= 1;

                distance += 2;
            }

            System.out.println(distance);
        }
    }

    private static void setStandardInput() {

        String input = "4\n" +
                "1 1\n" +
                "1 2\n" +
                "2 3\n" +
                "10 6";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }

    private static int getLeftMostSetBit(long num) {
        int n = 0;

        while (num != 0) {
            num = num >> 1;
            n++;
        }

        return n;
    }
}
