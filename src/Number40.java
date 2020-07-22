import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/*
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：
所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 

示例 1:
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

示例 2:
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
*/

public class Number40 {
	int[] candidates;
	List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	if(candidates.length == 0) {
    		return ans;
    	}
    	
    	Arrays.sort(candidates);
    	this.candidates = candidates;
    	
    	Stack<Integer> pre = new Stack<>();
    	backtrack(target, 0, pre);
    	
        return ans;
    }
    
    //回溯主体
    public void backtrack(int target, int start, Stack<Integer> pre) {
    	if(target == 0) {
    		ans.add(new ArrayList<>(pre));
    	}
    	
    	HashSet<Integer> hashSet = new HashSet<>();
    	
    	//尝试放入数字
    	for(int i = start; i < candidates.length && target - candidates[i] >= 0; i++) {
        	//剪枝,同一个父节点的子节点必须是不同的值
            if(hashSet.contains(candidates[i])){
                continue;
            }else{
                hashSet.add(candidates[i]);
            }
    		pre.push(candidates[i]);
    		backtrack(target - candidates[i], i+1, pre);
    		pre.pop();
    	}
    }
    
    
    public static void main(String[] args) {
    	int[] candidates = {10,1,2,7,6,1,5};
    	int target = 8;
    	Number40 n = new Number40();
    	List<List<Integer>> ans = n.combinationSum2(candidates, target);
    	System.out.println(ans.toString());
    }
}
