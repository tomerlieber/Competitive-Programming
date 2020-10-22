import java.util.Arrays;

// Given an array of n numbers and a target sum x, find three array values such that their sum is x,
// or report that no such values exist.
public class ThreeSum {

    // Time complexity = O(n^2)
    public static void main(String[] args) {

        int[] nums = {1, 4, 5, 6, 7, 9, 9, 10};
        int target = 10;

        Arrays.sort(nums);

        int pointer1 = 0;
        int pointer2  = 1;
        int pointer3 = nums.length - 1;

        boolean isFound = false;

        while (pointer1 < pointer2 && pointer2 < pointer3) {

                int currentSum = nums[pointer1] + nums[pointer2] + nums[pointer3];

                if (currentSum == target) {
                    isFound = true;
                    break;
                }
                else if (currentSum < target) {
                    if (pointer2 - pointer1 > 1) {
                        pointer1++;
                    }
                    else {
                        pointer2++;
                    }
                }
                else {
                    pointer3--;
                }
        }

        if (isFound) {
            System.out.print(nums[pointer1] + " + " + nums[pointer2] + " + " + nums[pointer3] + " = " + target);
        }
        else {
            System.out.print("There is no sub array");
        }
    }
}
