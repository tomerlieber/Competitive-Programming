import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

public class MrPipposPizza {

    private static HashMap<Integer, BigInteger> nToCatalan = new HashMap<>();

    static {
        // The value of the first catalan number is 1.
        nToCatalan.put(0, BigInteger.ONE);
    }

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        ArrayList<BigInteger> nums = new ArrayList<>();
        while (in.hasNextBigInteger()) {

            BigInteger num = in.nextBigInteger(); // The number of ways the pizza can be cut will be given.
            nums.add(num);
        }

        // Find the biggest input number in order to calculate the other numbers only one.
        // The computation of the biggest number will yield the results for the other numbers.
        BigInteger max = BigInteger.ZERO;
        for (BigInteger num : nums) {

            if (num.compareTo(max) > 0) {
                max = num;
            }
        }

        // Calc the results for all input numbers. The result based on catalan numbers.
        BigInteger currCatalan = BigInteger.ZERO;
        HashMap<BigInteger, Integer> catalanToN = new HashMap<>();

        int n = 1;
        while (currCatalan.compareTo(max) < 0) {
            currCatalan = catalan(n);
            catalanToN.put(currCatalan, n);
            n++;
        }

        for (BigInteger num : nums) {

            int nVal = catalanToN.get(num);
            System.out.println(nVal + 2);
        }
    }

    private static BigInteger catalan(int n) {

        // Check if I have already calculated the n'th catalan.
        if (nToCatalan.containsKey(n)) {
            return nToCatalan.get(n);
        }

        BigInteger result = BigInteger.ZERO;

        // Use the recursive formula to calculate the n'th catalan value.
        for (int i = 0; i <= n - 1; i++) {
            result = result.add(catalan(i).multiply(catalan(n-i-1)));
        }

        nToCatalan.put(n, result);

        return result;
    }

    private static void setStandardInput() {

        String input = "1\n" +
                "5";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}