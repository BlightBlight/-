/*
给定一个字符串s，找到s中最长的回文子串。你可以假设s的最大长度为 1000。

示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"
*/

/*
 * 动态规划
 * i为字符串开头位置，j为字符串结尾位置
 * P(i,j) = P(i+1,j−1) and S[i]==S[j]
 */
public class Number5 {
    public String longestPalindrome(String s) {
    	String answer = ""; 
        int maxLen = 0;//最大长度
    	boolean [][] target = new boolean[s.length()][s.length()];
    	
    	//长度为len的字符串
    	for(int len = 1; len <= s.length(); len++) {
    		//从第1个字符开始遍历长度为len的字符串
    		for(int start = 0; start < s.length(); start++) {
    			int end = start + len - 1;
    			//下标越界，下一轮
    			if(end >= s.length()) {
    				break;
    			}
    			/*
    			 * 长度为1和2的单独判断下
    			 * 求 长度为1和长度为2的P(i,j)时不能用上边的公式，因为我们代入公式后会遇到P[i][j]中i > j的情况
    			 * 比如求P[1][2]的话，我们需要知道P[1+1][2-1]=P[2][1]
    			 * 而P[2][1]代表着S[2,1]是不是回文串，显然是不对的，所以我们需要单独判断。
    			 */
    			target[start][end] = (len == 1 || len == 2 || target[start + 1][end - 1]) && (s.charAt(start) == s.charAt(end));
    			if(target[start][end] && len > maxLen) {
    				answer = s.substring(start, end + 1);
                }
    		}
    	}
    	return answer;
    }
    
    public static void main(String[] args) {
    	String s = "abcba";
    	Number5 n = new Number5();
    	System.out.println(n.longestPalindrome(s));
    }
}
