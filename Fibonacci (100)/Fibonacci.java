// Don't place your source in a package
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Fibonacci {

    private static int firstDigit = 1;
    private static int secondDigit = 1;

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        in.skip("\n");

        ArrayList<Integer> fibUnityDigitCycle = new ArrayList<>();
        fibUnityDigitCycle.add(firstDigit);
        fibUnityDigitCycle.add(secondDigit);

        calcFibUnityDigitCycle(firstDigit, secondDigit, fibUnityDigitCycle);

        int[] helperArray = fibUnityDigitCycle.stream().mapToInt(Integer::valueOf).toArray();

        for (int testNum = 1; testNum <= t; testNum++) {

            int m = in.nextInt();

            in.skip(testNum < t ? "\n" : "");

            int result = helperArray[m % helperArray.length];

            System.out.println(result);
        }
    }

    private static void calcFibUnityDigitCycle(int prevDigit, int curDigit, ArrayList<Integer> fibUnityDigitCycle) {

        int nextDigit = (prevDigit + curDigit) % 10;

        // Detect cycle because the next digit can be the firstDigit
        if (nextDigit == firstDigit && (curDigit + nextDigit == secondDigit)) {
            return;
        }

        fibUnityDigitCycle.add(nextDigit);
        calcFibUnityDigitCycle(curDigit, nextDigit, fibUnityDigitCycle);
    }

    public static void setStandardInput() {

        String input = "3\n" +
                "2\n" +
                "4\n" +
                "10";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}