/*
 * 给定两个大小 m和n的有序数组nums1和nums2。
请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。

示例 1:
nums1 = [1, 3]
nums2 = [2]
则中位数是 2.0

示例 2:
nums1 = [1, 2]
nums2 = [3, 4]

则中位数是(2 + 3)/2 = 2.5
*/

//文件袋里
public class Number4 {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m, n;
		if(nums1.length > nums2.length) {
			int[] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
		}
		m = nums1.length;
		n = nums2.length;
		int iMin = 0, iMax = m;
		int i, j;
		while(iMin <= iMax) {
			i = (iMin + iMax) / 2;
			j = (m + n + 1) / 2 - i;
			if(i > 0 && j < n && nums1[i-1] > nums2[j]) {
				iMax = i - 1;
			}else if (j > 0 && i < m && nums2[j-1] > nums1[i]) {
				iMin = i + 1;
			}else {
				int maxLeft = 0;
				if(i == 0) {
					maxLeft = nums2[j-1];
				}else if(j == 0) {
					maxLeft = nums1[i-1];
				}else{
					maxLeft = Math.max(nums1[i-1], nums2[j-1]);
				}
				
				if((m + n) % 2 == 1) {
					//总数是奇数的话边界的maxLight就是中位数，偶数就要左右边界相加除2.0
					return maxLeft;
				}
				
				int minRight = 0;
				if(i == m) {
					minRight = nums2[j];
				}else if(j == n) {
					minRight = nums1[i];
				}else {
					minRight = Math.min(nums1[i], nums2[j]);
				}
				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}
}
