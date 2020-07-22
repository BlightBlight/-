import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

/*
 * 字典序排序算法
 * 从右到左找出第一个非递增元素n(选择左边那个)，意思就是左边比右边小
 * 再次从右到左找出比n大的最小的元素m
 * 交换n和m
 * 将n后面的元素排序
 * 再次循环，直到找不出非递增元素
 */

public class Number46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        
        if(nums.length == 0) {
        	return ans;
        }
        if(nums.length == 1) {
    		ArrayList<Integer> temp = new ArrayList<>();
    		for(int k = 0; k < nums.length; k++) {
    			temp.add(nums[k]);
    		}
            ans.add(temp);
        	return ans;
        }
        
        Arrays.sort(nums);
		ArrayList<Integer> temp = new ArrayList<>();//存储一次排序
		
		//第一次排序
		for(int k = 0; k < nums.length; k++) {
			temp.add(nums[k]);
		}
        ans.add(temp);
        
        int n = 0;//非递增元素
        int m = 0;//比n大的最小元素
        int circle = simpleCircle(nums.length);//排列个数
        for(int i = 0; i < circle; i++) {
	        for(int j = nums.length - 2; j >= 0; j--) {
	        	if(nums[j] < nums[j+1]) {
	        		n = j;
	        		m = j + 1;
	        		for(int k = nums.length - 1; k > j; k--) {
	        			if(nums[k] > nums[n] && nums[k] < nums[m]) {
	        				m = k;
	        			}
	        		}
	        		swap(nums, n, m);
	        		Arrays.sort(nums, n + 1, nums.length);
	        		temp = new ArrayList<>();
	        		for(int k = 0; k < nums.length; k++) {
	        			temp.add(nums[k]);
	        		}
	        		ans.add(temp);
	        		break;
	        	}
	        }
        }
        return ans;
    }
    
    public void swap(int[] nums, int n, int m) {
    	int temp = nums[n];
    	nums[n] = nums[m];
    	nums[m] = temp;
    }
    
    
	public int simpleCircle(int num) {
		int sum = 1;
		for(int i = 1; i <= num; i++) {
			sum *= i;
		}
		return sum;// 返回阶乘的值
	}
    
    public static void main(String[] args) {
    	int[] nums = {1};
    	Number46 n = new Number46();
    	System.out.println(n.permute(nums).toString());
    }
}
