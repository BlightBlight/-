import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
给出一个区间的集合，请合并所有重叠的区间。

示例 1:
输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2:
输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
*/

/*
 * 先排序，再比较
 */
public class Number56 {
    public int[][] merge(int[][] intervals) {
    	if(intervals.length == 0) {
    		return new int[][] {};
    	}
    	Arrays.sort(intervals, new Comparator<int[]>() {
    		public int compare(int[] o1, int[] o2) {
    			if(o1[0] == o2[0]) {
    				return o1[1] - o2[1];
    			}
    			return o1[0] - o2[0];
    		}
    	});
    	List<int[]> list = new ArrayList<>();
    	int left = intervals[0][0], right = intervals[0][1];//左边界与右边界
    	
    	for(int i = 1; i < intervals.length; i++) {
    		//重叠部分，下一个区间的左边界比前一个区间的右边界小
    		if (intervals[i][0] <= right) {
                right = Math.max(right, intervals[i][1]);//更新右边界
            } 
    		//无重叠部分
    		else {
                list.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
    	}
    	list.add(new int[]{left, right});//最后一个区间
    	
    	int[][] ans = new int[list.size()][2];
    	for(int i = 0; i < list.size(); i++) {
    		ans[i][0] = list.get(i)[0];
    		ans[i][1] = list.get(i)[1];
    	}
        return ans;
    }
    
    public static void main(String[] args) {
    	int[][] nums = {
    					/*{1,3},
    					{2,6},
    					{8,10},
    					{15,18}*/
    					
    					/*{1,4},
    					{4,5}*/
    			
		    			{1,4},
						{0,2},
						{3,5}
    	                };
    	Number56 n = new Number56();
    	int[][] ans = n.merge(nums);
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
