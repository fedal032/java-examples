package educative.binarysearch;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KClosest
{
    public static void main(String[] args)
    {
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5, 6, 7}, 5, 7));
    }

    public static List<Integer> findClosestElements(int[] nums, int k, int target)
    {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("Start time = " + start);
        Pair<Integer, Integer> elem;

        List<Pair<Integer, Integer>> distances = new ArrayList<>();
        for (int num : nums) // O(N)
        {
            int dist = Math.abs(num - target);
            elem = new Pair<>(num, dist);
            distances.add(elem);
        }

        distances.sort((o1, o2) ->                  // O(N*LogN)
        {
            if (o1.getValue().equals(o2.getValue()))
                return o1.getKey().compareTo(o2.getKey());

            return o1.getValue().compareTo(o2.getValue());
        });

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++)             // O(K*LogK)
        {
            res.add(distances.get(i).getKey());
        }
        res.sort(Integer::compareTo);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("End time = " + end + ", duration, mks = " + (end.getNano() - start.getNano()) / 1000000);
        return res;
    }

    public static List<Integer> findClosestElementsFast(int[] nums, int k, int target)
    {
        //init checks
        int len = nums.length;
        List<Integer> res = new ArrayList<>(k);

        // If the length of 'nums' is the same as k, return 'nums'
        if (k == len) {
            for (int num : nums) {
                res.add(num);
            }
            return res;
        }
        // if target is less than or equal to first element in 'nums',
        // return the first k elements from 'nums'
        if (target <= nums[0])
        {
            for (int i = 0; i < k; i++) {
                res.add(nums[i]);
            }
            return res;
        }

        // if target is greater than or equal to last element in 'nums',
        // return the last k elements from 'nums'
        if (target >= nums[len - 1]) {
            for (int i = len - k; i < len; i++) {
                res.add(nums[i]);
            }
            return res;
        }

        // find the first closest element to target using binary search
        int closestElemPos = Arrays.binarySearch(nums, target);
        int winLeft;
        int winRight;
        if (closestElemPos < 0) {
            winLeft = -closestElemPos;
            winRight = -closestElemPos + 1;
        }
        else {

            winLeft = closestElemPos - 1;
            winRight = closestElemPos + 1;
        }


        while ((winRight - winLeft - 1) < k)
        {
            if (winLeft == -1)
            {
                winRight++;
                continue;
            }

            // if window's right pointer is going out of bounds
            // OR if the element pointed to by window's left pointer is closer to target than
            // the element pointed to by window's right pointer, move the window leftward
            if (winRight == nums.length || Math.abs(target - nums[winLeft]) <= Math.abs(target - nums[winRight])) {
                winLeft--;
            }
            // if the element pointed to by window's right pointer is closer to target than
            // the element pointed to by window's left pointer, move the window rightward
            else {
                winRight++;
            }
        }


        for (int i = winLeft + 1; i < winRight; i++)
        {
            res.add(nums[i]);
        }

        return res;

        //Pair<Integer, Integer> elem;
        //List<Pair<Integer, Integer>> distances = new ArrayList<>();
        //for (int num : nums)
        //{
        //    int dist = Math.abs(num - target);
        //    elem = new Pair<>(num, dist);
        //    distances.add(elem);
        //}

        //distances.sort((o1, o2) ->
        //{
        //    if (o1.getValue().equals(o2.getValue()))
        //        return o1.getKey().compareTo(o2.getKey());

        //    return o1.getValue().compareTo(o2.getValue());
        //});

        //List<Integer> res = new ArrayList<>();
        //for (int i = 0; i < k; i++)
        //{
        //    res.add(distances.get(i).getKey());
        //}
        //res.sort(Integer::compareTo);
        //return res;
    }
}
