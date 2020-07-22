/*
给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

示例 1:
输入: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]

示例 2:
输入: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]

进阶:
一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？
*/

/*
 * 遍历整个矩阵，如果matrix[i][j] == 0就将第i行和第j列的第一个元素标记。
 * 第一行和第一列的标记是相同的，都是matrix[0][0]，所以需要一个额外的变量告知第一列是否被标记，同时用matrix[0][0]继续表示第一行的标记。
 * 然后，从第二行第二列的元素开始遍历，如果第row行或者第column列被标记了，那么就将matrix[row][column]设为 0。
 * 然后我们检查是否matrix[0][0] == 0，如果是则赋值第一行的元素为零。
 * 然后检查第一列是否被标记，如果是则赋值第一列的元素为零。
 */
public class Number73 {
	public void setZeroes(int[][] matrix) {
		//第一行和第一列的标记是相同的，都是 matrix[0][0]，所以需要一个额外的变量告知第一列是否被标记，同时用matrix[0][0]继续表示第一行的标记。  
		Boolean isCol = false;
		int row = matrix.length;
		int column = matrix[0].length;

		for(int i = 0; i < row; i++) {
			//第一列标记
			if(matrix[i][0] == 0) {
				isCol = true;
			}

			for(int j = 1; j < column; j++) {
				if(matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		//所有元素标0
	    for(int i = 1; i < row; i++) {
	    	for(int j = 1; j < column; j++) {
	    		if(matrix[i][0] == 0 || matrix[0][j] == 0) {
	    			matrix[i][j] = 0;
		        }
	    	}
	    }
	    
	    //第一行标0
	    if(matrix[0][0] == 0) {
	    	for(int j = 0; j < column; j++) {
	    		matrix[0][j] = 0;
	    	}
	    }
	    
	    //第一列标0
	    if (isCol) {
	    	for (int i = 0; i < row; i++) {
	    		matrix[i][0] = 0;
	    	}
	    }
	}
	  
	public static void main(String[] args) {
		int[][] matrix = {
					{0,1,2,0},
					{3,4,5,2},
					{1,3,1,5}
		};
		Number73 n = new Number73();
    	n.setZeroes(matrix);
	}
}
