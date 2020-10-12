import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneratingSubsets {

    public static void main(String[] args) {

        ArrayList<Integer> nums1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        // Another way to create a list:
        // ArrayList<Integer> nums2 = Arrays.asList(new Integer[] {1, 2, 3, 4});

        List<List<Integer>> powerSet = getSubsets(nums1);

        for (List subset : powerSet) {
            for (var element : subset) {
                System.out.print(element + " ");
            }

            System.out.println();
        }
    }

    private static <T> List<List<T>> getSubsets(List<T> list) {

        int n = list.size();

        int[] powerOfTwos = new int[n + 1];
        for (int i  = 0; i <= n; i++) {
            powerOfTwos[i] = 1 << i;
        }

        int powerSetSize = powerOfTwos[n];

        List<List<T>> powerSet = new ArrayList<>(powerSetSize);

        for (int counter = 0; counter < powerSetSize; counter++) {

            List<T> currentSubset = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if ((counter & powerOfTwos[i]) > 0) {

                    currentSubset.add(list.get(i));
                }
            }

            powerSet.add(currentSubset);
        }

        return powerSet;
    }
}
