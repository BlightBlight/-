/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
输入: [2,2,3,2]
输出: 3

示例 2:
输入: [0,1,0,1,0,1,99]
输出: 99
*/

/*
 * 如果所有数字都出现了3次，那么每一列的1的个数就一定是3的倍数
 * 之所以有的列不是3的倍数，就是因为只出现了1次的数贡献出了1
 * 所以所有不是3的倍数的列写1，其他列写0，就找到了这个出现1次的数。
 */

public class Number137 {
	public int singleNumber(int[] nums) {
	    int ans = 0;
	    //考虑每一位
	    for(int i = 0; i < 32; i++) {
	        int count = 0;
	        //考虑每一个数
	        for(int j = 0; j < nums.length; j++) {
	            //当前位是否是 1
	            if((nums[j] >>> i & 1) == 1) {
	                count++;
	            }
	        }
	        //1的个数是否是 3 的倍数
	        if (count % 3 != 0) {
	            ans = ans | 1 << i;
	        }
	    }
	    return ans;
	}

	public static void main(String[] args) {
		int[] nums = {0,1,0,1,0,1,99};
		
		Number137 n = new Number137();
		n.singleNumber(nums);
	}
}
