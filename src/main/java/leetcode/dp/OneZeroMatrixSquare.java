package leetcode.dp;

public class OneZeroMatrixSquare {
    public static int maxSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;

        int[][] dp = new int[rows + 1][cols + 1];
        int maxSqLen = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    maxSqLen = Math.max(maxSqLen, dp[i][j]);
                }
            }
        }

        return maxSqLen* maxSqLen;
    }
}
