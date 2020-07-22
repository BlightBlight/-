/*
给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量
一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例 1:
输入:
11110
11010
11000
00000
输出: 1

示例 2:
输入:
11000
11000
00100
00011
输出: 3
*/

public class Number200 {
	char[][] grid;
	int row;
    int column;
    
    public int numIslands(char[][] grid) {
        int ans = 0;
        if(grid.length == 0) {
        	return ans;
        }
        
        this.grid = grid;
        this.row = grid.length;
        this.column = grid[0].length;
        for(int i = 0; i < row; i++) {
        	for(int j = 0; j < column; j++) {
        		if(grid[i][j] == '1') {
	        		ans++;
	        		dfs(i, j);
        		}
        	}
        }
        return ans;
    }
    
    public void dfs(int i, int j) {
    	if(i < 0 || i >= row || j < 0 || j >= column ||grid[i][j] == '0') {
    		return;
    	}
    	
    	grid[i][j] = '0';
        dfs(i - 1, j);
        dfs(i + 1, j);
        dfs(i, j - 1);
        dfs(i, j + 1);
    }
    
	public static void main(String[] args) {
		char[][] grid = {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'},
				{'0','0','0','1','1'}
						};
		
		Number200 n = new Number200();
		System.out.println(n.numIslands(grid));
	}
}
