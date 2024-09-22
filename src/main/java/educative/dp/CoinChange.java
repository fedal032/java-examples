package educative.dp;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 4}, 5));
    }

    public static int recurentCointChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount];
        Arrays.fill(dp, Integer.MAX_VALUE);

        return calcCoinChange(coins, amount, dp);
    }

    private static int calcCoinChange(int[] coins, int remaining, int[] dp) {
        int res = 0;
        if (remaining < 0)
            return -1;
        if (remaining == 0)
            return 0;

        if (dp[remaining - 1] != Integer.MAX_VALUE)
            return dp[remaining - 1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            res = calcCoinChange(coins, remaining - coin, dp);
            if (res >= 0 && res < min)
                min = res + 1;
        }

        if (min != Integer.MAX_VALUE)
            dp[remaining-1] = min;
        else
            dp[remaining - 1] = -1;

        return dp[remaining - 1];
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1]; //
        Arrays.fill(dp, amount + 1); // максимальное число

        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;

        // Iterate through all amounts from 1 to amount
        for (int i = 1; i <= amount; i++) {
            // Try each coin denomination
            for (int coin : coins) {
                if (coin <= i) {
                    // Update the minimum number of coins needed for the current amount
                    // result = min(result, 1 + Change(money-c) )
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still greater than amount, it means the amount cannot be made up by any combination of coins
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
