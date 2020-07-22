import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

/*
 * 回溯法加剪枝法
 */
public class Number47 {
	List<List<Integer>> ans = new ArrayList<>();
	int[] nums = new int[] {};
    public List<List<Integer>> permuteUnique(int[] nums) {
    	if(nums.length == 0) {
    		return ans;
    	}
    	
    	this.nums = nums;
    	Arrays.sort(nums);
    	int[] hasUsed = new int[nums.length];
    	Stack<Integer> pre = new Stack<>();
    	backtrack(pre, hasUsed);
    	
        return ans;
    }
    
    //回溯主体
    public void backtrack(Stack<Integer> pre, int[] hasUsed) {
    	if(pre.size() == nums.length) {
    		ans.add(new ArrayList<>(pre));
    		return;
    	}
    	
    	//尝试放入数字
    	for(int i = 0; i < nums.length; i++) {
        	//剪枝,一个节点只能使用一次
            if(hasUsed[i] != 1){
            	//去除重复的值，比如说1,4,4,4,4,5，循环到第三个位置开始，第三个位置的值为4，上一个位置的也为4，且上一个位置没使用，则冲突，跳过
				if(i > 0 && nums[i] == nums[i - 1] && hasUsed[i - 1] != 1) {
					continue;
				}
            
    		pre.push(nums[i]);
    		hasUsed[i] = 1;
    		backtrack(pre, hasUsed);
    		hasUsed[i] = 0;
    		pre.pop();
    		}
    	}
    }
    
    public static void main(String[] args) {
    	int[] nums = {1,1,2};
    	Number47 n = new Number47();
    	System.out.println(n.permuteUnique(nums).toString());
    }
}
