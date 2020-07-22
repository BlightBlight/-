/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
输入: [2,2,1]
输出: 1

示例 2:
输入: [4,1,2,1,2]
输出: 4
*/

/*
 * 一个数和 0 做 XOR 运算等于本身：a⊕0 = a
 * 一个数和其本身做 XOR 运算等于 0：a⊕a = 0
 * XOR 运算满足交换律和结合律：a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
 * 将所有数字按照顺序做抑或运算，最后剩下的结果即为唯一的数字
 */

public class Number136 {
	public int singleNumber(int[] nums) {
		int ans = 0;
		for(int num : nums) {
			ans ^= num;
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] nums = {2,2,1};
		
		Number136 n = new Number136();
		n.singleNumber(nums);
	}
}
