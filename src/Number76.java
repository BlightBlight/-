import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

示例：
输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"

说明：
如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
*/

public class Number76 {
    public String minWindow(String s, String t) {
    	if (s.length() == 0 || t.length() == 0) {
            return "";
        }
    	
    	//t字符串的每个字符存储到Map
        Map<Character, Integer> tMap = new HashMap<Character, Integer>();
        for(int i = 0; i < t.length(); i++) {
        	tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        
        //requied指需要多少个字符相同
        int required = tMap.size();
        
        //存储所需字符在s字符串中位置
        List<Pair<Integer, Character>> positionMap = new ArrayList<Pair<Integer, Character>>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (tMap.containsKey(c)) {
            	positionMap.add(new Pair<Integer, Character>(i, c));
            }
        }
        //在字符串s中匹配tMap，当sMap中每个元素大于等于tMap中每个元素所需个数时，则匹配完成
        Map<Character, Integer> sMap = new HashMap<Character, Integer>();
        /*
         * left指左边第一个匹配字符，right指右边最后匹配一个字符
         * formed指是否和tMap所需字符个数相符，每当一个字符个数符合，formed就+1,直到与requied相同
         */
        int left = 0, right = 0, formed = 0;
        //第一个元素是单词长度，第二个是单词起始位置，第三个是单词终止位置
        int[] ans = {-1, 0, 0};
        
        //滑动窗口
        while (right < positionMap.size()) {
        	//一直向右滑动，直到匹配
            char c = positionMap.get(right).getValue();
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            
            //当sMap中元素与tMap中元素个数刚好相同时，则formed+1，表示一个元素匹配完成
            if (sMap.get(c).intValue() == tMap.get(c).intValue()) {
                formed++;
            }
            
            //当全部字符匹配时
            while (formed == required) {
                c = positionMap.get(left).getValue();
                int start = positionMap.get(left).getKey();
                int end = positionMap.get(right).getKey();
                
                //第一个单词或单词长度小于之前匹配长度时
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }
                
                //将左边匹配完的字符-1
                sMap.put(c, sMap.get(c) - 1);
                
                /*
                 * 当sMap中所需字符个数小于tMap字符个数时，formed-1，表示这个字符不匹配
                 * 假设现在left指向B，所需A=1，B=1，C=1，sMap中B有两个，但是只需要一个，则不会进入if分支
                 * 再次left++，相当于把左边的窗口向右滑动，直到有字符小于所需个数时
                 */
                if (sMap.get(c).intValue() < tMap.get(c).intValue()) {
                    formed--;
                }
                left++;
            }
            right++;   
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
    
	public static void main(String[] args) {
		String s = "ADOBECODEBANC", t = "ABC";
		Number76 n = new Number76();
    	System.out.println(n.minWindow(s, t));
	}
}
