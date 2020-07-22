import java.util.ArrayList;
import java.util.List;

/*
给出一个无重叠的 ，按照区间起始端点排序的区间列表。
在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

示例 1:
输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
输出: [[1,5],[6,9]]

示例 2:
输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出: [[1,2],[3,10],[12,16]]
解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
*/

public class Number57 {
	public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int length = intervals.length;
        if (length == 0) {
        	ans.add(newInterval);
            return ans.toArray(new int[0][]);
        }
        int i;
        for(i = 0; i < length; i++) {
        	/*
        	 * 验证是否可能重合，若当前区间右边界比新区间左边界大，则有可能
        	 * break了也不一定是重合
        	 * 比如[3,5]和[1,2]
        	 */
            if(intervals[i][1] >= newInterval[0]) {
                break;
            }
            ans.add(intervals[i]);
        }
        //循环到末尾了，并不是因可能重合而break的
        if(i == length) {
        	ans.add(newInterval);
            return ans.toArray(new int[0][]);
        }
        //并没有重合，且新区间小于现区间，先插入新区间
        if(intervals[i][0] > newInterval[1]) {
        	ans.add(newInterval);
        } 
        //重合了,处理所有重合
        else {
            int left = Math.min(newInterval[0], intervals[i][0]), right = Math.max(newInterval[1], intervals[i][1]);
            i++;
            //重叠部分，下一个区间的左边界比前一个区间的右边界小
            while (i < length && intervals[i][0] <= right) {
            	right = Math.max(newInterval[1], intervals[i][1]);
                i++;
            }
            ans.add(new int[] {left, right});
        }
        //处理重合之后的
        while(i < length) {
        	ans.add(intervals[i++]);
        }
        return ans.toArray(new int[0][]);
    }
    
    public static void main(String[] args) {
    	int[][] intervals = {
    					{2,10}
    					
    					/*{1,4},
    					{4,5}*/
    			
		    			/*{1,4},
						{0,2},
						{3,5}*/
    	                };
    	int[] newInterval = new int[] {0,1};
    	Number57 n = new Number57();
    	int[][] ans = n.insert(intervals, newInterval);
    	for(int[] i : ans) {
    		System.out.print("[");
    		for(int j : i) {
    			System.out.print(j);
    			System.out.print(" ");
    		}
    		System.out.print("]");
    		System.out.print(" ");
    	}
    }
}
