import java.util.Arrays;

/*
给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

示例:
输入:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
输出: 6
*/

/*
 * 这题解法用到84题解法
 * 看图85_1理解
 * left 和 right 是对称关系
 *我们考虑最后一个柱子的更新。上一层的leftLessMin = 1，也就是蓝色 0 的位置是第一个比它低的柱子
 *但是在当前层，由于中间出现 0。所以不再是之前的leftLessMin，而是和上次出现0的位置进行比较(因为0一定比当前柱子小)
 *谁的下标大，更接近当前柱子，就选择谁。上图中出现0的位置是2，之前的leftLessMin是1，选一个较大的，那就是2了。
 */

public class Number85 {
	public int maximalRectangle(char[][] matrix) {
	    if (matrix.length == 0) {
	        return 0;
	    }
	    
	    int maxArea = 0;
	    int cols = matrix[0].length;
	    int[] leftLessMin = new int[cols];//leftLessMin[n]代表左边第一个小于第n个柱子的柱子
	    int[] rightLessMin = new int[cols];//rightLessMin[n]代表右边第一个大于第n个柱子的柱子
	    Arrays.fill(leftLessMin, -1);//初始化为-1，也就是最左边
	    Arrays.fill(rightLessMin, cols);//初始化为cols，也就是最右边
	    int[] heights = new int[cols];//柱子高度
	    //一行一行叠加，算出每一次最大面积
	    for(int row = 0; row < matrix.length; row++) {
	        //更新所有高度
	        for(int col = 0; col < cols; col++) {
	            if(matrix[row][col] == '1') {
	                heights[col] += 1;
	            }else {
	            	//遇到0直接把柱子高度置为0
	                heights[col] = 0;
	            }
	        }
			//更新所有leftLessMin
	        int boundary = -1; //记录上次出现0的位置
	        for(int col = 0; col < cols; col++) {
	            if(matrix[row][col] == '1') {
	                //和上次出现0的位置比较取最大，就是最接近当前柱子的第一个小于的柱子
	                leftLessMin[col] = Math.max(leftLessMin[col], boundary);
	            }else {
	            	//柱子为0的左边第一个小于柱子选-1，右边第一个小于柱子选cols，与boundary相符
	                leftLessMin[col] = -1; 
	                //更新0的位置
	                boundary = col;
	            }
	        }
	        //rightLessMin同理
	        boundary = cols;
	        for(int col = cols - 1; col >= 0; col--) {
	            if(matrix[row][col] == '1') {
	                rightLessMin[col] = Math.min(rightLessMin[col], boundary);
	            }else {
	                rightLessMin[col] = cols;
	                boundary = col;
	            }
	        }
			
	        //更新所有面积
	        for(int col = cols - 1; col >= 0; col--) {
	            int area = (rightLessMin[col] - leftLessMin[col] - 1) * heights[col];
	            maxArea = Math.max(area, maxArea);
	        }
	    }
	    return maxArea;
	}
	
	public static void main(String[] args) {
		char[][] matrix = {
					{'1','0','1','0','0'},
					{'1','0','1','1','1'},
					{'1','1','1','1','1'},
					{'1','0','0','1','0'}
		};
		Number85 n = new Number85();
    	System.out.println(n.maximalRectangle(matrix));
	}
}
