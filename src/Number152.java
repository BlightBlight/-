/*
给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

示例 1:
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

示例 2:
输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
*/

public class Number152 {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i = 0; i < nums.length; i++){
        	/*
        	 * 当出现负数时，则最大值与最小值交换
        	 * 此时，最小值由于是负数，所以接下来循环中必然选择乘积更小的值
        	 * 当出现第二次负数时，则最大值与最小值交换
        	 * 最小值乘一个负数就是最大值
        	 */
            if(nums[i] < 0){ 
              int tmp = imax;
              imax = imin;
              imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            
            max = Math.max(max, imax);
        }
        return max;
    }

	public static void main(String[] args) {
		int[] nums = {2,3,-2,4,-2,5};
		
		Number152 n = new Number152();
		System.out.println(n.maxProduct(nums));
	}
}
