import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]
*/

/*
 * 遍历三个有效位置curr_pos 以放置点。
 * 检查从上一个点到现在点中间的部分是否有效:
 * 是:放置该点。
 * 检查全部 3个点是否放好:
 * 是:将结果添加到输出列表中。
 * 否:继续放下一个点 backtrack(curr_pos, dots - 1)。
 * 回溯，移除最后一个点。
 * 看图93好理解
 */

public class Number93 {
	String s;
	int n;
	LinkedList<String> segments = new LinkedList<String>();
	ArrayList<String> output = new ArrayList<String>();
	
	//检查放置的数字是否合法
	public boolean valid(String segment) {
	    int m = segment.length();
	    if (m > 3) {
	      return false;
	    }
	    //注意:不能出现以0开头的两位以上数字,比如012,08...
	    return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
	  }

	public void update_output(int curr_pos) {
	    String segment = s.substring(curr_pos + 1, n);
	    //最后剩余数字若合法则添加
	    if(valid(segment)) {
	      segments.add(segment);
	      output.add(String.join(".", segments));
	      //移除刚刚添加的
	      segments.removeLast();
	    }
	}

	/*
	 * prev_pos：先前放置的数字的位置
	 * dots：还要放置多少数字
	 */
	public void backtrack(int prev_pos, int dots) {
		//max_pos为字符串可以放置数字的最大位置
		int max_pos = Math.min(n - 1, prev_pos + 4);
		for(int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
			String segment = s.substring(prev_pos + 1, curr_pos + 1);
			if(valid(segment)) {
				segments.add(segment);
				if(dots - 1 == 0) {
					update_output(curr_pos);
				}else {
					backtrack(curr_pos, dots - 1);
				}
				segments.removeLast();
			}
	    }
	}

	public List<String> restoreIpAddresses(String s) {
		this.n = s.length();
	    this.s = s;
	    backtrack(-1, 3);
	    return output;
	}

	public static void main(String[] args) {
		String s = "25525511135";
		Number93 n = new Number93();
		n.restoreIpAddresses(s);
	}
}
