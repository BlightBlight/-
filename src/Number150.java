import java.util.ArrayList;
import java.util.List;

/*
根据逆波兰表示法，求表达式的值。
有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：
整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

示例 1：
输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9

示例 2：
输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6

示例 3：
输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*/

public class Number150 {
    public int evalRPN(String[] tokens) {
        List<Integer> stack = new ArrayList<>();
        int temp = 0;
        for(int i = 0; i < tokens.length; i++) {
        	switch(tokens[i]) {
        	case "+":
        		temp = stack.remove(stack.size() - 2) + stack.remove(stack.size() - 1);
        		stack.add(temp);
        		break;
        	case "-":
        		temp = stack.remove(stack.size() - 2) - stack.remove(stack.size() - 1);
        		stack.add(temp);
        		break;
        	case "*":
        		temp = stack.remove(stack.size() - 2) * stack.remove(stack.size() - 1);
        		stack.add(temp);
        		break;
        	case "/":
        		temp = stack.remove(stack.size() - 2) / stack.remove(stack.size() - 1);
        		stack.add(temp);
        		break;
        	default:
        		stack.add(Integer.valueOf(tokens[i]));
        		break;
        	}
        }
        return stack.remove(stack.size() - 1);
    }
    
	public static void main(String[] args) {
		String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
		
		Number150 n = new Number150();
		System.out.println(n.evalRPN(tokens));
	}
}