package leetcode.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FindSum3Closest {

    //O(N^2)
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;

        if (nums.length == 3) {
            for (int num : nums) {
                sum += num;
            }
            return sum;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.poll();
        while (queue.poll() != null) {

        }

        int res = 0;
        int diff = Integer.MAX_VALUE;

        //O(N)
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) { // O(N)
                sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }
                else if (sum > target) {
                    if (sum - target < diff) {
                        diff = sum - target;
                        res = sum;
                    }
                    right--;
                }
                else {
                    if (target - sum < diff) {
                        diff = target - sum;
                        res = sum;
                    }
                    left++;
                }
            }
        }
        return res;
    }
}
