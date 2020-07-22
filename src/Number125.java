/*
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:
输入: "A man, a plan, a canal: Panama"
输出: true

示例 2:
输入: "race a car"
输出: false
*/

public class Number125 {
    public boolean isPalindrome(String s) {
    	s = s.toUpperCase();
        int start = 0, end = s.length() - 1;
        while(start < end) {
        	while(start < end && !Character.isLetterOrDigit(s.charAt(start))) {
        		start++;
        	}
        	while(start < end && !Character.isLetterOrDigit(s.charAt(end))) {
        		end--;
        	}
        	
        	if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
        		return false;
        	}
        	start++;
        	end--;
        }
        return true;
    }
    
	public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";
		
		Number125 n = new Number125();
		System.out.println(n.isPalindrome(s));
	}
}
