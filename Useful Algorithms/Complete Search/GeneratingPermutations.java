import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneratingPermutations {

    public static void main(String[] args) {

        ArrayList<Integer> nums1 = new ArrayList<>(Arrays.asList(1, 2, 3));

        // Another way to create a list:
        // ArrayList<Integer> nums2 = Arrays.asList(new Integer[] {1, 2, 3, 4});

        List<List<Integer>> powerSet = getPermutations(nums1);

        for (List subset : powerSet) {
            for (var element : subset) {
                System.out.print(element + " ");
            }

            System.out.println();
        }
    }

    // Time Complexity = O(n*n!), Space Complexity = O(n*n!)
    private static <T> List<List<T>> getPermutations(List<T> list) {

        List<List<T>> result = new ArrayList<>();

        // Start from an empty list
        result.add(new ArrayList<>());

        for (T elementToAdd : list) {

            // List of list in current iteration of the array num
            List<List<T>> currentResult = new ArrayList<>();

            for (List<T> currentPermutation : result) {

                // The number of locations to insert is largest index + 1
                for (int j = 0; j < currentPermutation.size() + 1; j++) {

                    // Add list[i] to different locations
                    currentPermutation.add(j, elementToAdd);

                    List<T> temp = new ArrayList<>(currentPermutation);
                    currentResult.add(temp);

                    // Remove list[i] add
                    currentPermutation.remove(j);
                }
            }

            result = new ArrayList<>(currentResult);
        }

        return result;
    }

    // Time Complexity = O(n*n!)
    private static <T> List<List<T>> getPermutationsRecursion(T[] list) {
        List<List<T>> result = new ArrayList<>();
        helper(0, list, result);
        return result;
    }

    private static <T> void helper(int start, T[] list, List<List<T>> result) {

        int n = list.length;
        if (start == n - 1) {

            List<T> temp = new ArrayList<>(Arrays.asList(list));
            result.add(temp);
        }
        else {
            for (int i = start; i < n; i++) {
                swap(list, i, start);
                helper(start + 1, list, result);
                swap(list, i, start);
            }
        }
    }

    private static <T> void swap(T[] list, int i, int j) {
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}