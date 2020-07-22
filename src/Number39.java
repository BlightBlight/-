import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：
所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 

示例 1:
输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]

示例 2:
输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

public class Number39 {
	int[] candidates;
	List<List<Integer>> ans = new ArrayList<>();
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	if(candidates.length == 0) {
    		return ans;
    	}
        this.candidates = candidates;
        /*
         * 把候选数组排个序，选取的数不能比前面选的数还要小，即 “更深层的边上的数值不能比它上层的边上的数值小”，按照这种策略，剪枝就可以去掉重复的组合。
         * 具体看图
         */
        Arrays.sort(candidates);
        
        //利用栈来存储每轮存放的数字
        Stack<Integer> pre = new Stack<>();
        backtrack(target, 0, pre);
        
        return ans;
    }
    
    //回溯主体
    public void backtrack(int target, int start, Stack<Integer> pre) {
    	if(target == 0) {
    		ans.add(new ArrayList<>(pre));
    		return;
    	}
    	
    	//尝试放入数字，直到不行才回退
    	for(int i = start; i < candidates.length && target - candidates[i] >= 0 ; i++) {
    		pre.add(candidates[i]);
    		backtrack(target - candidates[i], i, pre);
    		//回溯，把上一个数字出栈，再下一轮
    		pre.pop();
    	}
    }
    
    public static void main(String[] args) {
    	int[] candidates = {2,3,5};
    	int target = 8;
    	Number39 n = new Number39();
    	n.combinationSum(candidates, target);
    }
}
