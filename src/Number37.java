/*
编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：
数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。

一个数独。
答案被标成红色。

Note:
给定的数独序列只包含数字 1-9 和字符 '.' 。
你可以假设给定的数独只有唯一解。
给定数独永远是 9x9 形式的。
*/

/*
 * 下列代码使用回溯法进行填充，简单来说
 * 选定一个格子填一个数字，若成功，填下一个，一直直到填满或者无法再填下一个
 * 当无法再填下一个时，回退上一个格子填的数字，若还有别的能填的数字，则继续填，否则再回退一个格子
 * 进行最多(9!)^9次尝试后，成功
 * 
 * 其实我觉得正常数独可以使用剪枝法能减低尝试次数
 * 具体思路就是先不从第一个空格子开始填
 * 每个格子若能填充数字，则创建一个int[],把能填充的数字都放进int[];不能填充，则不创建;(设有n个格子)
 * 为n个格子填充完后，选择只能填充一个数字的格子进行填充;
 * 填充完后再更新该数字同一行同一列的格子，把填充数字去掉，再进行下一轮
 * 若没有只能填充一个数字的格子，则从最少数字的格子开始填充(这样更复杂了，可能还要链表来储存填充路径)
 * 接下来就是回溯法，同样一直直到填满或者无法再填下一个
 * 适用于数独初始数字较多的情况，或者只能填充一个数字的格子较多的情况
 */
public class Number37 {
	int n = 3;
	int N = n * n;

	int[][] rows = new int[N][N + 1];//第一维表示有多少行，第二维表示有多少个数字
	int[][] columns = new int[N][N + 1];//第一维表示有多少列，第二维表示有多少个数字
	int[][] boxes = new int[N][N + 1];//第一维表示有多少个九宫格，第二维表示有多少个数字

	char[][] board;

	boolean sudokuSolved = false;
	
	//判断能否填充数字
	public boolean couldPlace(int target, int row, int col) {
		int idx = (row / n) * n + col / n;
		return rows[row][target] + columns[col][target] + boxes[idx][target] == 0;
	}
	
	//把数字填进格子
	public void placeNumber(int target, int row, int col) {
		int idx = (row / n) * n + col / n;

		rows[row][target]++;
		columns[col][target]++;
		boxes[idx][target]++;
		board[row][col] = (char) (target + '0');
	}
	
	//移除填充数字
	public void removeNumber(int target, int row, int col) {
		int idx = (row / n) * n + col / n;
		rows[row][target]--;
		columns[col][target]--;
		boxes[idx][target]--;
		board[row][col] = '.';
	}
	
	//进入下一个格子
	public void placeNextNumbers(int row, int col) {
		//最后一行，最后一列，说明前面所有格子都填满，返回正确
		if ((col == N - 1) && (row == N - 1)) {
			sudokuSolved = true;
		}else {
			//填满一列，下一行开始
			if(col == N - 1) {
				backtrack(row + 1, 0);
			}
			//同一行，下一列开始
			else {
				backtrack(row, col + 1);
			}
		}
	}
	
	//回溯法主体
	public void backtrack(int row, int col) {
		//当格子为空时，进行尝试，否则，下一个格子
		if (board[row][col] == '.') {
			//从1到9一个个填
			for (int target = 1; target < 10; target++) {
				if (couldPlace(target, row, col)) {
					placeNumber(target, row, col);
					placeNextNumbers(row, col);
					//当填充满后，不必要再次回溯，因为题目保证只有一个解
					//若有多个解，则继续回溯，寻找其他解
					if (!sudokuSolved)
						removeNumber(target, row, col);
				}
			}
		} else
			placeNextNumbers(row, col);
	}

	public void solveSudoku(char[][] board) {
		this.board = board;

		// 初始化行、列、九宫格HashMap
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				char num = board[i][j];
				if (num != '.') {
					int target = Character.getNumericValue(num);
					placeNumber(target, i, j);
				}
			}
		}
		backtrack(0, 0);
	}

    public static void main(String[] args) {
    	char[][] board = {
    		{'5','3','.','.','7','.','.','.','.'},
    		{'6','.','.','1','9','5','.','.','.'},
    		{'.','9','8','.','.','.','.','6','.'},
    		{'8','.','.','.','6','.','.','.','3'},
    		{'4','.','.','8','.','3','.','.','1'},
    		{'7','.','.','.','2','.','.','.','6'},
    		{'.','6','.','.','.','.','2','8','.'},
    		{'.','.','.','4','1','9','.','.','5'},
    		{'.','.','.','.','8','.','.','7','9'}
    		};
    	Number37 n = new Number37();
    	
    	n.solveSudoku(board);
    }
}
