import java.util.Arrays;

/*
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
*/

//跟15题一样思路
public class Number16 {
    public int threeSumClosest(int[] nums, int target) {
    	Arrays.sort(nums);
    	int left = 0, right = 0, sum = 0, ans = nums[0] + nums[1] + nums[2], distance = 0;
    	for(int i = 0; i < nums.length; i++) {
			left = i + 1;//左指针
			right = nums.length - 1;//右指针
			while(left < right) {
				sum = nums[i] + nums[left] + nums[right];
    			distance = Math.abs(target - sum);
                ans = (distance < Math.abs(target - ans)) ? sum : ans;
    			
    			if(distance == 0) {
    				return ans;
    			}else if(sum < target) {
    				left++;
    			}else if(sum > target) {
    				right--;
    			}
			}
    	}
    	return ans;
    }
	
    public static void main(String[] args) {
    	int[] i = {-1, 2, 1, -4};
    	Number16 n = new Number16();
    	System.out.println(n.threeSumClosest(i, 1));
    }
}
