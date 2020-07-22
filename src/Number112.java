/*
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
说明: 叶子节点是指没有子节点的节点。
示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
*/

public class Number112 {
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null) {
			return false;
		}

		sum -= root.val;
		if((root.left == null) && (root.right == null)) {
			return (sum == 0);
		}
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(5);
		TreeNode temp = p;
		p.left = new TreeNode(4);
		p.right = new TreeNode(8);
		temp = p.left;
		temp.left = new TreeNode(11);
		temp.right = null;
		temp = temp.left;
		temp.left = new TreeNode(7);
		temp.right = new TreeNode(2);
		temp = p.right;
		temp.left = new TreeNode(13);
		temp.right = new TreeNode(4);
		temp = temp.right;
		temp.left = null;
		temp.right = new TreeNode(1);
		
		Number112 n = new Number112();
		n.hasPathSum(p, 22);
	}
}
