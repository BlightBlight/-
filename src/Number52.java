/*
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回 n 皇后不同的解决方案的数量。

示例:
输入: 4
输出: 2
解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

/*
 * 同51
 */
public class Number52 {
	int ans = 0;
	//按行循环
	int rows[];
	//主对角线
	int hills[];
	//副对角线
	int dales[];
	//皇后个数
	int n;
	//以行循环，则第n个皇后为第n行，第n个为m则皇后在第m列
	int queens[];

	public boolean isNotUnderAttack(int row, int col) {
		//hills[row-col+(n-1)]是因为最小row=0,col=n-1,0-(n-1)则会为负数，所以全体加上n-1。最大为row=n-1,col=0,则最多为2n-2
		int res = rows[col] + hills[row-col+(n - 1)] + dales[row+col];
		return (res == 0) ? true : false;
	}

	public void placeQueen(int row, int col) {
		queens[row] = col;
		rows[col] = 1;
		hills[row-col+(n - 1)] = 1;
		dales[row+col] = 1;
	}

	public void removeQueen(int row, int col) {
		queens[row] = 0;
		rows[col] = 0;
		hills[row-col+(n - 1)] = 0;
		dales[row+col] = 0;
	}

	//回溯主体
	public void backtrack(int row) {
		for (int col = 0; col < n; col++) {
			//判断这一列是否能放
			if (isNotUnderAttack(row, col)) {
				placeQueen(row, col);
				//全部放完
				if (row + 1 == n) {
					ans++;
				}
				//继续回溯，下一行开始
				else {
					backtrack(row + 1);
				}
				// backtrack
				removeQueen(row, col);
			}
		}
	}

	public int solveNQueens(int n) {
		this.n = n;
		//行数
		rows = new int[n];
		//主对角线=行数-列数
		hills = new int[2 * n - 1];
		//副对角线=行数+列数
		dales = new int[2 * n - 1];
		//皇后个数
		queens = new int[n];

		backtrack(0);
		return ans;
	}
    
    public static void main(String[] args) {
    	int num = 8;
    	Number52 n = new Number52();
    	System.out.println(n.solveNQueens(num));
    }
}
