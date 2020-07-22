/*
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:
初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

示例:
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
输出: [1,2,2,3,5,6]
*/

public class Number88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	if(nums1.length == 0 || nums2.length == 0) {
    		return;
    	}
	    int p1 = m - 1;
	    int p2 = n - 1;
	    int p =nums1.length - 1;
	    //从后往前加
	    while((p1 >= 0) && (p2 >= 0)) {
	    	//p1和p2中从右往左取最大的放入最后面，相同放p1
	    	nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
	    }
	    /*
	     * 将nums2剩余的加入到nums1中，复制范围为从nums2的0到p2到nums1的0到p2
	     * nums2在上面不一定全部添加进入nums1
	     * 设nums1[2,2,2,2,0,0,0,0]，m=4，nums2[1,1,1,1]，n=4，p1=3，p2=3
	     * 则nums1经过上面变成nums1[2,2,2,2,2,2,2,2]
	     * 则把nums2没复制到的元素覆盖nums1，也就是从0到p2覆盖(p2+1是因为不包含右边界)
	     */
	    System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
    
	public static void main(String[] args) {
		int[] nums1 = {1,2,3,0,0,0};
		int m = 3;
		int[] nums2 = {2,5,6};
		int n1 = 3;
		Number88 n = new Number88();
    	n.merge(nums1, m, nums2, n1);
	}
}
