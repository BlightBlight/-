import java.util.Stack;

/*
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
示例 1:
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"

示例 2:
输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
*/

/*
 * 对于遇到的每个'(' ，我们将它的下标放入栈中
 * 对于遇到的每个')'，我们弹出栈顶的元素并将当前元素的下标与弹出元素下标作差，得出当前有效括号字符串的长度
 * 通过这种方法，我们继续计算有效子字符串的长度，并最终返回最长有效子字符串的长度。
 */
public class Number32 {
	/*
	 * 1.s[i] = ')'且s[i-1] = '('，也就是字符串形如"……()"，我们可以推出
	 * dp[i] = dp[i-2] + 2
	 * 我们可以进行这样的转移，是因为结束部分的 "()" 是一个有效子字符串，并且将之前有效子字符串的长度增加了2
	 * 2.s[i] = ')'且s[i-1] = ')'，也就是字符串形如"……))"，我们可以推出
	 * 如果s[i - dp[i-1] - 1] = '('，那么
	 * dp[i] = dp[i-1] + dp[i - dp[i-1] -2] + 2
	 * 这背后的原因是如果倒数第二个 ')'是一个有效子字符串的一部分(记为 sub_s)
	 * 对于最后一个')'，如果它是一个更长子字符串的一部分，那么它一定有一个对应的'('
	 * 它的位置在倒数第二个')'所在的有效子字符串的前面（也就是 sub_s的前面)
	 * 因此，如果子字符串sub_s的前面恰好是'('，那么我们就用2加上sub_s的长度dp[i-1]去更新dp[i]
	 * 除此以外，我们也会把有效子字符串 sub_s之前的有效子字符串的长度也加上，也就是加上dp[i − dp[i−1] − 2]
	 * 像是 ()(()) 这种，要把第二个'('之前有效的字符串长度也加上
	 * 看图32好理解
	 */
/*	public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for(int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }*/

    public int longestValidParentheses(String s) {
    	if(s.length() <2 ) {
    		return 0;
    	}
    	
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLength = 0;
        for(int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) == '(') {
                stack.push(i);
            }else {
                stack.pop();
                if(stack.empty()) {
                    stack.push(i);
                }else {
                	maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
    
    public static void main(String[] args) {
    	String s = "())";
    	Number32 n = new Number32();
    	System.out.println(n.longestValidParentheses(s));
    }
}
