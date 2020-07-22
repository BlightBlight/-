/*
给定一个非负整数数组，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。

示例:
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

说明:
假设你总是可以到达数组的最后一个位置。
*/

/*
 * 贪心算法
 */
public class Number45 {
    public int jump(int[] nums) {
        int max = 0;
        int end = 0;//能跳到的边界
        int step = 0;
        
		/*
		 * 让i < nums.length - 1，是因为开始的时候边界是第0个位置，step已经加 1 了。
		 * 若i < nums.length，则当i到达nums.length - 1时，会再次进入if语句，再加1步
		 */
        for(int i = 0; i < nums.length - 1; i++) {
        	max = Math.max(max, nums[i] + i);//在一次边界中找到能跳到的最远边界，nums[i]+i表示能跳到哪个位置
        	//更新边界
        	if(i == end) {
        		end = max;
        		step++;//步数加1
        	}
        }
        return step;
    }
    
    public static void main(String[] args) {
    	int[] nums = {3,0,1,0,3};
    	Number45 n = new Number45();
    	System.out.println(n.jump(nums));
    }
}
