/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。
( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

示例 1:
输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true

示例 2:
输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false

进阶:
这是 搜索旋转排序数组 的延伸题目，本题中的nums可能包含重复元素。
这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
*/

public class Number81 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int start = 0, end = nums.length - 1;
        
        while(start <= end) {
        	int mid = start + (end - start) / 2;
        	
        	if(nums[mid] == target) {
        		return true;
        	}
        	
        	if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
        	
        	if(nums[start] < nums[mid]) {
        		if(nums[mid] > target && nums[start] <= target) {
        			end = mid - 1;
        		}else {
        			start = mid + 1;
        		}
        	}else {
        		if(nums[mid] < target && nums[end] >= target) {
        			start = mid + 1;
        		}else {
        			end = mid - 1;
        		}
        	}
        }
        return false;
    }
    
	public static void main(String[] args) {
		int[] nums = {2,5,6,0,0,1,2};
		int target = 3;
		Number81 n = new Number81();
    	System.out.println(n.search(nums, target));
	}
}
