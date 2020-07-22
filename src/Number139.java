import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：
拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。

示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。

示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。

示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
*/

/*
 * 看图139
 * 将字符串可视化成一棵树，每一个节点是用end为结尾的前缀字符串。
 * 为了形成这样的一棵树，我们从给定字符串的第一个字符开始(比方说s)，将它作为树的根部，开始找所有可行的以该字符为首字符的可行子串
 * 进一步的，将每一个子字符串的结束字符的下标(比方说i)放在队列的尾部供宽搜后续使用
 * 每次我们从队列最前面弹出一个元素，并考虑字符串s(i + 1, end)作为原始字符串，并将当前节点作为树的根。
 */

public class Number139 {
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> wordDictSet = new HashSet<>(wordDict);
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[s.length()];
		queue.add(0);
		while(!queue.isEmpty()) {
			int start = queue.remove();
			if (visited[start] == 0) {
				//找出所有以s[start]开头的字符串，并将结尾位置放入queue，下一轮继续遍历
				for(int end = start + 1; end <= s.length(); end++) {
					if(wordDictSet.contains(s.substring(start, end))) {
						queue.add(end);
						if (end == s.length()) {
							return true;
						}
					}
				}
				visited[start] = 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "catsanddog";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("cats");
		wordDict.add("dog");
		wordDict.add("sand");
		wordDict.add("and");
		wordDict.add("cat");
		
		Number139 n = new Number139();
		n.wordBreak(s, wordDict);
	}
}
