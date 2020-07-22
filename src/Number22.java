import java.util.LinkedList;
import java.util.List;

/*
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/
/*
 * i对括号的一个组合，在i - 1对括号的基础上得到
 * i对括号的一个组合，一定以左括号 "(" 开始（不一定以 ")" 结尾），为此，我们可以枚举右括号 ")" 的位置，得到所有的组合
 * 枚举的方式就是枚举左括号 "(" 和右括号 ")" 中间可能的合法的括号对数
 * 而剩下的合法的括号对数在与第一个左括号 "(" 配对的右括号 ")" 的后面，这就用到了以前的状态。
 * dp[i] = "(" + dp[可能的括号对数] + ")" + dp[剩下的括号对数]
 * dp[i] = "(" + dp[j] + ")" + dp[i- j - 1] , j = 0, 1, ...，i - 1
 */
public class Number22 {
	public List<String> generateParenthesis(int n) {
		//动态规划数组，以LinkedList方式写
		LinkedList<LinkedList<String>> result = new LinkedList<LinkedList<String>>();
		if(n == 0) {
			return result.get(0);
		}
		
		LinkedList<String> list0 = new LinkedList<String>();
		list0.add("");
		result.add(list0);
		LinkedList<String> list1 = new LinkedList<String>();
		list1.add("()");
		result.add(list1);
		
		for(int i = 2; i <= n; i++) {
			LinkedList<String> temp = new LinkedList<String>();
			for(int j = 0; j < i; j++) {
				//str1和str2就是dp[j]和dp[i - j - 1]
				List<String> str1 = result.get(j);
				List<String> str2 = result.get(i - j - 1);
				for (String s1 : str1) {
					for (String s2 : str2) {
						//枚举右括号的位置
						String el = "(" + s1 + ")" + s2;
						temp.add(el);
					}
				}
			}
			result.add(temp);
		}
		return result.get(n);
	}
	
    public static void main(String[] args) {
    	int i = 3;
    	Number22 n = new Number22();
    	List<String> l = n.generateParenthesis(i);
    	System.out.println(l);
    }
}
