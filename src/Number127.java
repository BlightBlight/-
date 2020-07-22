import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:
如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

示例 1:
输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
输出: 5
解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
     
示例 2:
输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
输出: 0
解释: endWord "cog" 不在字典中，所以无法进行转换。
*/

public class Number127 {
	//单词长度
	private int wordLength = 0;
	/*
	 * 存放所有单词替换方式
	 * key为通用状态，value为通用状态所有可能的单词，比如
	 * key为*ot，value为hot,lot
	 */
	private HashMap<String, ArrayList<String>> allComboDict = new HashMap<String,ArrayList<String>>();
	
	/*
	 * BFS方式遍历每个节点下一层节点
	 * visited为当前已遍历节点，othersVisited为反方向已遍历节点
	 */
	private int visitWordNode(Queue<Pair<String, Integer>> Q, HashMap<String, Integer> visited,
			HashMap<String, Integer> othersVisited) {
		Pair<String, Integer> node = Q.remove();
		//当前节点单词
		String word = node.getKey();
		//单词层次
		int level = node.getValue();

		for(int i = 0; i < this.wordLength; i++) {
			//当前遍历节点单词
			String newWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
			/*
			 * BFS遍历寻找当前节点下一层节点
			 * 比如word为dot，则下一层节点可以到达的有*ot，d*t，do*
			 * 则可以到达的节点为hot，lot，dog
			 */
			for(String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
				/*
				 * 若正方向与反方向都遍历到同一个节点，则说明该节点就是最短路径，返回正路径长度+反路径长度
				 */
				if(othersVisited.containsKey(adjacentWord)) {
					return level + othersVisited.get(adjacentWord);
				}
				
				/*
				 * 若当前节点没访问过，则加入当前节点为下一层节点
				 */
				if(!visited.containsKey(adjacentWord)) {
					visited.put(adjacentWord, level + 1);
					Q.add(new Pair<String, Integer>(adjacentWord, level + 1));
				}
			}
		}
		return -1;
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if(!wordList.contains(endWord)) {
			return 0;
		}
		// Since all words are of same length.
		this.wordLength = beginWord.length();
		
		/*
		 * 为每个单词做预处理，找出所有的通用状态，比如
		 * hot或lot都可以得到*ot，则*ot的value为hot，lot
		 */
		wordList.forEach(word -> {
			for(int i = 0; i < wordLength; i++) {
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
				ArrayList<String> transformations = this.allComboDict.getOrDefault(newWord, new ArrayList<String>());
				transformations.add(word);
				this.allComboDict.put(newWord, transformations);
			}
		});
		
		 //正方向BFS寻找节点，key为节点单词，value为层次
		Queue<Pair<String, Integer>> Q_begin = new LinkedList<Pair<String, Integer>>();
		//反方向BFS寻找节点
		Queue<Pair<String, Integer>> Q_end = new LinkedList<Pair<String, Integer>>();
		Q_begin.add(new Pair<String, Integer>(beginWord, 1));
		Q_end.add(new Pair<String, Integer>(endWord, 1));

		/*
		 * 存放正方向已访问过的节点(当前节点和下一层节点)，防止重复遍历，key为单词，value为层次
		 * dot下一层节点可以到达的有dog，hot，lot
		 * hot在上一层已经遍历过了，也就是在往回走，则去掉hot
		 * 在上一层的下一层节点(就是当前层节点)中有lot，也就是当前层要遍历lot，与当前层节点重复，则去掉lot
		 */
		HashMap<String, Integer> visitedBegin = new HashMap<String, Integer>();
		//存放反方向已访问过的节点
		HashMap<String, Integer> visitedEnd = new HashMap<String, Integer>();
		visitedBegin.put(beginWord, 1);
		visitedEnd.put(endWord, 1);
		
		while(!Q_begin.isEmpty() && !Q_end.isEmpty()) {
			//正方向BFS遍历一层
			int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
			if(ans > -1) {
				return ans;
			}

			//反方向BFS遍历一层
			ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
			if(ans > -1) {
				return ans;
			}
		}
		return 0;
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

		Number127 n = new Number127();
		System.out.println(n.ladderLength(beginWord, endWord, wordList));
	}
}
