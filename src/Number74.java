/*
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。

示例 1:
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
输出: true

示例 2:
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
输出: false
*/

public class Number74 {
    public boolean searchMatrix(int[][] matrix, int target) {
    	if(matrix.length == 0 || matrix[0].length == 0) {
    		return false;
    	}
    	//上下
        int up = 0, down = matrix.length - 1;
        while(up < down) {
        	int mid = (up + down + 1) >>> 1;
        	
        	if(matrix[mid][0] > target) {
        		down = mid - 1;
        	}else {
        		up = mid;
        	}
        }
        //左右
        int left = 0, right = matrix[0].length - 1;
        while(left < right) {
        	int mid = (left + right + 1) >>> 1;
        	
        	if(matrix[up][mid] > target) {
        		right = mid - 1;
        	}else {
        		left = mid;
        	}
        }
        return matrix[up][left] == target ? true : false;
    }
    
	public static void main(String[] args) {
		int[][] matrix = {
					{1,   3,  5,  7},
					{10, 11, 16, 20},
					{23, 30, 34, 50}
		};
		int target = 3;
		Number74 n = new Number74();
    	System.out.println(n.searchMatrix(matrix, target));
	} 
}
