/*
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

X X X X
X O O X
X X O X
X O X X
运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
解释:
被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'
任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
*/

/*
 * 从边界出发吧，先把边界上和O连通点找到, 把这些连通的点变成B,然后遍历整个board把O变成X, 把B变成O
 */

public class Number130 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    char[][] board;
    int row;
    int col;
    
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
        	return;
        }
        this.board = board;
        this.row = board.length;
        this.col = board[0].length;
        
        for(int j = 0; j < col; j++) {
            //第一行
            if(board[0][j] == 'O') {
            	dfs(0, j);
            }
            //最后一行
            if(board[row - 1][j] == 'O') {
            	dfs(row - 1, j);
            }
        }

        for(int i = 0; i < row; i++) {
            //第一列
            if(board[i][0] == 'O') {
            	dfs(i, 0);
            }
            //最后一列
            if(board[i][col - 1] == 'O') {
            	dfs(i, col - 1);
            }
        }

        //转变
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                	board[i][j] = 'X';
                }
                if (board[i][j] == 'B') {
                	board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(int i, int j) {
        board[i][j] = 'B';
        for (int[] dir : dirs) {
            int tmp_i = dir[0] + i;
            int tmp_j = dir[1] + j;
            //上下左右不在边界上或者不等于'O'时，则不做处理，否则，递归标记
            if (tmp_i < 0 || tmp_i >= row || tmp_j < 0 || tmp_j >= col || board[tmp_i][tmp_j] != 'O') {
            	continue;
            }
            dfs(tmp_i, tmp_j);
        }
    }
    
	public static void main(String[] args) {
		char[][] board = {
				{'X', 'X' ,'X', 'X'},
				{'X', 'O' ,'O', 'X'},
				{'X', 'X' ,'O', 'X'},
				{'X', 'O' ,'X', 'X'}
						};
		
		Number130 n = new Number130();
		n.solve(board);
	}
}
