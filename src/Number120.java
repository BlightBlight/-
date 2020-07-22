import java.util.ArrayList;
import java.util.List;

/*
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
例如，给定三角形：
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：
如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
*/

/*
 * 动态规划，自底向上
 */

public class Number120 {
	public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] dp = new int[row];
        for(int i = 0; i < row; i++) {
        	dp[i] = triangle.get(row - 1).get(i);
        }
        for(int i = row - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
    
	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<>();
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(2);
		triangle.add(temp);
		temp = new ArrayList<Integer>();
		temp.add(3);
		temp.add(4);
		triangle.add(temp);
		temp = new ArrayList<Integer>();
		temp.add(6);
		temp.add(5);
		temp.add(7);
		triangle.add(temp);
		temp = new ArrayList<Integer>();
		temp.add(4);
		temp.add(1);
		temp.add(8);
		temp.add(3);
		triangle.add(temp);
		
		Number120 n = new Number120();
		System.out.println(n.minimumTotal(triangle));
	}
}
