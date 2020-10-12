import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class FullAdder {

    private static int base;
    private static HashMap<Character, Integer> symbolToDigit = new HashMap<>();
    private static HashMap<Integer, Character> digitToSymbol = new HashMap<>();

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        // Read the first 4 lines
        String[] lines = new String[4];
        for (int i = 0; i < 4; i++) {
            lines[i] = in.nextLine();
        }

        // Extract the base, the symbols that make up the base and the numbers be be summed.
        String[] firstLine = lines[0].split(" ");
        base = Integer.parseInt(firstLine[0]);
        String symbols = firstLine[1];
        String num1 = lines[1].trim();
        String num2 = lines[2].substring(1).trim();

        // Create a dictionaries to map between the symbols to the digits (both directions).
        for (int i = 0; i < base; i++) {
            symbolToDigit.put(symbols.charAt(i), i);
            digitToSymbol.put(i, symbols.charAt(i));
        }

        // Make sure that the longer number will be num2.
        if (num1.length() > num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // Pad num1 with zeros in the end, so num1 and num2 are in the same length.
        int distance = num2.length() - num1.length();
        String zero = digitToSymbol.get(0).toString();
        String zeros = repeat(zero, distance);
        num1 = zeros + num1;

        String sum = add(num1, num2);

        // Print the first four lines of the output that identical to the input.
        for (int i = 0; i < 4; i++) {
            System.out.println(lines[i]);
        }

        // Print the sum.
        System.out.println(sum.length() > num2.length() ? sum : " " + sum);
    }

    private static String repeat(String s, int length) {

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < length; i++) {
            res.append(s);
        }

        return res.toString();
    }

    private static String add(String num1, String num2) {

        StringBuilder res = new StringBuilder();

        int n = num1.length();

        char Cin = digitToSymbol.get(0);
        for (int i = n - 1; i >= 0; i--) {

            char A = num1.charAt(i);
            char B = num2.charAt(i);

            int sum = symbolToDigit.get(A) + symbolToDigit.get(B) + symbolToDigit.get(Cin);

            char S = digitToSymbol.get(sum % base);
            char Cout = digitToSymbol.get(sum / base);

            Cin = Cout;
            res.append(S);
        }

        if (Cin != digitToSymbol.get(0)) {
            res.append(Cin);
        }

        return res.reverse().toString();
    }

    private static void setStandardInput() {

        String input = "14 qc765OZLev8SIB\n" +
                " IZvOOL76qL5S8SS8\n" +
                "+    O8qZeB6IBS68\n" +
                "-----------------\n" +
                "              ???";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
