import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class RunningUpStairs {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt(); // The number of instances of the problem

        for (int testNum = 0; testNum < t; testNum++) {

            int n = in.nextInt(); // The number of steps

            BigInteger count = calcWays(n);

            System.out.println(count);
        }
    }

    private static BigInteger calcWays(int n) {

        // Store the Fibonacci numbers we calculated to prevent recalculation.
        BigInteger[] fibNums = new BigInteger[n + 1];
        fibNums[0] = BigInteger.valueOf(1);
        fibNums[1] = BigInteger.valueOf(1);

        return calcWaysRec(n, fibNums);
    }

    private static BigInteger calcWaysRec(int n, BigInteger[] fibNums) {

        if (fibNums[n] != null) {
            return fibNums[n];
        }

        fibNums[n] = calcWaysRec(n - 2, fibNums).add(calcWaysRec(n - 1, fibNums));

        return fibNums[n];
    }

    private static void setStandardInput() {

        String input = "3\n" +
                "1\n" +
                "2\n" +
                "5";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}

