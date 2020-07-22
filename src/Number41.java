/*
给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:
输入: [1,2,0]
输出: 3

示例 2:
输入: [3,4,-1,1]
输出: 2

示例 3:
输入: [7,8,9,11,12]
输出: 1

说明:
你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
*/

public class Number41 {
    public int firstMissingPositive(int[] nums) {
    	int length = nums.length;

        //遍历数组，每个元素放到对应的位置。
        for(int i = 0; i < length;) {
            int t = nums[i];
            
            //i + 1 == nums[i] || t == nums[t-1]说明那个位置已经放置正确数字了
            if(i + 1 != nums[i] && t > 0 && t <= length && t != nums[t-1]) {
                //交换
                nums[i] = nums[t - 1];
                nums[t - 1] = t;
            }else {
                i++;
            }
        }

        //找到不对应的数字
        for(int i = 0; i < length; i++) {
            if(i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return length + 1;
    }
    
    public static void main(String[] args) {
    	int[] nums = {3,4,-1,1};
    	Number41 n = new Number41();
    	System.out.println(n.firstMissingPositive(nums));
    }
}
