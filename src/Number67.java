/*
给定两个二进制字符串，返回他们的和（用二进制表示）。
输入为非空字符串且只包含数字 1 和 0。

示例 1:
输入: a = "11", b = "1"
输出: "100"

示例 2:
输入: a = "1010", b = "1011"
输出: "10101"
*/

public class Number67 {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int  carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        for(;i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.insert(0, sum % 2);
            carry = sum / 2;
        }
        ans.insert(0, carry == 1 ? "1" : "");
        return ans.toString();
    }
    
	public static void main(String[] args) {
		String a = "11";
		String b = "1";
		Number67 n = new Number67();
    	System.out.println(n.addBinary(a, b));
	}
}
