/*
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
注意：给定 n 是一个正整数。

示例 1：
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶

示例 2：
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
*/

/*
 * 动态规划
 * dp[n]=dp[n-1]+dp[n-2]
 * 跳到第n阶台阶的跳法只有两种，从n-1阶跳，从n-2阶跳
 */
public class Number70 {
    public int climbStairs(int n) {
    	if(n == 0) {
    		return 0;
    	}
    	
    	if(n == 1) {
    		return 1;
    	}
    	
    	if(n == 2) {
    		return 2;
    	}
    	
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        
        for(int i = 2; i < n; i++) {
        	dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }
    
	public static void main(String[] args) {
		int x = 5;
		Number70 n = new Number70();
    	System.out.println(n.climbStairs(x));
	}
}
