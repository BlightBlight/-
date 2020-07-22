import java.util.ArrayList;
import java.util.List;

/*
给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:
单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。
示例:
输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

示例 2:
输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
     
示例 3:
输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
*/

/*
 * 计算公式 a+x+b+x+c+x+d = maxWidth
 * x指单词间隙数，等于n-1(n为单词数)
 * 当x不能平均分配时，先取最小平均分配，再从左到右依次增加一个空格
 */
public class Number68 {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> ans = new ArrayList<String>();
		int start = 0;//每行开头单词位置
		int end = 0;//放入单词总数
		int curWidth = 0;//当前长度
		for(int i = 0; i < words.length;){
			//放入第一个单词，不用加空格
			if(curWidth == 0) {
	    		curWidth += words[i].length();
	    		end++;
	    		i++;
			}
			/*
			 * 放入一个空格加单词
			 * 这里之所以要放空格是因为每个单词之间最少需要一个空格隔开
			 */
			else if(i < words.length && curWidth + 1 + words[i].length() <= maxWidth) {
				end++;
				curWidth = curWidth + 1 + words[i].length();
	            i++;
			}
			//不能继续放入单词情况
			else {
	    		int x = end - start - 1;//单词间隙数
	    		int averageBlank = (x == 0) ? (maxWidth - curWidth) : (maxWidth - curWidth) / x;//平均空格个数，若没有间隙，则平均空格就是剩余空格
	    		int extraBlank = (x == 0) ? 0 : (maxWidth - curWidth) % x;//剩余空格个数，若没有间隙，则没有剩余空格
	    		StringBuilder sb = new StringBuilder();
	    		//没有间隙情况，也就是只放一个单词的时候
	    		if(x == 0) {
	    			sb.append(words[start]);
	    			sb.append(getStringBlank(averageBlank));
	    		}
	    		//有间隙情况
	    		else {
		    		for(int j = start; j < end; j++) {
		    			sb.append(words[j]);
		    			//加一个单词就加一个间隙
		    			if(x > 0) {
		    				//+1是因为一开始已经放入一个空格
		    				sb.append(getStringBlank(averageBlank + 1));
		    				x--;
		    			}
		    			//剩余空格一次加一个
		    			if(extraBlank != 0) {
		    				sb.append(" ");
		    				extraBlank--;
		    			}
		    		}
	    		}
	    		ans.add(sb.toString());
	    		curWidth = 0;
	    		start = end;
	    	}
		}
		//最后一行单独处理
		StringBuilder sb = new StringBuilder();
	    sb.append(words[start]);
	    for(int i = 1; i < (end - start); i++) {
	        sb.append(" " + words[start + i]);
	    }
	    sb.append(getStringBlank(maxWidth - curWidth));
	    ans.add(sb.toString());
	    return ans;
	}
    
    //得到n个空格
    private String getStringBlank(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
	public static void main(String[] args) {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		int maxWidth = 16;
		Number68 n = new Number68();
    	n.fullJustify(words, maxWidth);
	}
}
