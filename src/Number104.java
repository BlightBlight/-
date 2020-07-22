import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树，找出其最大深度。
二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。
*/

public class Number104 {
	public int maxDepth(TreeNode root) {
		Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
		if(root != null) {
			stack.add(new Pair<TreeNode, Integer>(root, 1));
		}

		int depth = 0;
		while(!stack.isEmpty()) {
			Pair<TreeNode, Integer> current = stack.poll();
			root = current.getKey();
			int current_depth = current.getValue();
			if(root != null) {
				depth = Math.max(depth, current_depth);
				stack.add(new Pair<TreeNode, Integer>(root.left, current_depth + 1));
				stack.add(new Pair<TreeNode, Integer>(root.right, current_depth + 1));
			}
		}
		return depth;
	}
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(3);
		TreeNode temp = p;
		p.left = new TreeNode(9);
		p.right = new TreeNode(20);
		temp = p.right;
		temp.left = new TreeNode(15);
		temp.right = new TreeNode(7);
		
		Number104 n = new Number104();
		System.out.println(n.maxDepth(p));
	}
}
