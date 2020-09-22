import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;

public class PlayWithGCD {

    private static int mod = 1000000007;

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Scanner in = new Scanner(System.in);

        int N = in.nextInt(); // Denoting the number of balls.
        in.skip("\n");

        int maxN = 100005; // The maximum number of balls.
        int maxV = 10005; // The maximum value written on a ball.

        int[] nums = new int[maxN];
        ArrayList<Integer> sortedBalls = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            nums[i] = in.nextInt();
            sortedBalls.add(nums[i]);
        }

        Arrays.sort(sortedBalls.toArray());

        int[] balls = IntStream.of(nums).distinct().toArray(); // V

        HashMap<Integer, Integer> H = new HashMap<>();
        for (int i = 0; i < balls.length; i++) {
            H.put(balls[i], i);
        }

        int[] counter = new int[maxN];
        for (int i = 1; i <= N; i++) {
            counter[H.get(nums[i])]++;
        }

        long[] powerOfTwo = new long[maxN]; // Maybe I can change to LONG as solution
        powerOfTwo[0] = 1L;
        for (int i = 1; i <= N; i++) {
            powerOfTwo[i] = (powerOfTwo[i - 1] << 1L) % mod;
        }

        // Let ways[v] denotes the number of ways modulus 10^9+7 that balls can
        // be drawn from the set, so that their GCD equals the number v.
        int[] ways = new int[maxV];

        for (int i = 0; i < balls.length; i++) {
//            balls
        }

        for (int i = 0; i < balls.length; i++) {

            for (int v = 1; v <= 10000; v++) {

                int gcd = gcd(balls[i], v);
                ways[gcd] = add(ways[gcd], (int)((powerOfTwo[counter[i]] - 1) * ways[v]));
            }

            ways[balls[i]] = add(ways[balls[i]], (int)powerOfTwo[counter[i]] - 1);
        }

        int Q = in.nextInt(); // Representing the number of GCD queries that will have to be performed.
        in.nextLine();

//        in.skip("\n");

        for (int queryNum = 1; queryNum <= Q; queryNum++) {

            int X = in.nextInt(); // Denoting the GCD.

            System.out.println(ways[X]);
        }
    }

    private static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }

        return gcd(y, x % y);
    }

    private static int add(int x, int y) {
        x+=y;
        if(x>=mod)x-=mod;
        return x;
//        return (x % mod + y % mod) % mod;
    }

    private static void setStandardInput() {

        String input = "7\n" +
                "2 3 5 6 2 12 18\n" +
                "2 \n" +
                "2\n" +
                "3";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}


//---//////////////////////////////////////
//
// int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
// Arrays.sort(nums);
//
// int[] balls = IntStream.of(nums).distinct().toArray();
//
// List[] primes = new List[N];
//
//  for (int i = 0; i < N; i++) {
//     primes[i] = getPrimeFactors(nums[i]);
//  }
//
//  int Q = in.nextInt(); // Representing the number of GCD queries that will have to be performed.
//  in.skip("\n");
//
//  for (int queryNum = 0; queryNum < Q; queryNum++) {
//
//     int X = in.nextInt(); // Denoting the GCD.
//     List xPrimes = getPrimeFactors(X);
//
//     System.out.println("GOOD");
// }


//    // Get all prime factors of n.
//    public static List<Integer> getPrimeFactors(int n) {
//
//        ArrayList<Integer> primeFactors = new ArrayList<>();
//
//        // Print the number of 2s that divide n
//        while (n % 2 == 0) {
//
//            primeFactors.add(2);
//            n /= 2;
//        }
//
//        // n must be odd at this point.  So we can skip one element (Note i = i +2)
//        for (int i = 3; i <= Math.sqrt(n); i += 2) {
//
//            // While i divides n, print i and divide n
//            while (n % i == 0) {
//                primeFactors.add(i);
//                n /= i;
//            }
//        }
//
//        // This condition is to handle the case when n is a prime number greater than 2
//        if (n > 1) {
//            primeFactors.add(n);
//        }
//
//        return primeFactors;
//    }