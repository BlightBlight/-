import java.util.HashMap;
import java.util.Map;

/*
给定一个未排序的整数数组，找出最长连续序列的长度。
要求算法的时间复杂度为 O(n)。

示例:
输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
*/

public class Number128 {
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> lookup = new HashMap<>();
		int res = 0;
		for(int num : nums) {
			if(!lookup.containsKey(num)) {
				// 查看左右两边是否可以相连
				int left = (lookup.containsKey(num - 1)) ? lookup.get(num - 1) : 0;
				int right = (lookup.containsKey(num + 1)) ? lookup.get(num + 1) : 0;
				lookup.put(num, left + right + 1);
				/*
				 * 改变首尾两个长度(换成更长的长度)
				 * 这里进行了剪枝，直接改变首尾两个长度，实际是应该每个连续数字都改变，但是题目只需要最长长度
				 * 可以剪枝的原因，比如100, 4, 200, 1, 3, 2, 5，这时候遍历到2
				 * 2的left = 1，right = 2，2的长度等于left + right + 1 = 4，把1， 4长度改变为4，其余数字长度不变是没有问题的
				 * 2的left就是1，改变1的长度就是正确的
				 * 2的right为3, 4，改变4的长度就是正确的，由于3, 4已经遍历过了，所以不会再次遍历，所以不需要再次用到3的长度
				 */
				lookup.put(num - left, left + right + 1);
				lookup.put(num + right, left + right + 1);
				res = Math.max(res, left + right + 1);
			}
		}
		return res;
	}
    
	public static void main(String[] args) {
		int[] nums = {100, 4, 200, 1, 3, 2, 5};
		
		Number128 n = new Number128();
		System.out.println(n.longestConsecutive(nums));
	}
}
