import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个包含n个整数的数组nums和一个目标值target
判断nums中是否存在四个元素a，b，c，d
使得a + b + c + d的值与target相等
找出所有满足条件且不重复的四元组。

注意：
答案中不可以包含重复的四元组。

示例：
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

public class Number18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        
        List<List<Integer>> ans = new ArrayList<>();
        int left = 0, right = 0, sum = 0;
        /*
         * 四个指针遍历，i，j，left，right
         * 每个指针都要去重，i，j去重标准是当前这个等于前一个
         * left，right去重标准是当前这个等于下一个
         * 因为i，j是固定的，left，right是浮动的
         */
        for(int i = 0; i < nums.length - 3; i++) {
        	//最小的数都大于target的1/4，后面的不用看了
        	if(nums[i] > target/4) {
                break;
        	}
        	//去重
        	if(i > 0 && nums[i] == nums[i-1]) {
        		continue;
        	}
        	
        	for(int j = i + 1; j < nums.length - 2; j++) {
        		//去重
        		if(j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
        		}
	        	left = j + 1;
	        	right = nums.length - 1;
	        	while(left < right) {
		        	sum = nums[i] + nums[j] + nums[left] + nums[right];
		        	if(sum == target) {
		        		ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
		        		//去重
		        		while(left < right && nums[left] == nums[left+1]) {
		        			left++;	
		        		}
		        		//去重
		        		while(left < right && nums[right] == nums[right-1]) {
		        			right--;	
		        		}
		        		left++;
		        		right--;
		        	}else if(sum < target) {
		        		left++;
		        	}else if(sum > target) {
		        		right--;
		        	}
		        }
        	}
        }
        return ans;
    }
	
	
    public static void main(String[] args) {
    	int[] i = {4,-9,-2,-2,-7,9,9,5,10,-10,4,5,2,-4,-2,4,-9,5};
    	Number18 n = new Number18();
    	List<List<Integer>> list = n.fourSum(i, -13);
    	System.out.println(list.toString());
    }
}
