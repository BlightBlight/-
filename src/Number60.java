import java.util.LinkedList;
import java.util.List;

/*
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
"123"
"132"
"213"
"231"
"312"
"321"
给定n和k，返回第k个排列。

说明：
给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。

示例 1:
输入: n = 3, k = 3
输出: "213"

示例 2:
输入: n = 4, k = 9
输出: "2314"
*/

public class Number60 {
    public String getPermutation(int n, int k) {
    	StringBuilder sb = new StringBuilder();
        //n为几时共有几个排列组合
        int factor[] = {1, 1, 2, 6, 24 ,120, 720, 5040, 40320};
        List<Integer> list = new LinkedList<>();
        for(int i = 1; i < 10; i++) {
        	list.add(i);
        }
        //从第一位开始算每一位的值
		for(int i = n; i > 0; i--) {
			/*
			 * 举个例子：4，9
			 * 具体第一位的排列是1xxx、2xxx、3xxx、4xxx这四种情况每一种的所得到的情况数都是3!，数目都是一样的
			 * 所以我们可以用k / (n - 1)!由此得知首位的值的情况 对应代码也就是这三行 
			 * int sv = k / factor[i - 1]
			 * k = k % factor[i - 1]
			 * sv = k > 0 ? sv + 1 : sv
			 * 取余大于0就说明满足首位数字所有排列且有多余，则首位再加1
			 * 取余等于0则说明刚好满足，就是这个首位数字的最后一个排列。让k等于后一位排列组合个数，则下一次循环仍然为0，继续进入这个状态
			 * 接着我们循环计算第二的情况，前一位的余数就是第二位对应的k值以此循环得到所有的位置的值
			 */
            int first = k / factor[i - 1];
            k = k % factor[i - 1];
            first = k > 0 ? first + 1 : first;
            if(k == 0) {
            	k = factor[i - 1];
            }
            /*
             * list取值范围为[1, n]，first取值范围为(0, (n - 1)! + 1)，一一对应
             * 因为一个数字只能使用一次，所以first指的是逻辑上第n大的数字，如1xxx、2xxx、3xxx、4xxx，first等于2，就是list[2] = 3
             * 如n为4，list取值范围为[1, 4]，first取值范围为[0, 3]，若first等于1，则移除list[0]
             * list下一轮取值范围为[2, 4]，first下一轮取值范围为[0, 2]
             */
            sb.append(list.remove(first - 1));
        }
        return sb.toString();
    }
    
	public static void main(String[] args) {
    	int n1 = 4, k = 3;
    	Number60 n = new Number60();
    	System.out.println(n.getPermutation(n1, k));
    }
}
