import java.util.Arrays;

/*
给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
如果数组元素个数小于 2，则返回 0。

示例 1:
输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。

示例 2:
输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。

说明:
你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
*/

/*
 * 我们把箱子能放的的数字个数记为interval，给定的数字中最小的是min，最大的是max
 * 那么箱子划分的范围就是min ~ (min + 1 * interval - 1)、(min + 1 * interval） ~ （min + 2 * interval - 1)...
 * 通过(nums[i] - min) / interval即可得到当前数字应该放到的箱子编号
 * 保证至少有一个空箱子，那么我们就可以断言，箱子内部一定不会产生最大gap
 * 因为在我们的某次计算中，会跳过一个空箱子，那么得到的gap一定会大于interval，而箱子中的数字最大的gap是interval - 1
 * 0, 3, 4, 6, 23, 28, 29, 33, 38，数组中的最小值0和最大值38,并没有参与到interval的计算中，所以它俩可以不放到箱子中，还剩下n - 2个数字
 * 为什么最大值和最小值不参与，因为最小值摆放在第一个箱子的min，最大值摆放在最后一个箱子的max
 * 而我们求gap时只会用到下一个箱子的min - 前一个箱子的max，也就是说第一个箱子的min和最后一个箱子的max不会使用到
 * 鸽巢原理，有n - 2个数字，如果箱子数多于n - 2，那么一定会出现空箱子
 * 总范围是max - min，那么interval = (max - min) / 箱子数，为了使得interval尽量大，箱子数取最小即可，也就是n - 1
 * 所以 interval = (max - min) / n - 1 。这里如果除不尽的话，我们 interval 可以向上取整
 */

public class Number164 {
	public int maximumGap(int[] nums) {
	    if(nums.length <= 1) {
	        return 0;
	    }
	    
	    int n = nums.length;
	    int min = nums[0];
	    int max = nums[0];
	    //找出最大值、最小值
	    for (int i = 1; i < n; i++) {
	        min = Math.min(nums[i], min);
	        max = Math.max(nums[i], max);
	    }
	    if(max - min == 0) {
	        return 0;
	    }
		
	    //算出每个箱子的范围
	    int interval = (int) Math.ceil((double)(max - min) / (n - 1));
	    
	    //每个箱子里数字的最小值和最大值
	    int[] bucketMin = new int[n - 1];
	    int[] bucketMax = new int[n - 1];
	    
	    //最小值初始为 Integer.MAX_VALUE
	    Arrays.fill(bucketMin, Integer.MAX_VALUE);
	    //最大值初始化为-1，因为题目告诉我们所有数字是非负数
	    Arrays.fill(bucketMax, -1);

	    //考虑每个数字
	    for (int i = 0; i < nums.length; i++) {
	        //当前数字所在箱子编号
	        int index = (nums[i] - min) / interval;
	        //最大数和最小数不需要考虑
	        if(nums[i] == min || nums[i] == max) {
	            continue;
	        }
	        //更新当前数字所在箱子的最小值和最大值
	        bucketMin[index] = Math.min(nums[i], bucketMin[index]);
	        bucketMax[index] = Math.max(nums[i], bucketMax[index]);
	    }
		 
	    int maxGap = 0;
	    //min看做第-1个箱子的最大值
	    int previousMax = min;
	    //从第0个箱子开始计算
	    for(int i = 0; i < n - 1; i++) {
	        //最大值是-1说明箱子中没有数字，直接跳过
	        if(bucketMax[i] == -1) {
	            continue;
	        }
	        
	        //当前箱子的最小值减去前一个箱子的最大值
	        maxGap = Math.max(bucketMin[i] - previousMax, maxGap);
	        previousMax = bucketMax[i];
	    }
	    //最大值可能处于边界，不在箱子中，需要单独考虑
	    maxGap = Math.max(max - previousMax, maxGap);
	    return maxGap;
	}

	public static void main(String[] args) {
		int[] nums = {3,6,9,1};
		
		Number164 n = new Number164();
		n.maximumGap(nums);
	}
}
