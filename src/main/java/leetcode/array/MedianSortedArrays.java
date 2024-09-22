package leetcode.array;

public class MedianSortedArrays
{
    public static void main(String[] args)
    {

        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3, 4}));

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m+n];

        int p1 = 0;
        int p2 = 0;
        int i = 0;

        while (p1 < m && p2 < n) {
            if(nums1[p1] <= nums2[p2]) {
                merged[i] = nums1[p1];
                p1++;
                i++;
            }
            else {
                merged[i] = nums2[p2];
                p2++;
                i++;
            }
        }

        while(p1 < m) {
            merged[i] = nums1[p1];
            p1++;
            i++;
        }

        while(p2 < n) {
            merged[i] = nums2[p2];
            p2++;
            i++;
        }

        int l = merged.length;

        if (l%2 == 0) {
            return (double)(merged[(l+1)/2] + merged[(l-1)/2])/2.0;
        }
        else
        {
            return merged[l/2];
        }

    }
}
