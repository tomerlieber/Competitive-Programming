import java.util.stream.StreamSupport;

/*
    Find the minimum number of editing operations to transform a string into another string.
 */
public class EditDistance {
    public static void main (String[] args) {

        String x = "LOVE";
        String y = "MOVIE";

        int n = x.length() + 1;
        int m = y.length() + 1;

        // Let distance[a][b] denoting the distance between prefixes x[0...a] and y[0...b].
        int[][] distances = new int[n][m];

        // Base case - the distance between two empty strings is 0.
        distances[0][0] = 0;

        // Base case - the distance between empty string and a string is the length of the string
        for (int a = 0; a < n; a++) {
            distances[a][0] = a;
        }
        for (int b = 0; b < m; b++) {
            distances[0][b] = b;
        }

        // The recursive case
        for (int a = 1; a < n; a++) {

            for (int b = 1; b < m; b++) {

                // Insert a character at the end of y
                // For example, after we calculated the distance of "abc" and "ab", we can use it in order to calculate
                // the distances of "abc" and "abc"
                int opt1 = distances[a][b-1] + 1;

                // remove the last character from x
                // For example, after we calculated the distance of "ab" and "abc", we can use it in order to calculate
                // the distances of "abc" and "abc".
                int opt2 = distances[a-1][b] + 1;

                // match or modify the last character of x
                // For example, after we calculated the distance of "ab" and "ac", we can use it in order to calculate
                // the distance of "abd" and "acd".
                int opt3 = distances[a-1][b-1] + (x.charAt(a - 1) == y.charAt(b - 1) ? 0 : 1);

                distances[a][b] = Math.min(opt1, Math.min(opt2, opt3));
            }
        }

        System.out.println(distances[n- 1][m - 1]);
    }
}
