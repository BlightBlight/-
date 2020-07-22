import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
给定一个字符串s和一些长度相同的单词words。找出s中恰好可以由words中所有单词串联形成的子串的起始位置。
注意子串要与words中的单词完全匹配，中间不能有其他字符，但不需要考虑words中单词串联的顺序。

示例 1：
输入：
  s = "barfoothefoobarman",
  words = ["foo","bar"]
输出：[0,9]
解释：
从索引0和9开始的子串分别是"barfoo"和"foobar"。
输出的顺序不重要, [9,0] 也是有效答案。

示例 2：
输入：
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
输出：[]
*/

/*
 * 题目中没有要求明确排列顺序，所以不需要求出全排列
 * 由于单词长度一致，所以可以以单词长度为单位进行寻找
 * 运用HashMap来存储单词，key为单词，value为单词出现次数
 * 第一个HashMap存储要寻找的所有单词
 * 第二个HashMap从字符串中依次寻找单词存进HashMap
 * 若第二个HashMap中的单词在第一个HashMap中不存在，直接跳过该单词，从下一个单词开始寻找
 * 若存在，则比较单词出现次数，若出现次数超过第一个HashMap，则通过滑动窗口(滑动长度为单词长度)滑过该单词第一次出现的地方(记得在HashMap中删除滑过单词次数)
 * 若第二个HashMap与第一个HashMap完全匹配，则输出下标。字符串中第一个单词次数减1，跳过该单词，进行下一次寻找
 */
public class Number30 {
    public List<Integer> findSubstring(String s, String[] words) {
    	List<Integer> ans = new ArrayList<>();
    	if(words.length == 0) {
    		return ans;
    	}
    	
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for(String word : words) {
        	wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        
        int wordLength = words[0].length();
        String word = "";
        HashMap<String, Integer> hasWords = new HashMap<>();
        
        /* 
	     * 以单词长度为界，有wordLength种找法
	     * 如wordLength为3，有i=0,i=1,i=2三种开始方法，当i=3时就是i=0的第二次循环
	     */
        for(int i = 0; i < wordLength; i++) {
        	int num = 0;
        	hasWords.clear();//每次循环开始清除
        	for(int j = i; j < s.length(); j += wordLength) {
        		//最后一次超过范围
        		if(j + (wordLength - 1) >= s.length()) {
        			break;
        		}
        		
        		word = s.substring(j, j + wordLength);//每次分割的单词
        		num++;
        		if(wordsMap.containsKey(word)) {
        			hasWords.put(word, hasWords.getOrDefault(word, 0) + 1);
        			
        			while(hasWords.get(word) > wordsMap.get(word)) {
        				int length = (num - 1) * wordLength;//单词总长度
        				String firstWord = s.substring(j - length, j - length + wordLength);
        				hasWords.put(firstWord, hasWords.getOrDefault(firstWord, 0) - 1);
        				num--;
        			}
        		}else {
        			hasWords.clear();
        			num = 0;
        		}
        		
        		if(num == words.length) {
        			ans.add(j - (num - 1) * wordLength);
        			String firstWord = s.substring(j - ((num - 1) * wordLength), j - ((num - 1) * wordLength)+ wordLength);
    				hasWords.put(firstWord, hasWords.getOrDefault(firstWord, 0) - 1);
    				num--;
        		}
        	}
        }
        return ans;
    }
    
    public static void main(String[] args) {
    	String haystack = "aaaaaaaa";
    	String[] words = new String[] {"aa","aa","aa"};
    	Number30 n = new Number30();
    	
    	System.out.println(n.findSubstring(haystack, words));
    }
}
