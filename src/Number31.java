/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
必须原地修改，只允许使用额外常数空间。
以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/*
 * 字典序排序法
 * 从右往左寻找第一个非递增的元素(左边小于右边，选择左边那个)，标记为i
 * 从非递增元素往后寻找大于它的最小一个元素，或者从后往前寻找第一个大于非递增元素的元素
 * 交换这两个元素，i后进行升序排序
 */
public class Number31 {
	public void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while(i >= 0 && nums[i+1] <= nums[i]) {
			i--;
		}
		if(i >= 0) {
			int j = nums.length - 1;
			while(j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	private void reverse(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
    
    public static void main(String[] args) {
    	int[] nums = new int[]{1,3,2};
    	Number31 n = new Number31();
    	
    	n.nextPermutation(nums);
    }
}
