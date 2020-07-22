import java.util.HashMap;
import java.util.Map;

/*
给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

示例 1:
输入: [[1,1],[2,2],[3,3]]
输出: 3
解释:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4

示例 2:
输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出: 4
解释:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
*/

/*
 * 固定一点, 找其他点和这个点组成直线, 统计他们的斜率!
 * 求斜率.用两种方法
 * 用最大约数方法(gcd), 把他化成最简形式, 3/6 == 2/4 == 1/2
 * 除数(不太精确, 速度快!)
 */
public class Number149 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if(n == 0) {
        	return 0;
        }
        if(n == 1) {
        	return 1;
        }
        int res = 0;
        //对于第i个点只需考虑它和后面点的组合即可，因为和前面的组合已经在更小的i时候讨论了 
        for(int i = 0; i < n - 1; i++) {
            Map<String, Integer> slope = new HashMap<>();
            //repeat记录和第i个点重复点的个数 
            int repeat = 0;
            int tmp_max = 0;
            for(int j = i + 1; j < n; j++) {
            	//dy为第j个点和第i个点之间y的距离 
                int dy = points[i][1] - points[j][1];
                //dx为第j个点和第i个点之间x的距离 
                int dx = points[i][0] - points[j][0];
                //dy = 0 && dx = 0表明第j个点和第i个点重合了 
                if(dy == 0 && dx == 0) {
                    repeat++;
                    continue;
                }
                //求公约数，因为如果采用dy/dx的方式求斜率，如果是一条平行于y轴的线会造成除法错误 
                int g = gcd(dy, dx);
                //这一步不仅是进行简单的约分
				//对于特殊例子[0,0]的两个点[1,-1]、[-1,1]两者公约数分别为-1、1，但除去公约数后则为[-1,1]、[-1,1]则相同了
                if(g != 0) {
                    dy /= g;
                    dx /= g;
                }
                String tmp = String.valueOf(dy) + "/" + String.valueOf(dx);
				//slope中更新斜率一样的点的个数 
                slope.put(tmp, slope.getOrDefault(tmp, 0) + 1);
                tmp_max = Math.max(tmp_max, slope.get(tmp));
            }
            //重合的点也算在一条直线上，且重合的点可以说在任意一条直线上 
            res = Math.max(res, repeat + tmp_max + 1);
        }
        return res;
    }
    
    //辗转相除法
    private int gcd(int dy, int dx) {
        if(dx == 0) {
        	return dy;
        }else {
        	return gcd(dx, dy % dx);
        }
    }
    
	public static void main(String[] args) {
		int[][] points = {
				{1,1},{3,2},{5,3},
				{4,1},{2,3},{1,4}
				};
		
		Number149 n = new Number149();
		n.maxPoints(points);
	}
}
