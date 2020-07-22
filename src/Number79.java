/*
给定一个二维网格和一个单词，找出该单词是否存在于网格中。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.
*/

public class Number79 {
	private char[][] board;
	private String word;
	private int row;
    private int column;
    private boolean[][] marked;
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    
    public boolean exist(char[][] board, String word) {
    	row = board.length;
        if(row == 0) {
            return false;
        }
        column = board[0].length;
        marked = new boolean[row][column];
        this.word = word;
        this.board = board;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int start) {
        if(start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        
        if(board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            for(int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if(inArea(newX, newY) && !marked[newX][newY]) {
                    if(dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < column;
    }

	public static void main(String[] args) {
		char[][] board = {
					{'A','B','C','E'},
					{'S','F','C','S'},
					{'A','D','E','E'}
		};
		String word = "SEE";
		Number79 n = new Number79();
    	System.out.println(n.exist(board, word));
	}
}
