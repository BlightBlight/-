import java.util.LinkedList;
import java.util.List;

/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例:
输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
*/

/*
 * 巧妙运用队列的方法
 * 先把一个数字的字母都进入队列
 * 从队列中取出首元素与下一个数字的每个字母相加入队
 * 重复直到取出队列元素的长度大于循环次数
 */
public class Number17 {
	public List<String> letterCombinations(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		if(digits.isEmpty()) {
			return ans;
		}
		String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ans.add("");
		for(int i = 0; i < digits.length(); i++){
			int x = digits.charAt(i) - '0';
			//第i轮就有i+1个数字
			while(ans.peek().length() == i){
				String t = ans.remove();
				for(char s : mapping[x].toCharArray()) {
					ans.add(t + s);
				}
			}
		}
		return ans;
	}
	
    public static void main(String[] args) {
    	String s = "234";
    	Number17 n = new Number17();
    	n.letterCombinations(s);
    }
}
