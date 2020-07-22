import java.util.ArrayList;
import java.util.List;

/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。
假设一个二叉搜索树具有如下特征：
节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

示例 1:
输入:
    2
   / \
  1   3
输出: true

示例 2:
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
*/

public class Number98 {
	public boolean isValidBST(TreeNode root) {
		List<TreeNode> stack = new ArrayList<>();
		double inorder = -Double.MAX_VALUE;

		while(!stack.isEmpty() || root != null) {
			while(root != null) {
				stack.add(root);
				root = root.left;
			}
			root = stack.remove(stack.size() - 1);
			if(root.val <= inorder) {
				return false;
			}
			inorder = root.val;
			root = root.right;
		}
		return true;
	}
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		TreeNode temp = root;
		temp.left = new TreeNode(1);
		temp.right = new TreeNode(4);
		temp = temp.right;
		temp.left = new TreeNode(3);
		temp.right = new TreeNode(6);
		Number98 n = new Number98();
		System.out.println(n.isValidBST(root));
	}
}
