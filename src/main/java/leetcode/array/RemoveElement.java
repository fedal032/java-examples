package leetcode.array;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    public int removeElement2Pointers(int[] nums, int val) {
        int start = 0;
        int end = nums.length - 1;

        //[0,1,2,2,3,0,4,2], val = 2
        while (start <= end) {
            if (nums[end] == val) {
                end--;
            }
            else {
                if (nums[start] == val) {
                    nums[start] = nums[end];
                    nums[end] = val;
                    end--;
                }
                start++;
            }
        }

        return end + 1;
    }
}
