/*
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
你的算法时间复杂度必须是 O(log n) 级别。
如果数组中不存在目标值，返回 [-1, -1]。

示例 1:
输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]

示例 2:
输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
*/

public class Number34 {
    public int[] searchRange(int[] nums, int target) {
    	if(nums.length == 0) return new int[] {-1, -1};
    	
        int mid = 0;
        int left = 0;
        int right = 0;
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end) {
        	mid = start + (end - start) / 2;
        	if(nums[mid] == target) {
        		left = right = mid;
        		while(left - 1 >= 0 && nums[left-1] == nums[mid]) {
        			left = left - 1;
        		}
        		while(right + 1 < nums.length && nums[right+1] == nums[mid]) {
        			right = right + 1;
        		}
        		return new int[] {left, right};
        	}else if(nums[mid] > target) {
        		end = mid - 1;
        	}else {
        		start = mid + 1;
        	}
        }
        return new int[] {-1, -1};
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[] {1, 1, 2};
    	int target = 1;
    	Number34 n = new Number34();
    	
    	
    	int[] ans = n.searchRange(nums, target);
    	for(int i : ans)
    		System.out.println(i);
    }
}
