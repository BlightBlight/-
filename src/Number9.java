/*
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:
输入: 121
输出: true

示例 2:
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。

示例 3:
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
*/

public class Number9 {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        boolean flag = true;
        int i = 0;
        while(i < s.length() / 2) {
        	if(s.charAt(i) !=  s.charAt(s.length() - i - 1)) {
        		flag = false;
        	}
        	i++;
        }
        return flag;
    }
    
    public static void main(String[] args) {
    	int i = -10;
    	Number9 n = new Number9();
    	System.out.println(n.isPalindrome(i));
    }
}
