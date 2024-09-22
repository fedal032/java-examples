package educative.twoHeaps;

import java.util.*;

public class MaxCapital {
    public static void main(String[] args) {
        System.out.println("Cap = " + maximumCapital(1, 2, new int[]{1, 2, 2, 3}, new int[]{2, 4, 6, 8}));

    }

    public static int maximumCapital(int c, int k, int[] capitals, int[] profits) {
//        Queue<Integer> minHeap = new PriorityQueue<>();
//        Map<Integer, Integer> usedCap = new HashMap<>();
//
//        for (int pr = 0; pr < k; pr++) {
//            for (int i = 0; i < capitals.length; i++) {
//                if (!usedPos.contains(i) && c >= capitals[i]) {
//                    minHeap.offer(profits[i]);
//                }
//            }
//            if (!minHeap.isEmpty()) {
//                c += minHeap.poll();
//                usedCap.put()
//                minHeap.clear();
//            }
//        }

        return c;
    }
}
