import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class DigitFun {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        String A0 = in.nextLine();

        while (!A0.equals("END")) {

            System.out.println(getSmallestI(A0));

            A0 = in.nextLine();
        }
    }

    private static int getSmallestI(String A0) {

        int i = 1;

        String nextA = String.valueOf(A0.length());

        while (!nextA.equals(A0)) {
            A0 = nextA;
            nextA = String.valueOf(nextA.length());
            i++;
        }

        return i;
    }

    private static void setStandardInput() {

        String input = "9999\n" +
                "0\n" +
                "1\n" +
                "9999999999\n" +
                "END";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}