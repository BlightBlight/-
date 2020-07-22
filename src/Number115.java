/*
给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
一个字符串的一个子序列是指
通过删除一些(也可以不删除)字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

示例 1:
输入: S = "rabbbit", T = "rabbit"
输出: 3
解释:
如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)
rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^

示例 2:
输入: S = "babgbag", T = "bag"
输出: 5
解释:
如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
(上箭头符号 ^ 表示选取的字母)
babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
*/

/*
 * 每个字母只有两种情况，选择这个字母和不选这个字母
 * 这里是从后往前规划！
 * 这里我们用一个二维数组dp[i][j]对应于从S[i，S_len) 中能选出多少个 T[j，T_len)
 * 当 i == S_len，意味着S是空串，此时dp[i][j]，j取任何值都为 0
 * 当 j == T_len，意味着T是空串，此时dp[i][j]，i取任何值都为 1
 * 然后状态转移，如果求dp[i][j]
 * T[i] == S[j]，当前字符相等，那就对应两种情况
 * 选择当前字母，那么由S(i+1, S_Len)匹配T(j+1, T_Len)就行了，即dp[i+1][j+1]
 * 不选择当前字母，那么由S(i+1, S_Len)匹配T(j, T_Len)就行了，即dp[i+1][j]
 * dp[i][j] = dp[i+1][j+1] + dp[i+1][j]
 * T[i] != S[j]，只有一种情况，不选择S的当前字母
 * dp[i][j] = dp[i+1][j]
 */

public class Number115 {
	public int numDistinct(String s, String t) {
	int sLen = s.length();
    int tLen = t.length();
    int[][] dp = new int[sLen + 1][tLen + 1];
    //当T为空串时，所有的s对应于 1
    for(int i = 0; i <= sLen; i++) {
        dp[i][tLen] = 1;
    }

    //T每次增加一个字母
    for(int i = tLen - 1; i >= 0; i--) {
        //S每次增加一个字母
        for(int j = sLen - 1; j >= 0; j--) {
            //如果当前字母相等
            if(t.charAt(i) == s.charAt(j)) {
                //对应于两种情况，选择当前字母和不选择当前字母
                dp[j][i] = dp[j+1][i+1] + dp[j+1][i];
            //如果当前字母不相等
            }else {
                dp[j][i] = dp[j+1][i];
            }
        }
    }
    return dp[0][0];
	}
	
	public static void main(String[] args) {
		String s = "rabbbit", t = "rabbit";
		
		Number115 n = new Number115();
		n.numDistinct(s, t);
	}
}
