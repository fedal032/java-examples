package leetcode.dp;

public class ShipCapacity {
    public int shipWithinDays(int[] weights, int days) {
        //range from max(weights[i]) to sum(wights[i])
        // min capacity = max(weights[0..n]) // N days
        // max capacity = sum(weights[0..n]) // 1 day
        // range capacities .. binary search
        // to avoid overflow = l + (r-l)/2
        //

        int maxLoad = 0;
        int totalLoad = 0;

        for (Integer w : weights) {
            totalLoad += w;
            maxLoad = Math.max(maxLoad, w);
        }

        int l = maxLoad;
        int r = totalLoad;

        while (l < r) {
            int mid = l + (r-l)/2;
            if (checkCap(weights, mid, days)) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        return l;
    }

    public boolean checkCap (int[] weights, int cap, int days) {
        int daysNeeded = 1;
        int curLoad = 0;

        for (int w : weights) {
            curLoad += w;
            if (curLoad > cap) {
                daysNeeded++;
                curLoad = w;
            }
        }

        return daysNeeded <= days;
    }
}
