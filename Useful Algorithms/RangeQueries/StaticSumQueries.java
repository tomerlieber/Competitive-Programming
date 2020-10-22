// The arrays is static, i.e., the array values are never updated between the queries.
// We can easily process sum queries on a static array by constructing a prefix sum array.
// The prefix sum arrary can be constructed in O(n) time and each query is O(1).
public class StaticSumQueries {

    public static void main (String[] args) {

        int[] nums = {1, 3, 4, 8, 6, 1, 4, 2};

        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i -1] + nums[i];
        }

        int a = 3, b = 6;

        System.out.println(String.format("The sum in range [%d,%d] is %d", a, b, prefixSum[b] - prefixSum[a-1]));
    }
}
