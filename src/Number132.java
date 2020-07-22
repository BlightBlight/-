/*
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
返回符合要求的最少分割次数。

示例:

输入: "aab"
输出: 1
解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
*/

public class Number132 {
    public int minCut(String s) {
        int n = s.length();
        int[] min_s = new int[n];
        //dp[i][j]表示s[i, j]是否是回文串
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++) {
        	//每个字符单独分割的情况
            min_s[i] = i;
            for(int j = 0; j <= i; j++) {
            	/*
            	 * 若j + 1 <= i - 1，则 i - j >= 2，则dp[j + 1][i - 1]范围不出错
            	 * 若j + 1 > i - 1，则i - j < 2，则直接判断s[i] == s[j]即可
            	 */
                if(s.charAt(j) == s.charAt(i) && (i - j < 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
    				/*
    				 *如果从第一个字符开始是回文串，则不用分割，否则
    				 *如果原来的分割方案次数少，就保留原来的，如果是新的分割方案次数少，那么保留新的。
    				 */
                    min_s[i] = (j == 0) ? 0 : Math.min(min_s[i], min_s[j - 1] + 1);
                }
            }
        }
        return min_s[n - 1];
    }
    
	public static void main(String[] args) {
		String s = "aab";
		
		Number132 n = new Number132();
		n.minCut(s);
	}
}
