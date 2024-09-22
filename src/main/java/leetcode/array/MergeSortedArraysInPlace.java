package leetcode.array;

import java.util.Arrays;

public class MergeSortedArraysInPlace
{
    public static void main(String[] args)
    {
        int[] a1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] a2 = new int[]{4, 5, 6};
        int n = 3;
        printIntArray(merge(a1, m, a2, n));
    }

    private static int[] merge(int[] a1, int m, int[] a2, int n)
    {
        int p1 = m - 1;
        int p2 = n - 1;

        for (int p = m + n - 1; p > 0; p--)
        {
            if (p2 < 0)
                break;

            if (p1 >= 0 && a1[p1] > a2[p2])
            {
                a1[p] = a1[p1];
                p1--;
            }
            else
            {
                a1[p] = a2[p2];
                p2--;
            }
        }

        return a1;
    }

    public static void printIntArray(int[] arr)
    {
        System.out.print("res = [ ");
        Arrays.stream(arr).mapToObj(i -> i + " ").forEach(System.out::print);
        System.out.print("]");
    }
}
