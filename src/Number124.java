/*
给定一个非空二叉树，返回其最大路径和。
本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:
输入: [1,2,3]

       1
      / \
     2   3

输出: 6

示例 2:
输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42
*/

public class Number124 {
	int max_sum = Integer.MIN_VALUE;
	
	//自顶向下递归
	public int max_gain(TreeNode node) {
		if(node == null) {
			return 0;
		}
			
		int left_gain = Math.max(max_gain(node.left), 0);
		int right_gain = Math.max(max_gain(node.right), 0);
		
		//创建新路径
		int price_newpath = node.val + left_gain + right_gain;
		
		max_sum = Math.max(max_sum, price_newpath);
		
		//返回到当前节点的一条最大路径
		return node.val + Math.max(left_gain, right_gain);
	}

	public int maxPathSum(TreeNode root) {
		max_gain(root);
		return max_sum;
	}
    
	public static void main(String[] args) {
/*		TreeNode p = new TreeNode(1);
		TreeNode temp = p;
		p.left = new TreeNode(2);
		p.right = new TreeNode(3);*/
		TreeNode p = new TreeNode(-10);
		TreeNode temp = p;
		p.left = new TreeNode(9);
		p.right = new TreeNode(20);
		temp = p.right;
		temp.left = new TreeNode(15);
		temp.right = new TreeNode(7);
		
		Number124 n = new Number124();
		n.maxPathSum(p);
	}
}
