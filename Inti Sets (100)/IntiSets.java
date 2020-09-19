import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

public class IntiSets {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int Q = in.nextInt(); // The number of students.

        for (int test_i = 0; test_i < Q; test_i++) {

            long N = in.nextLong();
            long A = in.nextLong();
            long B = in.nextLong();

            Set<Long> primeFactors = getUniquePrimeFactors(N);
            List<Long> primes = new ArrayList<>(primeFactors);

            // Add sum of all numbers between A and B (inclusive)
            BigInteger sum = BigInteger.valueOf(B - A + 1).multiply(BigInteger.valueOf(B + A)).divide(BigInteger.valueOf(2));

            // The number of combinations for the inclusion-exclusion equation.
            long numberOfCombinations = 1 << primes.size();

            // Run from counter 00..01 to 11..11 (
            for (long counter = 1; counter < numberOfCombinations; counter++) {

                long productOfPrimes = 1;

                long primeCount = 0;

                for (int j = 0; j < primes.size(); j++) {

                    //Check if jth bit in the counter is set. If set then add jth element to the calculation */
                    if ((counter & (1 << j)) > 0) {
                        primeCount++;
                        productOfPrimes *= primes.get(j);
                    }
                }

                // Check the sign before next connected according the number of primes that was expressed in the calculation.
                // |A u B u C| =|A| + |B| + |C| - |A and B| - |B and C| - |A and C| + |A and B and c|
                if (primeCount % 2 == 1) {
                    sum = sum.subtract(sumOfMultiples(productOfPrimes, A, B));
                } else {
                    sum = sum.add(sumOfMultiples(productOfPrimes, A, B));
                }
            }

            System.out.println(sum.mod(BigInteger.valueOf(1000000007)));
        }
    }

    // Sum all multiples of base between A and B (inclusive)
    public static BigInteger sumOfMultiples(long base, long A, long B) {

        long a1 = ((base + (A - 1)) / base) * base;
        long an = B - (B % base);
        long count = (an - a1) / base + 1;

        return BigInteger.valueOf(count).multiply(BigInteger.valueOf(a1 + an)).divide(BigInteger.valueOf(2));
    }

    // Get all unique prime factors of n.
    public static Set<Long> getUniquePrimeFactors(long n) {

        Set<Long> primeFactors = new HashSet<>();

        // Print the number of 2s that divide n
        while (n % 2 == 0) {

            primeFactors.add(2L);
            n /= 2;
        }

        // n must be odd at this point.  So we can skip one element (Note i = i +2)
        for (long i = 3; i <= Math.sqrt(n); i += 2) {

            // While i divides n, print i and divide n
            while (n % i == 0) {
                primeFactors.add(i);
                n /= i;
            }
        }

        // This condition is to handle the case when n is a prime number greater than 2
        if (n > 1) {
            primeFactors.add(n);
        }

        return primeFactors;
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "12 5 10 \n" +
                "5 1 4";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}