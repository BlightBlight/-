/*
 给定一个二叉树，找出其最小深度。
最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
说明: 叶子节点是指没有子节点的节点。

示例:
给定二叉树 [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
返回它的最小深度  2.
*/

public class Number111 {
	public int minDepth(TreeNode root) {
		if(root == null) {
			return 0;
		}
		// 左孩子为空，只考虑右孩子的方向
		if(root.left == null) {
			return minDepth(root.right) + 1;
		}
		// 右孩子为空，只考虑左孩子的方向
		if(root.right == null) {
			return minDepth(root.left) + 1;
		}
		return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	}

	public static void main(String[] args) {
		TreeNode p = new TreeNode(3);
		TreeNode temp = p;
		p.left = new TreeNode(9);
		p.right = new TreeNode(20);
		temp = p.right;
		temp.left = new TreeNode(15);
		temp.right = new TreeNode(7);
		
		Number111 n = new Number111();
		n.minDepth(p);
	}
}
