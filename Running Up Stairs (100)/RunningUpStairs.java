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

            BigInteger count = calcWaysRec(n);

            System.out.println(count);
        }
    }

    // The best approach to solve the question with loop for.
    private static BigInteger calcWays(int n)
    {
        BigInteger prev = BigInteger. valueOf(1);
        BigInteger curr = BigInteger.valueOf(1);
        BigInteger next;

        if (n == 1) {
            return curr;
        }

        for (int i = 2; i <= n; i++)
        {
            next = prev.add(curr);
            prev = curr;
            curr = next;
        }

        return curr;
    }

    // Another approach to solve the question with recursion.
    // Put attention how I stored the fibonacci numbers I calculated so far to prevent recalculation.
    // This reduce the running time from exponential to linear.
    private static BigInteger calcWaysRec(int n) {

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
                "22000";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}

