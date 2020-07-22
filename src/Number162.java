/*
峰值元素是指其值大于左右相邻值的元素。
给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
你可以假设 nums[-1] = nums[n] = -∞。
	
示例 1:
输入: nums = [1,2,3,1]
输出: 2
解释: 3 是峰值元素，你的函数应该返回其索引 2。

示例 2:
输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5 
解释: 你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。

说明:
你的解法应该是 O(logN) 时间复杂度的。
*/

/*
 * 将nums数组中的任何给定序列视为交替的升序和降序序列
 * 通过利用这一点，以及“可以返回任何一个峰作为结果”的要求，我们可以利用二分查找来找到所需的峰值元素
 * 若该元素恰好位于降序序列或者一个局部下降坡度中(通过将nums[i]与右侧比较判断)，则说明峰值会在本元素的左边
 * 于是，我们将搜索空间缩小为mid的左边(包括其本身)，并在左侧子数组上重复上述过程。
 * 若该元素恰好位于升序序列或者一个局部上升坡度中(通过将nums[i]与右侧比较判断)，则说明峰值会在本元素的右边
 * 于是，我们将搜索空间缩小为mid的右边，并在右侧子数组上重复上述过程
 */

public class Number162 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r) {
            int mid = (l + r) >>> 1;
            if(nums[mid] > nums[mid + 1]) {
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
    
	public static void main(String[] args) {
		int[] nums = {1,2,1,3,5,6,4};
		
		Number162 n = new Number162();
		n.findPeakElement(nums);
	}
}
