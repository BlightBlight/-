/*
实现 pow(x, n) ，即计算 x 的 n 次幂函数。

示例 1:
输入: 2.00000, 10
输出: 1024.00000

示例 2:
输入: 2.10000, 3
输出: 9.26100

示例 3:
输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25

说明:
-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
*/

/*
 * 假定我们已经得到 x ^ (n / 2)的结果，并且我们现在想得到x ^ n的结果，我们令A是 x ^ (n / 2)的结果
 * 我们可以根据n的奇偶性来分别讨论x ^ n的值
 * 如果n为偶数，我们可以用公式(x ^ n) ^ 2 = x ^ {2 * n}来得到x ^ n = A * A
 * 如果n为奇数，那么A * A = x ^ (n - 1)  (设n为3，则n / 2 = 1, A * A = x ^ 2)
 * 直观上看，我们需要再乘一次x ，即x ^ n = A * A * x
 * 该方法可以很方便的使用递归实现。我们称这种方法为 "快速幂"，因为我们只需最多O(logn) 次运算来得到x ^ n。
 */
public class Number50 {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double ans = 1;
        double current_product = x; //这个就是A
        for (long i = N; i > 0; i /= 2) {
        	/*
        	 * 若开头为奇数，则current_product为x，ans = x ^ 2
        	 * 递归到最后总归是奇数，而上一次的current_product为x * x
        	 */
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
    
    public static void main(String[] args) {
    	double strs = 2.10000;
    	int n1 = 3;
    	Number50 n = new Number50();
    	System.out.println(n.myPow(strs, n1));
    }
}
