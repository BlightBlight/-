/*
给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:
输入: [3,2,3]
输出: 3

示例 2:
输入: [2,2,1,1,1,2,2]
输出: 2
*/

/*
 * 如果我们把众数记为+1，把其他数记为-1，将它们全部加起来，显然和大于 0 ，从结果本身我们可以看出众数比其他数多
 * 本质上，Boyer-Moore算法就是找nums的一个后缀suf，其中suf[0]就是后缀中的众数
 * 我们维护一个计数器，如果遇到一个我们目前的候选众数，就将计数器加一，否则减一
 * 只要计数器等于0，我们就将nums中之前访问的数字全部忘记 ，并把下一个数字当做候选的众数
 * 7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7
 * 首先，下标为0的7被当做众数的第一个候选
 * 在下标为5处，计数器会变回0。所以下标为6的5是下一个众数的候选者
 * 由于这个例子中7是真正的众数，所以通过忽略掉前面的数字，我们忽略掉了同样多数目的众数和非众数
 * 因此，7仍然是剩下数字中的众数。
 */

public class Number169 {
	public int majorityElement(int[] nums) {
		int count = 0;
		Integer candidate = null;

		for(int num : nums) {
			if(count == 0) {
				candidate = num;
			}
			count += (num == candidate) ? 1 : -1;
		}

		return candidate;
	}
    
	public static void main(String[] args) {
		int[] nums = {2,2,1,1,1,2,2};
		
		Number169 n = new Number169();
		n.majorityElement(nums);
	}
}
