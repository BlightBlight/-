/*
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
示例 1:
给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:
给定二叉树 [1,2,2,3,3,null,null,4,4]
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。
*/

public class Number110 {
    public boolean isBalanced(TreeNode root) {
    	return (depth(root) != -1);
    }

    private int depth(TreeNode root) {
        if(root == null) {
        	return 0;
        }
        int left = depth(root.left);
        if(left == -1) {
        	return -1;
        }
        int right = depth(root.right);
        if(right == -1) {
        	return -1;
        }
        /*
    	 * 发现有一例左/右子树高度差 > 1的情况时，代表此树不是平衡树，返回-1
    	 * 发现不是平衡树时，后面的高度计算都没有意义了，因此一路返回-1，避免后续多余计算
    	 */
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(3);
		TreeNode temp = p;
		p.left = new TreeNode(9);
		p.right = new TreeNode(20);
		temp = p.right;
		temp.left = new TreeNode(15);
		temp.right = new TreeNode(7);
		
		Number110 n = new Number110();
		n.isBalanced(p);
	}
}
