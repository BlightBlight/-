/*
给定一个整数 n，返回 n! 结果尾数中零的数量。

示例 1:
输入: 3
输出: 0
解释: 3! = 6, 尾数中没有零。

示例 2:
输入: 5
输出: 1
解释: 5! = 120, 尾数中有 1 个零.
说明: 你算法的时间复杂度应为 O(log n) 。
*/

/*
 * 尾部的0只能由2 * 5得来
 * 所以只要寻找阶乘中有多少对2-5就有多少个0
 * 11! = 11 * 10 * 9 * ...1 = 11 * (2 * 5) * 9 * (2 * 4) * 7 * (2 * 3)... * 1
 * 每两个数字就会有一个2，每五个数字至少有一个5，所以2的数字远大于5，寻找5就行
 * 125 = 5 * 5 * 5，25 = 5 * 5，5 = 1 * 5，每五个数字有一个5，每二十五个数字有两个5，每一百二十五个数字有三个5...
 * 所以5的个数就是while(n > 0) n = n / 5;
 * n = 25，count = 5，n = 5， count = 6;
 */
public class Number172 {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }
    
	public static void main(String[] args) {
		int n1 = 5;
		
		Number172 n = new Number172();
		System.out.println(n.trailingZeroes(n1));
	}
}
