import java.io.ByteArrayInputStream;
        import java.io.InputStream;
        import java.util.Scanner;

public class RecXor {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt(); // The number of test cases

        for (int testNum = 0; testNum < t; testNum++) {

            // Rectangle dimensions l x h.
            long l = in.nextInt();
            long h = in.nextInt();
            long n = in.nextInt(); // A number
            // The end points of either of the diagonals
            long d1 = in.nextInt();
            long d2 = in.nextInt();

            long x1 = (d1 - n) / l;
            long y1 = (d1 - n) % l;

            long x2 = (d2 - n) / l;
            long y2 = (d2 - n) % l;

            // Compute the xor of the outer rectangle.
            long bigRectangleXor = computeXOR(n + l * h - 1) ^ computeXOR(n - 1);

            // Change the y coordinates of the end points, so we can assume we got the main diagonal.
            if (y1 > y2) {
                long temp = y1;
                y1 = y2;
                y2 = temp;
            }

            // Compute the xor of the inner rectangle.
            long smallRectangleXor = 0;

            for (long i = x1; i <= x2; i++) {

                long beforeStartLine = n + y1 - 1 + i * l;
                long endLine = n + y2 + i * l;
                long line = computeXOR(beforeStartLine) ^ computeXOR(endLine);

                smallRectangleXor ^= line;
            }

            long result = bigRectangleXor ^ smallRectangleXor;

            System.out.println(result);
        }
    }

    // Method to calculate xor 1 to n.
    private static long computeXOR(long n)
    {
        // If n is a multiple of 4
        if (n % 4 == 0)
            return n;

        // If n%4 gives remainder 1
        if (n % 4 == 1)
            return 1;

        // If n%4 gives remainder 2
        if (n % 4 == 2)
            return n + 1;

        // If n%4 gives remainder 3
        return 0;
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "10 7 1 23 48\n" +
                "7 5 4 22 27";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}