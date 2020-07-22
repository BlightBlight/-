/*
一条包含字母 A-Z 的消息通过以下方式进行了编码：
'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

示例 1:
输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。

示例 2:
输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
*/

public class Number91 {
    public int numDecodings(String s) {
        if(s.length() == 0) {
        	return 0;
        }
        //dp[i]代表字符串s[i, s.len-1]，也就是s从i开始到结尾的字符串的解码方式。
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        //最后一个数字不等于0就初始化为 1
        if(s.charAt(s.length() - 1) != '0') {
            dp[s.length() - 1] = 1;
        }
        //从后往前遍历
        for(int i = s.length() - 2; i >= 0; i--) {
        	//当前数字等于0时直接跳过，0不代表任何字母
            if(s.charAt(i) == '0') {
                continue;
            }
            
            /*
             * 23223232322|2 和 2322323232|22
             * 如果最后两个数字小于等于26，则dp[i] = dp[i+1] + dp[i+2]
             * 23223232323|2 和 2322323232|32
             * 如果最后两个数字大于26，则dp[i] = dp[i+1]
             * dp[i+2]不能加，如果加了则出错，因为数字大于26不代表任何字母
             */
        	if(10 * (s.charAt(i) - '0') + s.charAt(i + 1) - '0' <= 26) {
        		dp[i] = dp[i+1] + dp[i+2];
        	}else {
        		dp[i] = dp[i+1];
        	}
        }
        return dp[0];
    }
    
	public static void main(String[] args) {
		String s = "232232323232";
		Number91 n = new Number91();
    	System.out.println(n.numDecodings(s));
	}
}
