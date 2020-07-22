/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。
( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
请找出其中最小的元素。
注意数组中可能存在重复的元素。

示例 1：
输入: [1,3,5]
输出: 1

示例 2：
输入: [2,2,2,0,1]
输出: 0

说明：
这道题是 寻找旋转排序数组中的最小值 的延伸题目。
允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
*/

public class Number154 {
    public int findMin(int[] nums) {
        int len = nums.length;
        if(len == 0) {
            return 0;
        }
        int left = 0;
        int right = len - 1;
        while(left < right) {
            // int mid = left + (right - left) / 2;
            int mid = (left + right) >>> 1;
            if(nums[mid] > nums[right]) {
                left = mid + 1;
            }else if (nums[mid] < nums[right]) {
                right = mid;
            }else {
                assert nums[mid] == nums[right];
                right--;
            }
        }
        return nums[left];
    }
    
	public static void main(String[] args) {
		int[] nums = {2,2,2,0,1};
		
		Number154 n = new Number154();
		System.out.println(n.findMin(nums));
	}
}
