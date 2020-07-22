/*
给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

示例:
输入: 3

输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

public class Number59 {
	public int[][] generateMatrix(int n) {
		int[][] ans = new int[n][n];
		if(n == 0) {
			return ans;
		}
		
		int r1 = 0, r2 = n - 1;
		int c1 = 0, c2 = n - 1;
		int number = 1;
		while(r1 <= r2 && c1 <= c2) {
			//从左到右
			for(int i = c1; i <= c2; i++) {
				ans[r1][i] = number;
				number++;
			}
			
			//从上到下
			for(int i = r1 + 1; i <= r2; i++) {
				ans[i][c2]= number;
				number++;
			}
			//从右到左
			for(int i = c2 - 1; i >= c1; i--) {
				ans[r2][i] = number;
				number++;
			}
			//从下到上
			for(int i = r2 - 1; i > r1; i--) {
				ans[i][c1] = number;
				number++;
			}
			r1++;
			r2--;
			c1++;
			c2--;
		}
        return ans;
    }
	
	public static void main(String[] args) {
    	int n1 = 4;
    	Number59 n = new Number59();
    	int[][] ans = n.generateMatrix(n1);
    	for(int i = 0; i < n1; i++) {
    		for(int j = 0; j < n1; j++) {
    			System.out.print(ans[i][j]);
    			if((j+1) % 4 == 0)
    				System.out.println();
    		}
    	}
    }
}
