/*
编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。

示例 1:
输入: ["flower","flow","flight"]
输出: "fl"

示例 2:
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。

说明:
所有输入只包含小写字母 a-z 。
*/

/*
 * 随便找一个字符串，选为最长公共前缀字符串
 * 遍历下一个字符串，找出两个字符串最长公共前缀
 * 为空，说明无最长公共前缀，返回空
 * 否则，继续下一个字符串
 */
public class Number14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
        	return "";
        
        String s = strs[0];
        for(int i = 1; i < strs.length; i++) {
        	int j = 0;
        	for(; j < s.length() && j < strs[i].length(); j++) {
        		if(s.charAt(j) != (strs[i].charAt(j))) {
        			break;
        		}
        	}
        	s = s.substring(0, j);
    		if(s.equals("")) {
    			return "";
    		}
        }
        return s;
    }
    
    public static void main(String[] args) {
    	String[] s = {"flower","flow","flight"};
    	Number14 n = new Number14();
    	System.out.println(n.longestCommonPrefix(s));
    }
}
