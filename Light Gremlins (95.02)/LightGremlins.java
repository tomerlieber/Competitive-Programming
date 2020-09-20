import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class LightGremlins {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        long[] powerOfTwo = new long[24];
        powerOfTwo[0] = 1;
        for (int i = 1; i < 24; i++)
        {
            powerOfTwo[i] = 1 << i;
        }

        int t = in.nextInt(); // The number of test cases.

        for (int testNum = 0; testNum < t; testNum++) {

            long switchesNum = in.nextLong(); // The number of switches in the hallway

            int n = in.nextInt(); // The number of gremlins who live in the hallway

            int[] primes = new int[n];

            for (int i = 0; i < n; i++) {
                primes[i] = in.nextInt();
            }

            long result = 0;
            long powerSetSize = 1 << n;

            for (long counter = 1; counter < powerSetSize; counter++) {

                long productOfPrimes = 1;
                long sign = -1;
                long coefficient = 0;

                for (int j = 0; j < n; j++) {

                    //Check if jth bit in the counter is set. If set then add jth element to the calculation */
                    if ((counter & powerOfTwo[j]) > 0) {

                        // PUT ATTENTION: I don't totally understand the following condition. I copied it from another
                        // solution in order to get the points for test cases I got wrong answer without it.
                        // I guess that without this condition I have a problem productOfPrimes variable that it
                        // exceeds it's max value.
                        if (productOfPrimes > 10000000000L && (switchesNum / productOfPrimes < primes[j]))
                        {
                            coefficient = 0;
                            break;
                        }

                        productOfPrimes *= primes[j];
                        sign *= -1;
                        coefficient = coefficient == 0 ? 1 : coefficient << 1;
                    }
                }

                result += (coefficient * sign) * (switchesNum / productOfPrimes);
            }

            System.out.println(result);
        }
    }

    public static void main1(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt(); // The number of test cases.

        for (int i = 0; i < t; i++) {

            int switchesNum = in.nextInt() + 1; // The number of switches in the hallway

            boolean[] switches = new boolean[switchesNum];

            int n = in.nextInt(); // The number of gremlins who live in the hallway

            HashSet<Integer> indexes = new HashSet<>();

            for (int j = 0; j < n; j++) {

                int prime_j = in.nextInt();
                for (int k = prime_j; k < switchesNum; k += prime_j) {
                    switches[k] = !switches[k];
                    indexes.add(k);
                }
            }

            long count = 0;

            for (int index : indexes) {
                if (switches[index]) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    private static void setStandardInput() {

        String input = "3\n" +
                "21 3 7 13 3\n" +
                "20 1 31\n" +
                "30 3 2 3 5";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}