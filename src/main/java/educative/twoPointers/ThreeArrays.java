package educative.twoPointers;

import java.util.Arrays;

public class ThreeArrays
{
    public static void main(String[] args)
    {
        System.out.println("Common number is : " + findCommonNumber(new int[]{1, 2, 4, 5}, new int[]{3, 3, 4}, new int[]{2, 3, 4, 5, 6}));
    }

    private static int findCommonNumber(int[] arr1, int[] arr2, int[] arr3)
    {
        Arrays.sort(arr1); //n log (n)
        Arrays.sort(arr2);
        Arrays.sort(arr3);

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length)
        {
            if (arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3])
                return arr1[p1];

            int min = Math.min(Math.min(arr1[p1], arr2[p2]), arr3[p3]);
            if (arr1[p1] == min)
                p1++;

            if (arr2[p2] == min)
                p2++;

            if (arr3[p3] == min)
                p3++;
        }

        return -1;
    }
}
