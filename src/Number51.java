import java.util.ArrayList;
import java.util.List;

/*
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

示例:
输入: 4
输出: [
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。
*/

/*
 * 回溯法
 * 皇后能攻击同一行，同一列，同一主对角线或副对角线，即
 * 一行只能有一个皇后且一列也只能有一个皇后
 * 同一主对角线只能有一个皇后且同一副对角线只能有一个皇后
 * 对于同一主对角线(左上到右下)有行号-列号=常数
 * 对于同一副对角线(左下到右上)有行号+列号=常数
 * 没有必要在棋盘上考虑所有的方格。只需要按行或者列循环即可。
 */
public class Number51 {
	List<List<String>> ans = new ArrayList<>();
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
		/*
		 * hills[row - col + (n - 1)]是因为最小row = 0,col = n - 1,0 - (n - 1)则会为负数，所以全体加上n - 1
		 * 最大为row = n - 1,col = 0,则最多为2n-2
		 */
		int res = rows[col] + hills[row - col + (n - 1)] + dales[row + col];
		return (res == 0) ? true : false;
	}

	public void placeQueen(int row, int col) {
		queens[row] = col;
		rows[col] = 1;
		hills[row - col + (n - 1)] = 1;
		dales[row + col] = 1;
	}

	public void removeQueen(int row, int col) {
		queens[row] = 0;
		rows[col] = 0;
		hills[row - col + (n - 1)] = 0;
		dales[row + col] = 0;
	}

	public void addSolution() {
		List<String> solution = new ArrayList<String>();
		for (int i = 0; i < n; ++i) {
			int col = queens[i];
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < col; ++j) {
				sb.append(".");
			}
			sb.append("Q");
			for (int j = 0; j < n - col - 1; ++j) {
				sb.append(".");
			}
			solution.add(sb.toString());
		}
		ans.add(solution);
	}

	//回溯主体
	public void backtrack(int row) {
		for (int col = 0; col < n; col++) {
			//判断这一列是否能放
			if (isNotUnderAttack(row, col)) {
				placeQueen(row, col);
				//全部放完
				if (row + 1 == n) {
					addSolution();
				}
				//继续回溯，下一行开始
				else {
					backtrack(row + 1);
				}
				//取消上一个棋子
				removeQueen(row, col);
			}
		}
	}

	public List<List<String>> solveNQueens(int n) {
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
    	int num = 4;
    	Number51 n = new Number51();
    	System.out.println(n.solveNQueens(num).toString());
    }
}
