import java.util.ArrayList;
import java.util.List;

/*
 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

示例:
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class Number118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        if(numRows == 0) {
        	return triangle;
        }
        
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);
        
        for(int i = 1; i < numRows; i++) {
        	List<Integer> temp = new ArrayList<>();
        	List<Integer> pre = triangle.get(i-1);
        	temp.add(1);
        	
        	for(int j = 1; j < i; j++) {
        		temp.add(pre.get(j-1) + pre.get(j));
        	}
        	
        	temp.add(1);
        	triangle.add(new ArrayList<>(temp));
        }
        return triangle;
    }
    
	public static void main(String[] args) {
		int numRows = 3;
		
		Number118 n = new Number118();
		System.out.println(n.generate(numRows).toString());
	}
}
