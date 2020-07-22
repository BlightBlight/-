/*
给定一个非负整数数组，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个位置。

示例 1:
输入: [2,3,1,1,4]
输出: true
解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。

示例 2:
输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
*/

/*
 * 贪心算法
 */
public class Number55 {
/*	
 * //动态规划方法
	int[] nums;
	int[] dp;//存放每个数字是否能到达，0不知道，1可以，-1不行
    public boolean canJump(int[] nums) {
    	if(nums.length == 0) {
    		return false;
    	}
    	
    	this.nums = nums;
    	dp = new int[nums.length];
    	
        dp[nums.length-1] = 1;//最后一个肯定可以

        return backtrack(0);
    }
    
    //回溯主体
    public boolean backtrack(int position) {
    	//不能往前跳且不是最后一格
    	if(nums[position] == 0 && position != nums.length - 1) {
    		dp[position] = -1;
    		return false;
    	}
    	//不是未知情况就返回
    	if(dp[position] != 0) {
    		return (dp[position] == 1) ? true : false;
    	}
    	
    	int min = Math.min(position + nums[position], nums.length - 1);//下一步跳跃位置
    	
        for(int i = position + 1; i <= min; i++) {
        	//从第一个开始跳，到最后一格，从最后一格开始往前返回
        	if (dp[i] != -1 && backtrack(i)) {
        		dp[position] = 1;
                return true;
            }
        }
        dp[position] = -1;
        return false;
    }
    */
	
	/*
	 * 贪心算法
	 * 标记最后为lastPos
	 * 从右往左一个一个找能一步跳到lastPos的位置，把lastPos标记为那一个位置
	 * 再继续往前一个一个找，直到i < 0
	 * 若lastPos为0，则说明第一步有路径跳到最后一步，否则没有
	 */
	public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
    
    public static void main(String[] args) {
    	int[] nums = {3,2,1,0,4};
    	Number55 n = new Number55();
    	System.out.println(n.canJump(nums));
    }
}
