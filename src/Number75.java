/*
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
注意:
不能使用代码库中的排序函数来解决这道题。

示例:
输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]

进阶：
一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？
*/

public class Number75 {
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        
        for(int i = 0; i <= end; i++) {
        	//等于2就从后往前依次放
        	if(nums[i] == 2){
        		int temp = nums[i];
        		nums[i] = nums[end];
        		nums[end] = temp;
        		//i--是因为要继续判断刚刚交换过来的元素是什么
                i--;
                end--;
            }
        	//等于0就从前往后依次放
        	else if(nums[i] == 0) {
            	int temp = nums[i];
        		nums[i] = nums[start];
        		nums[start] = temp;
                start++;
            }
        }
    }
    
	public static void main(String[] args) {
		int[] matrix = {2,0,2,1,1,0};
		Number75 n = new Number75();
    	n.sortColors(matrix);
	}
}
