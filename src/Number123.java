/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

示例 2:
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

示例 3:
输入: [7,6,4,3,1] 
输出: 0 
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
*/

/*
dp[i][j][0 or 1]
0 <= i <= n-1, 0 <= j <= K，
i为天数，j为最多交易数，0表示没有持有股票，1表示持有股票
此问题共i * j * 2种状 态，全部穷举就能搞定。
最终所求为dp[n - 1][K][0]，最后一天，最多允许K次交易，最多获得多少利润

每天都有三种选择：买入、卖出、无操作，我们用buy，sell，rest表示这三种选择，看图123
状态转移方程
dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + prices[i])
解释：今天我没有持有股票，有两种可能
要么是我昨天就没有持有，然后今天选择rest，所以我今天还是没有持有
要么是我昨天持有股票，但是今天我sell了，所以我今天没有持有股票了。
dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i])
解释：今天我持有着股票，有两种可能：
要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
要么我昨天本没有持有，但今天我选择buy，所以今天我就持有股票了。

状态转移方程基本情况
dp[-1][j][0] = 0
解释：因为i是从0开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是0。
dp[-1][j][1] = -infinity
解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
dp[i][0][0] = 0
解释：因为k是从1开始的，所以k = 0意味着根本不允许交易，这时候利润当然是0。
dp[i][0][1] = -infinity
解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。

 */
public class Number123 {
    public int maxProfit(int[] prices) {
    	if(prices.length == 0) {
    		return 0;
    	}
    	
    	int n = prices.length;
    	int max_k = 2;
    	int[][][] dp = new int[n][max_k + 1][2];
    	for(int i = 0; i < n; i++) {
    	    for(int k = max_k; k >= 1; k--) {
    	        if(i - 1 == -1) {
    	        	dp[i][k][0] = 0;
    	        	dp[i][k][1] = -prices[i];
    	        	continue;
    	        }
    	        dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
    	        dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
    	    }
    	}
    	//穷举了 n * max_k * 2 个状态，正确。
    	return dp[n - 1][max_k][0];
    }
    
	public static void main(String[] args) {
		int[] prices = {1,2,4,2,5,7,2,4,9,0};
		
		Number123 n = new Number123();
		System.out.println(n.maxProfit(prices));
	}
}
