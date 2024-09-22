package educative.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
We are given an array of closed intervals, intervals, where each interval has a start time and an end time.
The input array is sorted with respect to the start times of each interval.
For example, intervals = [ [1,4], [3,6], [7,9] ]
Your task is to merge the overlapping intervals and return a new output array consisting of only the non-overlapping intervals.
 */
public class MergeInterval {
    public static int[][] mergeIntervalsSorted(int[][] intervals) {
        //[[1,5],[3,7],[4,6]] 3x2
        List<int[]> res = new ArrayList<>();
        int start = 0;
        int end = 1;

        res.add(intervals[0]);
        int currInterval = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][start] <= res.get(currInterval)[end]) {
                if (intervals[i][end] > res.get(currInterval)[end])
                    res.get(currInterval)[end] = intervals[i][end];
            } else {
                currInterval++;
                res.add(intervals[i]);
            }
        }

        return res.toArray(new int[][]{});
    }

    public int[][] mergeUnsorted(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        res.add(intervals[0]);
        int currInt = 0;
        int start = 0;
        int end = 1;

        for(int i = 1; i < intervals.length; i++) {

            if (intervals[i][start] <= res.get(currInt)[end]) {
                if (intervals[i][end] > res.get(currInt)[end])
                    res.get(currInt)[end] = intervals[i][end];
            }
            else {
                currInt++;
                res.add(intervals[i]);
            }
        }

        return res.toArray(new int[][]{});
    }
}
