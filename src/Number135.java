/*
老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：

每个孩子至少分配到 1 个糖果。
相邻的孩子中，评分高的孩子必须获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？

示例 1:
输入: [1,0,2]
输出: 5
解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。

示例 2:
输入: [1,2,2]
输出: 4
解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
*/

/*
 * 看图理解
 * 对于由a和b组成的第一座山，在分配峰点(pt.5)糖果的时候，它应该被分配到a中满足左邻居约束，b中的局部谷点(pt.8)标志着第一座山c的结束
 * 在计算的时候，我们可以把这个点归属为当前的山，也可以归属到接下来的山中
 * 点pt.13标记的是第二座山的结束，因为pt.13和pt.14两个学生的评分是相同的
 * 因此，区域e比区域d有更多的点，局部峰点(pt.10)应该被划分到e区域满足右邻居的约束
 * 现在第三座山f应该被考虑为一座只有下降坡没有上升坡的山(up=0)
 * 类似的，因为与旁边的学生评分相同，pt.16,18,19也是山的结束。
 */
public class Number135 {
	/*
	 * 局部的分配形式一定是1, 2, 3, ..., n 或者n, ..., 2, 1，总和是n * (n + 1) / 2
	 */
    public int count(int n) {
        return (n * (n + 1)) / 2;
    }
    
    public int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        //糖果总数
        int candies = 0;
        //上升坡学生数
        int up = 0;
        //下降坡学生数
        int down = 0;
        //上一轮山的状态，1为上升坡，-1为下降坡，0为当前山结束
        int old_slope = 0;
        for(int i = 1; i < ratings.length; i++) {
        	//1为上升坡，-1为下降坡，-1为不在谷底，0为当前山结束
            int new_slope = (ratings[i] > ratings[i - 1]) ? 1 : (ratings[i] < ratings[i - 1] ? -1 : 0);
        	/*
        	 * 如果上一轮在上升坡且遇到平坡，则当前山结束，上升坡和下降坡置0
        	 * 如果上一轮在下降坡且遇到上升坡或者平坡，则当前山结束，上升坡和下降坡置0
        	 */
            if((old_slope > 0 && new_slope == 0) || (old_slope < 0 && new_slope >= 0)) {
            	/*
            	 * 上升坡总糖果数+下降坡总糖果数+峰顶值
            	 * 趋势为上升-相等或下降-相等，candies += count(up) + count(down) + Math.max(up, down) + 1
            	 * +1在后面new_slope补偿了
            	 */
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }
            if(new_slope > 0)
                up++;
            if(new_slope < 0)
                down++;
            if(new_slope == 0)
                candies++;

            old_slope = new_slope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }

	public static void main(String[] args) {
		int[] ratings = {1, 2, 3, 4, 5, 3, 2, 1, 2, 6, 5, 4, 3, 3, 2, 1, 1, 3, 3, 3, 4, 2};
		
		Number135 n = new Number135();
		n.candy(ratings);
	}
}
