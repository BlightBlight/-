/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。
( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
你可以假设数组中不存在重复的元素。
你的算法时间复杂度必须是 O(log n) 级别。

示例 1:
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4

示例 2:
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
*/

/*
 * 二分查找法
 * 若target==mid，返回
 * 1.若nums[mid] >= nums[start]，则说明左边数组是有序的
 * 1.1若nums[start]<=target<nums[mid]，则说明目标在左边有序数组中，否则目标在右边数组
 * 2.若nums[mid] < nums[start]，则说明右边数组是有序的
 * 2.1若nums[mid]<target<=nums[end]，则说明目标在右边有序数组中，否则在左边数组
 */
public class Number33 {
    public int search(int[] nums, int target) {
    	if(nums.length == 0) return -1;
    	
        int mid = 0;
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end) {
        	mid = start + (end - start) / 2;
        	if(nums[mid] == target) {
        		return mid;
        	}
        	if(nums[start] <= nums[mid]) {
        		if(target >= nums[start] && target < nums[mid]) {
        			end = mid - 1;
        		}else {
        			start = mid + 1;
        		}
        	}else {
        		if(target <= nums[end] && target > nums[mid]) {
        			start = mid + 1;
        		}else {
        			end = mid - 1;
        		}
        	}
        }
        return -1;
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[] {4,5,6,7,0,1,2};
    	int target = 0;
    	Number33 n = new Number33();
    	
    	System.out.println(n.search(nums, target));
    }
}
