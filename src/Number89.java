import java.util.ArrayList;
import java.util.List;

/*
格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

示例 1:
输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2

对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0
10 - 2
11 - 3
01 - 1

示例 2:
输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。
*/

/*
 * 设n阶格雷码集合为G(n)，则G(n+1)阶格雷码为：
 * 给G(n)阶格雷码每个元素二进制形式前面添加0，得到G'(n)
 * 设G(n)集合倒序为R(n)，给R(n)每个元素二进制形式前面添加1，得到R'(n)
 * G(n+1) = G'(n) ∪ R'(n)拼接两个集合即可得到下一阶格雷码。
 */
public class Number89 {
	public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        int head = 1;
        //G'(n)不用操作，因为001和01没区别，都是1
        for(int i = 0; i < n; i++) {
        	//倒序遍历添加R'(n)，原因看图
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            //head = 1 << i 计算出对应位数
            head <<= 1;
        }
        return res;
    }
	
	public static void main(String[] args) {
		int n1 = 3;
		Number89 n = new Number89();
    	n.grayCode(n1);
	}
}
