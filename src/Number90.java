import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
说明：解集不能包含重复的子集。

示例:
输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class Number90 {
	List<List<Integer>> ans;
	int[] nums;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	ans = new ArrayList<>();
    	if(nums.length == 0) {    	
    		ans.add(new ArrayList<>());
    		return ans;
    	}
        this.nums = nums;
        Arrays.sort(nums);
        backtrack(0, new ArrayList<>());
        return ans;
    }
    
    public void backtrack(int start, List<Integer> temp) {
    	ans.add(new ArrayList<>(temp));
    	for(int i = start; i < nums.length; i++) {
    		if(i > start && nums[i] == nums[i - 1]) {
                continue;
            }
    		temp.add(nums[i]);
			backtrack(i + 1, temp);
			temp.remove(temp.size() - 1);
    	}
    }
    
	public static void main(String[] args) {
		int[] nums = {1,2,2};
		Number90 n = new Number90();
    	n.subsetsWithDup(nums);
	}
}
