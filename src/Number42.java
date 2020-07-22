import java.util.Stack;

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
*/

/*
 * 先进第一个柱子，目前指向元素加1
 * 若目前指向元素大于栈顶高度(上一次的高度)就一直进，否则
 * 栈顶元素出栈，再计算现在栈顶元素和目前指向元素之间积水
 * 计算方法:底乘高，底就是目前指向元素位置减栈顶元素位置减一，高就是两个元素取最小值再减去栈顶元素
 * 高的计算方式是因为可能有很多层，之前已经计算出了底层积水，所以减去底层高度，底层高度就是栈顶元素高度
 * 目前指向元素进栈，再次循环
 */

public class Number42 {
    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while(current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while(!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if(stack.empty()) { //栈空就出去
                    break; 
                }
                int distance = current - stack.peek() - 1;//两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current);//当前指向的墙入栈
            current++;//指针后移
        }
        return sum;
    }
    
    public static void main(String[] args) {
    	int[] height = {3,0,1,0,3};
    	Number42 n = new Number42();
    	System.out.println(n.trap(height));
    }
}
