/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
你可以假设数组中无重复元素。

示例 1:
输入: [1,3,5,6], 5
输出: 2

示例 2:
输入: [1,3,5,6], 2
输出: 1

示例 3:
输入: [1,3,5,6], 7
输出: 4

示例 4:
输入: [1,3,5,6], 0
输出: 0
*/

public class Number35 {
    public int searchInsert(int[] nums, int target) {
    	if(nums.length == 0) {
    		return 0;
    	}
    	
        int mid = 0;
        int start = 0;
        int end = nums.length;
        
        while(start < end) {
        	mid = (start + end) >>> 1;
        	
        	/*
        	 * 要找的是等于目标值的第1个数的索引，若不存在，则返回它将会被按顺序插入的位置
        	 * 如果中位数小于目标值，左边界left就至少是mid + 1，可以排除
        	 * 如 1，3，5，6 mid = 1， target = 5，则答案最少为mid + 1就是nums[2]
        	 * 如果中位数大于等于目标值，还不能够肯定它就是我们要找的数，中位数以及中位数的左边都有可能是符合题意的数
        	 * 如 1(2)，3，5，6 mid = 1， target = 2，则答案可能是0(第一个数字是2)，也可能是1(第一个数字是1)，不能排除
        	 */
        	if(nums[mid] < target) {
        		start = mid + 1;
        	}else {
        		end = mid;
        	}
        }
        return start;
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[] {1,3,5,6};
    	int target = 5;
    	Number35 n = new Number35();
    	System.out.println(n.searchInsert(nums, target));
    }
}
