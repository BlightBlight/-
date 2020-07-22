import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:
输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

说明：
所有输入均为小写字母。
不考虑答案输出的顺序。
*/

/*
 * 将每个字符串按照字母顺序排序，这样的话就可以把 eat，tea，ate 都映射到 aet。其他的类似。
 */
public class Number49 {
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> hash = new HashMap<>();
		for(int i = 0; i < strs.length; i++) {
			char[] s_arr = strs[i].toCharArray();
			// 字符串排序
			Arrays.sort(s_arr);
			// 映射到 key
			String key = String.valueOf(s_arr);
			// 添加到对应的类中
			if(hash.containsKey(key)) {
				hash.get(key).add(strs[i]);
			}else {
				List<String> temp = new ArrayList<String>();
				temp.add(strs[i]);
				hash.put(key, temp);
			}
		}
		return new ArrayList<List<String>>(hash.values());
	}

    public static void main(String[] args) {
    	String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    	Number49 n = new Number49();
    	System.out.println(n.groupAnagrams(strs).toString());
    }
}
