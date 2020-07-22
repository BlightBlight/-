/*
给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
下图是字符串 s1 = "great" 的一种可能的表示形式。

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。

示例 1:
输入: s1 = "great", s2 = "rgeat"
输出: true

示例 2:
输入: s1 = "abcde", s2 = "caebd"
输出: false
*/

public class Number87 {
	public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null) {
        	return false;
        }
        if(s1.length() != s2.length()) {
        	return false;
        }
        if(s1.equals(s2)) {
        	return true;
        }
        int[] letters = new int[26];
        //两个字符串字符相同时则letters全为0
        for(int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        //两个字符串的字符不相同，则返回false
        for(int i = 0; i < 26; i++) {
            if(letters[i] != 0) {
            	return false;
            }
        }
        //递归主体
        for(int i = 1; i < s1.length(); i++) {
        	/*
        	 * 两个字符串前后顺序没改变，如great和rgeta，则s1和s2分别比较前半部分和后半部分
        	 * 递归，把前半部分和后半部分再次进入函数，直到比较得到相同
        	 * 比如说s1为gr和s2为rg进入函数，s1为eat和s2为eta进入函数
        	 */
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
        	/*
        	 * 两个字符串前后顺序改变，如great和etarg，则比较s1前半部分和s2后半部分，s1后半部分和s2前半部分
        	 * 递归，把前半部分和后半部分再次进入函数，直到比较得到相同
        	 * 比如说s1为gre和s2为arg进入函数，s1为at和s2为et进入函数
        	 */
            if(isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;   
    }
    
	public static void main(String[] args) {
		String s1 = "great", s2 = "rgeat";
		Number87 n = new Number87();
    	System.out.println(n.isScramble(s1, s2));
	}
}
