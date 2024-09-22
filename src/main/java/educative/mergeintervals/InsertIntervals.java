package educative.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertIntervals {

    public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {
        // Initialize variables to help in iterating over the existing intervals array
        int currInt = 0;
        int start = 0;
        int end = 1;
        int intLen = existingIntervals.length;

        // Initialize a list to store the output temporarily
        List<int[]> res = new ArrayList<>();

        // Append all intervals that start before the new interval to the output list
        while (currInt < intLen && existingIntervals[currInt][start] < newInterval[start]) {
            res.add(existingIntervals[currInt]);
            currInt++;
        }

        //System.out.println("The output before we add the new interval is: " + Arrays.deepToString(res.toArray()));

        // If the new interval starts after the end of the last interval appended to the output list,
        // just append the new interval to the output list.
        if (res.isEmpty() || res.get(res.size() - 1)[end] < newInterval[start]) {
            res.add(newInterval);
        } else {
            // Otherwise, merge the two intervals
            res.get(res.size() - 1)[end] = Math.max(res.get(res.size() - 1)[end], newInterval[end]);
        }

        // Copy any remaining intervals to the output list,
        // similarly merging any overlapping intervals as we go
        while (currInt < intLen) {
            if (res.get(res.size() - 1)[end] < existingIntervals[currInt][start]) {
                res.add(existingIntervals[currInt]);
            } else {
                res.get(res.size() - 1)[end] = Math.max(res.get(res.size() - 1)[end], existingIntervals[currInt][end]);
            }
            currInt++;
        }

        return res.toArray(new int[0][0]);
    }

    // Driver Code
    public static void main(String[] args) {
        int[][] newIntervals = {
                {5, 7},
                {8, 9},
                {10, 12},
                {1, 3},
                {1, 10}
        };

        int[][][] existingIntervals = {
                {{1, 2}, {3, 5}, {6, 8}},
                {{1, 3}, {5, 7}, {10, 12}},
                {{8, 10}, {12, 15}},
                {{5, 7}, {8, 9}},
                {{3, 5}}
        };

        for (int i = 0; i < newIntervals.length; i++) {
            System.out.println("Existing intervals: " + Arrays.deepToString(existingIntervals[i]));
            System.out.println("New interval: [" + newIntervals[i][0] + ", " + newIntervals[i][1] + "]");
            insertInterval(existingIntervals[i], newIntervals[i]);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
