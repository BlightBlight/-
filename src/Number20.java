import java.util.Stack;

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
有效字符串需满足
左括号必须用相同类型的右括号闭合
左括号必须以正确的顺序闭合
注意空字符串可被认为是有效字符串

示例 1:
输入: "()"
输出: true

示例 2:
输入: "()[]{}"
输出: true

示例 3:
输入: "(]"
输出: false

示例 4:
输入: "([)]"
输出: false

示例 5:
输入: "{[]}"
输出: true
*/

public class Number20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char temp = ' ';
        boolean ans = true;
        for(int i = 0; i < s.length(); i++) {
        	if(ans == false) {
        		return ans;
        	}
        	if(s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') {
        		if(!stack.isEmpty()) {
        			temp = stack.pop();
        		}
        		switch(temp) {
        		case '(':
        			ans = s.charAt(i) == ')' ? true : false;
        			break;
        		case '{':
        			ans = s.charAt(i) == '}' ? true : false;
        			break;
        		case '[':
        			ans = s.charAt(i) == ']' ? true : false;
        			break;
        			
        		default:
        			return false;
        		}
        	}else {
        		stack.push(s.charAt(i));
        	}
        }
        if(stack.isEmpty()) {
        	return ans;
        }else {
        	return false;
        }
    }
    
    public static void main(String[] args) {
    	String s = "]";
    	Number20 n = new Number20();
    	System.out.println(n.isValid(s));
    }
}
