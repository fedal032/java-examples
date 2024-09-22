package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//              dp[
//   2              4 1 8 3
//  3 4              6 5 7
// 6 5 7              3 4
//4 1 8 3              2

public class TriangleMinPath {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3 ,4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotalMemo(triangle));

    }


    public static int minimumTotalSavePrevLine(List<List<Integer>> triangle) {
        if (triangle.size() == 1)
            return triangle.get(0).get(0);

        List<Integer> prevRow = triangle.get(0); // base - first row

        for (int row = 1; row < triangle.size(); row++) {
            List<Integer> currRow = new ArrayList<>(triangle.get(row).size());

            for (int col = 0; col <= row; col++) {
                int smallestAbove = Integer.MAX_VALUE;

                if (col > 0) {
                    smallestAbove = prevRow.get(col - 1);
                }

                if (col < row) {
                    smallestAbove = Math.min(smallestAbove, prevRow.get(col));
                }

                currRow.add(smallestAbove + triangle.get(row).get(col));
            }
            prevRow = currRow;
        }

        return Collections.min(prevRow);

    }

//              dp[
//   2              4 1 8 3
//  3 4              6 5 7
// 6 5 7              3 4
//4 1 8 3              2
    public static int minimumTotalMemo(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] dp = new int[len + 1]; //все нули

        for (int i = len - 1; i >= 0; i--) {    // идем с низу вверх
            List<Integer> currRow = triangle.get(i);
            int curRowLength = currRow.size();     // берем длинну
            for (int j = 0; j < curRowLength; j++) {       // перебираем
                dp[j] = currRow.get(j) + Math.min(dp[j], dp[j + 1]);
            }
            System.out.println(" i = " + i + ", dp = " + Arrays.toString(dp));
        }
        return dp[0];
    }
}
