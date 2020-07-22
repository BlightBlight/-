import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
说明：
分隔时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。

示例 1：
输入:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
输出:
[
  "cats and dog",
  "cat sand dog"
]

示例 2：
输入:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
输出:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
解释: 注意你可以重复使用字典中的单词。

示例 3：
输入:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
输出:
[]
*/

public class Number140 {
	public List<String> wordBreak(String s, List<String> wordDict) {
		int len = s.length();
		// 状态定义：以 s[i] 结尾的子字符串是否符合题意
		boolean[] dp = new boolean[len];

		//加快遍历速度，将List转换成HashSet
		Set<String> wordSet = new HashSet<>();
		for (String word : wordDict) {
			wordSet.add(word);
		}

		//动态规划问题一般都有起点，起点也相对好判断一些
		//dp[0] = wordSet.contains(s.charAt(0));
		for(int r = 0; r < len; r++) {
			//整体是否在wordSet中
			if(wordSet.contains(s.substring(0, r + 1))) {
				dp[r] = true;
				continue;
			}
			//前后分割是否在wordSet中
			for(int l = 0; l < r; l++) {
				//dp[l]写在前面会更快一点，否则还要去切片，然后再放入hash表判重
				if(dp[l] && wordSet.contains(s.substring(l + 1, r + 1))) {
					dp[r] = true;
					//这个break很重要，一旦得到dp[r] = True，循环不必再继续
					break;
				}
			}
		}

		List<String> res = new ArrayList<>();
		//只有当最后一个值为true才可能有结果
		if(dp[len - 1]) {
			LinkedList<String> queue = new LinkedList<>();
			dfs(s, len - 1, wordSet, res, queue, dp);
			return res;
		}
		return res;
	}

	private void dfs(String s, int end, Set<String> wordSet, List<String> res, LinkedList<String> queue, boolean[] dp) {
		//整体在wordSet中存在，则添加到res
		if(wordSet.contains(s.substring(0, end + 1))) {
			queue.addFirst(s.substring(0, end + 1));

			StringBuilder sb = new StringBuilder();
			for(String word : queue) {
				sb.append(word);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length() - 1);
			res.add(sb.toString());

			queue.removeFirst();
		}
		
		//从前往后分割，若前后分割部分在wordSet中存在，将后半部分加入queue，前半部分继续递归分割
		for(int i = 0; i < end; i++) {
			if(dp[i]) {
				String suffix = s.substring(i + 1, end + 1);

				if(wordSet.contains(suffix)) {
					queue.addFirst(suffix);
					dfs(s, i, wordSet, res, queue, dp);
					queue.removeFirst();
				}
			}
		}
	}
    
	public static void main(String[] args) {
		String s = "pineapplepenapple";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("apple");
		wordDict.add("pen");
		wordDict.add("applepen");
		wordDict.add("pine");
		wordDict.add("pineapple");
		
		Number140 n = new Number140();
		n.wordBreak(s, wordDict);
	}
}
