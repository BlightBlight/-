import java.util.HashSet;

/*
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
*/

/*
滑动窗口思想
其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
如何移动？
我们只要把队列的左边的元素移出就行了，直到满足题目要求！
一直继续往右滑动，加入元素，维持这样的队列，找出队列最长的长度！
*/

public class Number3 {
	public int lengthOfLongestSubstring(String s) {
		HashSet<Character> set = new HashSet<>();
		int left = 0, right = 0, size = 0;
		while (left != s.length() && right != s.length()) {
			if (!set.contains(s.charAt(right))) {
				set.add(s.charAt(right));
				right++;
				size = Math.max(right - left, size);
			} else {
				set.remove(s.charAt(left));
				left++;
			}
		}
		return size;
	}
}
