/*
给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
如果不存在最后一个单词，请返回 0 。
说明：一个单词是指由字母组成，但不包含任何空格的字符串。

示例:
输入: "Hello World"
输出: 5
*/

public class Number58 {
	public int lengthOfLastWord(String s) {
		int end = s.length() - 1;
		while(end >= 0 && s.charAt(end) == ' ') {
			end--;
		}
		if(end < 0) {
			return 0;
		}
		int start = end;
		while(start >= 0 && s.charAt(start) != ' ') {
			start--;
		}
		return end - start;
	}
    
    public static void main(String[] args) {
    	String s = "Hello World";
    	Number58 n = new Number58();
    	System.out.println(n.lengthOfLastWord(s));
    }
}
