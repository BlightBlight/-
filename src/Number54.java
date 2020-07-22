import java.util.ArrayList;
import java.util.List;

/*
给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]

示例 2:
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

public class Number54 {
    public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> ans = new ArrayList<>();    	
    	if(matrix.length == 0) {
    		return ans;
    	}
    	
    	int r1 = 0, r2 = matrix.length - 1;//行数
    	int c1 = 0, c2 = matrix[0].length - 1;//列数
    	//按层循环
    	while(r1 <= r2 && c1 <= c2) {
    		//左到右
    		for(int c = c1;c <= c2; c++) {
    			ans.add(matrix[r1][c]);
    		}
    		//上到下
    		for(int r = r1 + 1; r <= r2; r++) {
    			ans.add(matrix[r][c2]);
    		}
    		//防止最后一层重复放，当r1==r2或者c1==c2时，说明是最后一层
    		if(r1 < r2 && c1 < c2) {
	    		//右到左
	    		for(int c = c2 - 1;c >= c1; c--) {
	    			ans.add(matrix[r2][c]);
	    		}
	    		//下到上
	    		for(int r = r2 - 1; r > r1; r--) {
	    			ans.add(matrix[r][c1]);
	    		}
    		}
    		r1++;
    		r2--;
    		c1++;
    		c2--;
    	}
    	return ans;
    }
    
    public static void main(String[] args) {
    	int[][] nums = {/*
    					{1,2},
    					{3,4}
    					*/
    					{1,2,3,4},
    					{5,6,7,8},
    					{9,10,11,12}
    					
    					/*{ 5, 1, 9,11},
    	                { 2, 4, 8,10},
    	                {13, 3, 6, 7},
    	                {15,14,12,16}*/
    	                
    	                };
    	Number54 n = new Number54();
    	System.out.println(n.spiralOrder(nums));
    }
}
