import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        System.out.println(-4 % 2);

        int[] nums = {2, 6, 0, 3, 5, 10};

        System.out.println("Original array:");
        Arrays.stream(nums).mapToObj(num -> num + " ").forEach(System.out::print);

        System.out.println();

        mergeSort(nums, 0, nums.length - 1);

        System.out.println("Sorted array:");
        Arrays.stream(nums).mapToObj(num -> num + " ").forEach(System.out::print);
    }

    private static void mergeSort(int[] arr, int start, int end) {

        if (start == end) {
            return;
        }

        // Calculate the position of the middle element.
        int middle = (start + end) / 2;

        // Recursively sort the first and second halves
        mergeSort(arr, start, middle);
        mergeSort(arr, middle + 1, end);

        merge(arr, start, middle, end);
    }

    // Merge two sub arrays of arr. First sub array is arr[start..middle] and second sub array is arr[middle+1...end].
    private static void merge(int arr[], int start, int middle, int end)
    {
        // Find sizes of two sub arrays to be merged
        int n1 = middle - start + 1;
        int n2 = end - middle;

        // Create left and right arrays
        int[] left = new int[n1];
        int[] right = new int[n2];

        // Copy data to left and right arrays
        System.arraycopy(arr, start, left, 0, n1);
        System.arraycopy(arr, middle + 1, right, 0, n2);

        // Merge the left and right arrays

        // Initial indexes of first, second and merged sub arrays accordingly.
        int p1 = 0, p2 = 0, p3 = start;

        while (p1 < n1 && p2 < n2) {
            if (left[p1] <= right[p2]) {
                arr[p3] = left[p1];
                p1++;
            }
            else {
                arr[p3] = right[p2];
                p2++;
            }

            p3++;
        }

        // Copy remaining elements of left sub array if any.
        while (p1 < n1) {
            arr[p3] = left[p1];
            p1++;
            p3++;
        }

        // Copy remaining elements of right sub array if any.
        while (p2 < n2) {
            arr[p3] = right[p2];
            p2++;
            p3++;
        }
    }

    // Merges two sub arrays of arr[].
    // First sub array is arr[start..middle] and second sub array is arr[middle+1...end] (in-place implementation).
    private static void mergeInPlace(int[] arr, int start, int middle, int end) {

        // Two pointers to maintain start of both arrays to merge
        int pointer1 = start;
        int pointer2 = middle + 1;

        while (pointer1 <= middle && pointer2 <= end) {

            // If element 1 is in right place
            if (arr[start] <= arr[pointer2]) {
                start++;
            }
            else {
                int value = arr[pointer2];
                int index = pointer2;

                // Shift all the elements between element 1 element 2, right by 1.
                while (index != start) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[start] = value;

                // Update all the pointers
                pointer1++;
                middle++;
                pointer2++;
            }
        }
    }
}
