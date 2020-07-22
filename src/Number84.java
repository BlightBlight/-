import java.util.Stack;

/*
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。

以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 
图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

示例:
输入: [2,1,5,6,2,3]
输出: 10
*/

/*
 * 初始化时，按照从左到右的顺序，我们不断将柱子的序号放进栈中，直到当前指向元素小于栈顶元素
 * 左边取第一个小于当前柱子下标，也就是top-1
 * 右边取第一个小于当前柱子的下标，也就是i
 * 最大矩形面积就是i-(top-1)-1，i-(top-1)就把top左边长度去掉，剩下top到i长度，再把i减去，就是减1
 * 在代码中，top-1就是stack.peek()，因为栈顶已经pop()出来了
 * 循环直到全部放入，若栈为空，则输出，不为空，则最后再算一轮
 * 时间复杂度为O(n)
 */

public class Number84 {
	public int largestRectangleArea(int[] heights) {
	    int maxArea = 0;
	    Stack<Integer> stack = new Stack<>();
	    int p = 0;
	    while(p < heights.length) {
	        //栈空入栈
	        if(stack.isEmpty()) {
	            stack.push(p);
	            p++;
	        }else {
	            int top = stack.peek();
	            //当前高度大于栈顶，入栈
	            if(heights[p] >= heights[top]) {
	                stack.push(p);
	                p++;
	            }else {
	                //保存栈顶高度
	                int height = heights[stack.pop()];
	                //左边第一个小于当前柱子的下标
	                int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
	                //右边第一个小于当前柱子的下标
	                int RightLessMin = p;
	                maxArea = Math.max(maxArea, (RightLessMin - leftLessMin - 1) * height);
	            }
	        }
	    }
	    while(!stack.isEmpty()) {
	        //保存栈顶高度
	        int height = heights[stack.pop()];
	        //左边第一个小于当前柱子的下标
	        int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
	        //右边没有小于当前高度的柱子，所以赋值为数组的长度便于计算，或者赋值为p也行
	        int RightLessMin = p;
	        maxArea = Math.max(maxArea, (RightLessMin - leftLessMin - 1) * height);
	    }
	    return maxArea;
    }
    
	public static void main(String[] args) {
		int[] heights = {2,1,5,6,2,3};
		Number84 n = new Number84();
    	System.out.println(n.largestRectangleArea(heights));
	}
}
