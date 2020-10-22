// Given an array of n positive integers and a target sum x, and we want to find a sub array whose sum x or report
// there is no sub sub array
public class SubarraySum {

    // Time complexity: O(n)
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 1, 1, 2, 3};
        int target = 8;

        int pointer1 = 0;
        int pointer2 = 0;

        boolean isFound = false;
        int currentSum = nums[pointer1];

        while (pointer1 < nums.length - 1 && pointer2 < nums.length - 1) {

            if (currentSum == target) {
                isFound = true;
                break;
            }
            else if (currentSum < target) {
                pointer2++;
                currentSum += nums[pointer2];
            }
            else {
                currentSum -= nums[pointer1];
                pointer1++;
            }
        }

        if (isFound) {
            for (int i = pointer1; i <= pointer2; i++) {
                System.out.print(nums[i] + " ");
            }
        } else {
            System.out.print("There is no sub array");
        }
    }
}
