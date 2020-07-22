import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回一个空列表。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

示例 1:
输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
输出:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]

示例 2:
输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
输出: []

解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
*/

/*
 * 整体思路：
 * 先通过BFS遍历生成树，通过剪枝、Hash等方法减少时间
 * 当BFS遍历完结或者找到最终节点提前终结时，返回路径
 */

public class Number126{
	List<List<String>> ans = new ArrayList<>();
	//利用BFS得到当前节点以及下一层节点
	Map<String, ArrayList<String>> map = new HashMap<>();
	
	List<String> wordList;
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		//如果不含有结束单词，直接结束
		if(!wordList.contains(endWord)) {
			return ans;
		}
		
		this.wordList = wordList;
		bfs(beginWord, endWord);
		return ans;
	}
	
	/*
	 * 通过BFS把所有节点遍历且放到map中
	 * map中key为每个节点，value为该节点能到达的下一层节点
	 */
	public void bfs(String beginWord, String endWord) {
		//存放每次遍历得到的路径
	    Queue<List<String>> queue = new LinkedList<>();
	    //临时List，将第一个单词放入queue
	    List<String> l1 = new ArrayList<>();
	    l1.add(beginWord);
	    queue.offer(l1);
	    //是否找到最终单词标记
	    boolean isFound = false;
	    //为了加快速度将ArrayList转换成HashSet
	    Set<String> dict = new HashSet<>(wordList);
	    //已经出现过的所有节点，即当前层节点以及下一层节点
	    Set<String> visited = new HashSet<>();
	    visited.add(beginWord);
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        //存放当前节点的下一层节点
	        Set<String> subVisited = new HashSet<>();
	        for(int j = 0; j < size; j++) {
	        	//当前路径
	            List<String> path = queue.poll();
	            //得到当前路径最末尾单词
	            String temp = path.get(path.size() - 1);
	            //一次性得到所有可以到达的下一层节点
	            ArrayList<String> neighbors = getNeighbors(temp, dict);
	            for(String neighbor : neighbors) {
					/*
					 * 只考虑之前没有出现过的单词，比如 
					 * dot下一层节点可以到达的有dog，hot，lot
					 * hot在上一层已经遍历过了，也就是在往回走，则去掉hot
					 * 在上一层的下一层节点(就是当前层节点)中有lot，也就是当前层要遍历lot，与当前层节点重复，则去掉lot
					 */
	                if(!visited.contains(neighbor)) {
	                    //到达结束单词
	                    if(neighbor.equals(endWord)) {
	                        isFound = true;
	                        path.add(neighbor);
	                        ans.add(new ArrayList<String>(path));
	                        path.remove(path.size() - 1);
	                    }
	                    //加入当前单词
	                    path.add(neighbor);
	                    queue.offer(new ArrayList<String>(path));
	                    path.remove(path.size() - 1);
	                    subVisited.add(neighbor);
	                }
	            }
	        }
	        visited.addAll(subVisited);
	        //找到最终节点，直接返回
	        if(isFound) {
	            break;
	        }
	    }
	}
	
	/*
	 * 返回符合题意的只有一个字母不同的单词列表
	 */
	private ArrayList<String> getNeighbors(String node, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char chs[] = node.toCharArray();

		for(char ch = 'a'; ch <= 'z'; ch++) {
			for(int i = 0; i < chs.length; i++) {
				if (chs[i] == ch) {
					continue;
				}
				char old_ch = chs[i];
				chs[i] = ch;
				if(dict.contains(String.valueOf(chs))) {
					res.add(String.valueOf(chs));
				}
				chs[i] = old_ch;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String beginWord = "hit", endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");

		Number126 n = new Number126();
		System.out.println(n.findLadders(beginWord, endWord, wordList).toString());
	}
}
