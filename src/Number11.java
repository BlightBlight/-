/*
 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
说明：你不能倾斜容器，且 n 的值至少为 2。

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例:
输入: [1,8,6,2,5,4,8,3,7]
输出: 49
*/

/*
在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 
使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。 
在每一步中，找出指针所指向的两条线段形成的区域，更新 maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。

注意：可能误解的地方为最后一步得到的是最大面积，不是的，最大面积是在某一步中已经找出来了，不一定是最后一步，下面证明

反证法:假设m，n围成面积为最大，设m为矮的边，n为高的边,n>m(仅方便描述)
双指针方法的规律是:每次都会向内移动偏矮的指针。要证明两个指针一定会移动到m和n位置，只需证明：
1.m左侧的指针点等于或矮于n
2.n右侧指针等于或矮于m。
假设m左侧有一个点p，高度高于n.
原面积为m*(n-m)
左侧高度高于n，则面积为n*(n-p)，
n>m且(n-p)>(n-m),则新面积绝对大于旧面积，与原假设不符，所以得出结论m左侧的点都不高于n，同理可得n右侧的点都不高于m，则双指针法可得最大面积
*/
public class Number11 {
	public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
    
    public static void main(String[] args) {
    	int[] i = new int[] {1,8,6,2,5,4,8,3,7};
    	Number11 n = new Number11();
    	System.out.println(n.maxArea(i));
    }
}
