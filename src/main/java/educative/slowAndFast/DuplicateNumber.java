package educative.slowAndFast;

public class DuplicateNumber
{
    public static void main(String[] args)
    {
        System.out.println(findDuplicate(new int[] {3,4,4,4,2}));
        System.out.println(findDuplicate(new int[] {1,1}));
        System.out.println(findDuplicate(new int[] {1,3,4,2,2}));
        System.out.println(findDuplicate(new int[] {1,2,2}));
    }

    public static int findDuplicate(int[] nums) {

        int slow = nums[0];
        int fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast)
                break;
        }

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return fast;
    }
}
