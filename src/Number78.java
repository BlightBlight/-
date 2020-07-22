import java.util.LinkedList;
import java.util.List;

/*
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
说明：解集不能包含重复的子集。

示例:
输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

/*
 * 回溯法
 */
public class Number78 {
	List<List<Integer>> output = new LinkedList<>();
	int[] nums;
	int length;
	
    public List<List<Integer>> subsets(int[] nums) {
    	this.length = nums.length;
    	this.nums = nums;
    	if(nums.length == 0) {
    		output.add(new LinkedList<Integer>());
    		return output;
    	}
    	
    	backtrack(0, new LinkedList<Integer>());
    	
    	return output;
    }
    
    public void backtrack(int start, LinkedList<Integer> cur) {
	    output.add(new LinkedList<Integer>(cur));
    
	    for(int i = start; i < length; i++) {
	    	cur.add(nums[i]);
	    	backtrack(i + 1, cur);
	    	cur.removeLast();
	    }
	}
    
	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		Number78 n = new Number78();
    	List<List<Integer>> ans = n.subsets(nums);
    	for(int i = 0; i < ans.size(); i++) {
    		System.out.println(ans.get(i));
    	}
	}
}
