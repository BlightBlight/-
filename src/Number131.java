import java.util.ArrayList;
import java.util.List;

/*
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
返回 s 所有可能的分割方案。

示例:
输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
*/

/*
 * dp[i][j] 表示 s[i, j]
 * dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]
 * 要保证dp[i + 1][j - 1] 中 i + 1 <= j - 1
 * i + 1 <= j - 1
 * 把 j = i + len - 1 代入上式
 * i + 1 <= i + len - 1 - 1
 * 化简得
 * len >= 3
 */

public class Number131 {
	public List<List<String>> partition(String s) {
	    boolean[][] dp = new boolean[s.length()][s.length()];
	    int length = s.length();
	    for(int len = 1; len <= length; len++) {
	        for(int i = 0; i <= s.length() - len; i++) {
	            int j = i + len - 1;
	            dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
	        }
	    }
	    List<List<String>> ans = new ArrayList<>();
	    partitionHelper(s, 0, dp, new ArrayList<String>(), ans);
	    return ans;
	}
	
/*	//分治
	private List<List<String>> partitionHelper(String s, int start, boolean[][] dp) {
	    if(start == s.length()) {
	        List<String> list = new ArrayList<>();
	        List<List<String>> ans = new ArrayList<>();
	        ans.add(list);
	        return ans;
	    }
	    List<List<String>> ans = new ArrayList<>();
	    for(int i = start; i < s.length(); i++) {
	        if(dp[start][i]) {
	            String left = s.substring(start, i + 1);
	            //在右边分割的每种可能中加入左边回文串
	            for(List<String> l : partitionHelper(s, i + 1, dp)) {
	                l.add(0, left);
	                ans.add(l);
	            }
	        }
	    }
	    return ans;
	}*/
	
	//回溯
	private void partitionHelper(String s, int start, boolean[][] dp, List<String> temp, List<List<String>> res) {
	    //到了空串就加到最终的结果中
	    if (start == s.length()) {
	        res.add(new ArrayList<>(temp));
	    }
	    //在不同位置切割
	    for(int i = start; i < s.length(); i++) {
	        //如果是回文串就加到结果中
	        if(dp[start][i]) {
	            String left = s.substring(start, i + 1);
	            temp.add(left);
	            partitionHelper(s, i + 1, dp, temp, res);
	            temp.remove(temp.size() - 1);
	        }
	    }
	}

	public static void main(String[] args) {
		String s = "aab";
		
		Number131 n = new Number131();
		n.partition(s);
	}
}
