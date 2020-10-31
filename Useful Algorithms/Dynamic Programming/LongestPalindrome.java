import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class LongestPalindrome {
    public static void main(String[] args) {

        String str = "obsession";

        InputStream fakeIn = new ByteArrayInputStream(str.getBytes());

        System.setIn(fakeIn);

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        // The result (length of LPS)
        int maxLength = 1;

        int start = 0;
        int len = input.length();

        int low, high;

        // One by one consider every character as center point of even and length palindromes
        for (int i = 1; i < len; ++i) {
            // Find the longest even length palindrome with center points as i-1 and i.
            low = i - 1;
            high = i;
            while (low >= 0 && high < len && input.charAt(low) == input.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                low--;
                high++;
            }

            // Find the longest odd length palindrome with center point as i
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < len && input.charAt(low) == input.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                low--;
                high++;
            }
        }

        System.out.println(input.substring(start, start + maxLength));
    }

    public static void method2() {
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        // get length of input string
        int n = input.length();

        // table[i][j] will be false if substring str[i..j] is not palindrome. Else table[i][j] will be true
        boolean[][] table = new boolean[input.length()][input.length()];

        // All substrings of length 1 are palindromes

        int maxLength = 1;
        for (int i = 0; i < table.length; i++) {
            table[i][i] = true;
        }

        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for lengths greater than 2. k is length of substring
        for (int k = 3; k <= n; ++k) {

            // Fix the starting index
            for (int i = 0; i < n - k + 1; ++i) {
                // Get the ending index of substring from starting index i and length k
                int j = i + k - 1;

                // checking for sub-string from ith index to jth index iff str.charAt(i+1) to str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1] && input.charAt(i) == input.charAt(j)) {
                    table[i][j] = true;

                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

        System.out.println(input.substring(start, start + maxLength));
    }

    public static void method1() {

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        int maxLength = 0;
        String longestPalindrome = "";

        for (int i = 0; i < input.length(); i++) {

            for (int j = i; j < input.length(); j++) {

                int currentLength = j - i + 1;
                if (currentLength > maxLength && isPalindrome(input, i, j)) {
                    maxLength = currentLength;
                    longestPalindrome = input.substring(i, i + currentLength);
                }
            }
        }

        System.out.println(longestPalindrome);
    }

    public static boolean isPalindrome(String str, int i, int j) {

        while (i < j) {

            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
