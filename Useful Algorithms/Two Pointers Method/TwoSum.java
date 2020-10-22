import java.util.Arrays;

// Given an array of n numbers and a target sum x, find two array values such that their sum is x,
// or report that no such values exist.
public class TwoSum {

    // Time complexity = O(nlogn) because of the sorting
    public static void main(String[] args) {

        int[] nums = {1, 4, 5, 6, 7, 9, 9, 10};
        int target = 20;

        Arrays.sort(nums);

        int pointer1 = 0;
        int pointer2 = nums.length - 1;

        boolean isFound = false;

        while (pointer1 < pointer2) {

            int currentSum = nums[pointer1] + nums[pointer2];
            if (currentSum == target) {
                isFound = true;
                break;
            }
            else if (currentSum < target) {
                pointer1++;
            }
            else {
                pointer2--;
            }
        }

        if (isFound) {
            System.out.print(nums[pointer1] + " and " + nums[pointer2]);
        }
        else {
            System.out.print("There is no sub array");
        }
    }
}
