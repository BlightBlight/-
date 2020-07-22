import java.util.ArrayList;
import java.util.List;

/*
 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:
输入: 3
输出: [1,3,3,1]
进阶：
你可以优化你的算法到 O(k) 空间复杂度吗？
*/

public class Number119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>(1);
        if(rowIndex == 0) {
        	return ans;
        }
        
        ans.add(1);
        int pre = 1;
        for(int i = 1; i <= rowIndex; i++) {
        	for(int j = 1; j < i; j++) {
        		int temp = ans.get(j);
    			ans.set(j, pre + ans.get(j));
    			pre = temp;
        	}
        	ans.add(1);
        }
        return ans;
    }
    
	public static void main(String[] args) {
		int rowIndex = 3;
		
		Number119 n = new Number119();
		System.out.println(n.getRow(rowIndex).toString());
	}
}
