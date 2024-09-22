package educative.binarysearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindSingleElem
{
    public static void main(String[] args)
    {
        int[][] inputs = {
                {1, 2, 2, 3, 3, 4, 4},
                {1, 1, 2, 2, 3, 4, 4, 5, 5},
                {1, 1, 2, 2, 3, 3, 4, 5, 5},
                {1, 1, 2, 3, 3},
                {1, 1, 2},
                {0, 2, 2, 3, 3, 4, 4, 5, 5}
        };
        for (int i = 0; i < inputs.length; i++)
        {
            System.out.print(i + 1);
            System.out.println(".\tInput array: " + Arrays.toString(inputs[i]));
            System.out.println("\tSingle element found: " + singleNonDuplicate(inputs[i]));
            System.out.println();

        }
    }

    public static int singleNonDuplicate(int[] nums) // O NLogN
    {
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (left < right)
        {
            mid = left + (right - left) / 2;
            System.out.println("left = " + left + ", right = " + right + ", mid = " + mid);

            System.out.println("odd (1) / even (0) = " + mid % 2);
            if (mid % 2 == 1)
            {  // если нечетный индекс
                mid--;
            }

            System.out.println("pair: nums[mid] = " + nums[mid] + ", nums[mid+1] = " + nums[mid + 1]);
            if (nums[mid] == nums[mid + 1])
            {
                left = mid + 2;
                System.out.println("new left = " + left + ", right = " + right + ", mid = " + mid);
            } else
            {
                right = mid;
                System.out.println("new left = " + left + ", right = " + right + ", mid = " + mid);
            }
        }
        return nums[left];
    }

    // простое решение, перебором после сортировки
    public int singleNonDupl2(int nums[])
    {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) // O(N/2)
        {
            if (nums[i] != nums[i + 1])
                return nums[i];
        }
        return nums[nums.length - 1];
    }

    public int singleNonDupl3(int[] nums)
    {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int num : nums)
        {
            if (!dict.containsKey(num))
                dict.put(num, 1);
            else
                dict.replace(num, dict.get(num) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : dict.entrySet())
        {
            if (entry.getValue() == 1)
                return entry.getKey();
        }

        return 0;
    }

    public int singleNonDupl3For(int[] nums)
    {
        int n = nums.length;

        if (n < 3)
            return nums[0];

        Arrays.sort(nums);

        if (nums[0] != nums[1])
            return nums[0];

        int i = 1;
        while (i < n)
        {
            if (nums[i] != nums[i - 1])
                return nums[i - 1];

            i += 3;
        }
        return nums[n - 1];
    }

}
