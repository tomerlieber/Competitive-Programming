import java.util.Arrays;

/*
    Find the longest increasing subsequence in an array of n elements.
 */
public class LongestIncreasingSubseqience {

    public static void main(String[] args) {

        int[] nums = {6, 2, 5, 1, 7, 4, 8, 3};
        int n = nums.length;

        // Length[i] = the length of the longest increasing subsequence that ends at position i.
        int[] length = new int[n];

        // prev[i] = the index of the previous element in the longest increasing subsequence that ends at position i.
        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        for (int i = 0; i < n; i++) {

            length[i] = 1;
            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i] && length[i] < length[j] + 1) {
                    length[i] = length[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int maxI = 0;
        for (int i = 0; i < n; i++) {
            if(length[maxI] < length[i]) {
                maxI = i;
            }
        }

        System.out.print("The original array is: ");
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        System.out.println("The longest increasing subsequence contains " + length[maxI] + " elements.");

        int[] subseq = new int[length[maxI]];
        for (int i = subseq.length -1; i >= 0; i--) {
            subseq[i] = nums[maxI];
            maxI = prev[maxI];

        }

        System.out.print("The elements are: ");
        for (int i = 0; i < subseq.length; i++) {
            System.out.print(subseq[i] + " ");
        }
    }
}

