import java.util.Arrays;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
问总共有多少条不同的路径？

例如，上图是一个7 x 3 的网格。有多少可能的路径？

说明：m 和 n 的值均不超过 100。

示例 1:
输入: m = 3, n = 2
输出: 3

解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右

示例 2:
输入: m = 7, n = 3
输出: 28
*/


/*
 * 由于只能从左边往右走或者从上边往下走，所以
 * 终点格子的路径总数 = 左边格子路径总数+上边格子路径总数
 * 所以可推出动态方程为
 * dp[i][j] = dp[i-1][j] + dp[i][j-1]
 * dp[i][j]等于左边格子的路径总数+上边格子的路径总数
 * 初始化第一行和第一列的值
 * dp[0][j] = 1，dp[i][0] = 1
 * 从0,0走到0,j只有一种走法，从0,0走到i,0只有一种走法
 */

public class Number62 {
	//初始版本，空间复杂度为O(m*n)
/*	
	public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++) {
         	dp[0][i] = 1;
        }
        for(int i = 0; i < m; i++) {
        	dp[i][0] = 1;
        }
        for(int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];  
    }
*/
	/*
	 * 改良版本，空间复杂度为O(2n)
	 * 因为我们每次只需要 dp[i-1][j],dp[i][j-1]
	 * 所以我们只要记录这两个数
	 */
/*	 
	public int uniquePaths(int m, int n) {
	        int[] pre = new int[n];//上一轮数组
	        int[] cur = new int[n];//这一轮数组
	        Arrays.fill(pre, 1);
	        Arrays.fill(cur,1);
	        for(int i = 1; i < m;i++){
	            for(int j = 1; j < n; j++){
	            //cur[j-1]代表dp[i][j-1],pre[j]代表dp[i-1][j]，i-1就是上一轮
	                cur[j] = cur[j-1] + pre[j];
	            }
	            pre = cur.clone();
	        }
	        return pre[n-1]; 
	    }
*/
	
	/*
	 * 最终改良版本，空间复杂度为O(n)
	 * 虽然复杂度降低，但是理解难度大幅上涨，要所有人都理解才能这么写
	 */
	public int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
            	/*
            	 * 配合图question_62理解，cur指竖列
            	 * 设cur[] = {1,2,3}
            	 * 在第一轮计算时，cur[] = {1, 2, 3}
            	 * 计算过程为1 + 2 = 3
            	 * 此时cur[j]代表dp[i-1][j]，也就是要计算的数字左边
            	 * 此时cur[j-1]代表dp[i][j-1]，也就是要计算的数字上边由于是第一轮，所以dp[i][j-1]与dp[i][j]永远为1，这是关键
            	 * 正是由于第一轮cur[j-1]永远为1，所以可以在一个数组中存储信息
            	 * 接着把cur[j]置换成cur[j] + cur[j-1]
            	 * 此时cur[j]代表本轮最终结果
            	 * 第二轮计算时，cur[] = {1, 3, 3}
            	 * 计算过程为3 + 3 = 6
            	 * 此时cur[j]代表dp[i-1][j]，也就是要计算的数字左边
            	 * 此时cur[j-1]代表dp[i][j-1]，也就是要计算的数字上边，在第一轮中已经把这个数字计算出来并且放在cur[j-1]位置上
            	 * 接着把cur[j]置换成cur[j] + cur[j-1]
            	 * 此时cur[j]代表本轮最终结果
            	 */
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }

	public static void main(String[] args) {
		int m = 7, n1 = 3;
    	Number62 n = new Number62();
    	System.out.println(n.uniquePaths(m, n1));
    }
}
