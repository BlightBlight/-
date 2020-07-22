import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

/*
 * 排序数组，双指针遍历
 */
public class Number15 {
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len < 3) {
        	return ans;
        }
        
    	Arrays.sort(nums);
    	int left = 0, right = 0, sum = 0;
    	for(int i = 0; i < nums.length; i++) {
			if(nums[i] > 0) {
				//nums[i]大于0，三数相加绝对大于0
				break;
			}
			if(i > 0 && nums[i] == nums[i-1]) {
				//当前数字等于上一个数字，去重
				continue;
			}
			
			left = i + 1;//左指针
			right = nums.length - 1;//右指针
			while(left < right) {
    			sum = nums[i] + nums[left] + nums[right];
    			if(sum == 0) {
    				ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
    				while(left < right && nums[left] == nums[left+1]) {
    					left++;//去重
    				}
    				while(left < right && nums[right] == nums[right-1]) {
    					right--;//去重
    				}
    				left++;
    				right--;
    			}
    			else if(sum < 0) {
    				left++;
    			}
    			else if(sum > 0) {
    				right--;
    			}
			}
    	}
    	return ans;
    }
    
    public static void main(String[] args) {
    	int[] i = {-1, 0, 1, 2, -1, -4};
    	Number15 n = new Number15();
    	System.out.println(n.threeSum(i));
    }
}
