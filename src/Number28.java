/*
实现strStr()函数。
给定一个haystack字符串和一个needle字符串，在haystack字符串中找出needle字符串出现的第一个位置 (从0开始)。如果不存在，则返回-1。

示例 1:
输入: haystack = "hello", needle = "ll"
输出: 2

示例 2:
输入: haystack = "aaaaa", needle = "bba"
输出: -1

说明:
当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
对于本题而言，当needle是空字符串时我们应当返回0。这与C语言的strstr()以及 Java的indexOf()定义相符。
*/

/*
 * 慢速写法，快速写法请参考KMP算法
 */
public class Number28 {
    public int strStr(String haystack, String needle) {
        if(needle.equals("")) {
        	return 0;
        }
        if(haystack.length() == 0) {
        	return -1;
        }
        
        int target = 0, j = 0;
        for(; target < haystack.length(); target++) {
        	j = 0;
        	if(haystack.charAt(target) == needle.charAt(0)) {
        		for(; j < needle.length(); j++) {
        			if(target + j >= haystack.length() || haystack.charAt(target + j) != needle.charAt(j)) {
        				break;
        			}
        		}
        		if(j == needle.length()) {
        			return target;
        		}
        	}
        }
        return -1;
    }
    
    public static void main(String[] args) {
    	String haystack = "a";
    	Number28 n = new Number28();
    	
    	System.out.println(n.strStr(haystack, ""));
    }
}
