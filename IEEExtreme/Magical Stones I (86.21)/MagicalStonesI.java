import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MagicalStonesI {

    public static void main (String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        int[] S = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = in.nextInt();
        }

        int Q = in.nextInt();

        int minK = Integer.MAX_VALUE;

        int[] queries = new int[Q];
        for (int i = 0; i < Q; i++) {
            int K = in.nextInt();
            queries[i] = K;

            if (K < minK) {
                minK = K;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(i);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int spellCounter = 0;
        map.put(set.size(), spellCounter);

        while (set.size() >= minK) {

            spellCounter++;

            HashSet<Integer> newSet = new HashSet<>();

            for (Integer stone : set) {

                newSet.add(S[stone - 1]);
            }

            if (newSet.size() == set.size()) {
                break;
            }

            set = newSet;

            map.put(set.size(), spellCounter);
        }

        for (int i = 0; i < Q; i++) {
            Integer result = map.get(queries[i]);
            System.out.println(result == null ? -1 : result);
        }

        System.out.println();
    }

    private static void setStandardInput() {

        String input = "5\n" +
                "3 3 2 3 1\n" +
                "4\n" +
                "3\n" +
                "2\n" +
                "1\n" +
                "4";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
