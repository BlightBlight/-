/*
给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。

示例 1:
输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出: true

示例 2:
输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出: false
*/

/*
 * 动态规划
 * 我们定义一个boolean二维数组dp[i][j]来表示s1[0, i)和s2[0, j)组合后能否构成s3[0, i + j)
 * 注意不包括右边界，主要是为了考虑开始的时候如果只取s1，那么s2就是空串，这样的话dp[i][0]就能表示s2取空串
 * 如果dp[i - 1][j] == true，并且s1[i - 1] == s3[i + j - 1]，dp[i][j] = true
 * 之所以要i - 1是因为i = 0指不取字符，但是字符串要从0开始算
 * 当前s1字符与s3字符相同，则dp[i][j]为true
 * 如果dp[i][j - 1] == true，并且s2[j - 1] == s3 [i + j - 1]，dp[i][j] = true
 * 当前s2字符与s3字符相同，则dp[i][j]为true
 * 否则的话，就更新为dp[i][j] = false
 * 如果i为0，或者j为0，那直接判断s2和s3对应的字母或者s1和s3对应的字母即可
 * 
 * 举例如下
 * s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * dp[0][0] = true,从s1不取字符开始，一直到s2取到末尾为止，若符合，则dp[0][s2] = true
 * s1取第一个字符，一直到s2取到末尾为止
 * 一直遍历直到s1和s2都遍历完成，
 */
public class Number97 {
    public boolean isInterleave(String s1, String s2, String s3) {
		if(s1.length() + s2.length() != s3.length()) {
			return false;
		}
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		/*
		 * 0是指不取字符的时候，不是指第一个字符
		 */
		for(int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if(i == 0 && j == 0) {
					dp[i][j] = true;
				}else if(i == 0) {
					dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
				}else if(j == 0) {
					dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
				}else {
					dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
							|| dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
				}
			}
		}
		return dp[s1.length()][s2.length()];
    }
    
	public static void main(String[] args) {
		String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
		Number97 n = new Number97();
		System.out.println(n.isInterleave(s1, s2, s3));
	}
}
