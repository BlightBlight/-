import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
例如：
给定二叉树 [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：
[
  [15,7],
  [9,20],
  [3]
]
*/

public class Number107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> ans = new LinkedList<List<Integer>>();
		if(root == null) {
			return ans;
		}
		queue.offer(root);
		while(!queue.isEmpty()) {
			int levelNum = queue.size(); // 当前层元素的个数
			List<Integer> subList = new LinkedList<Integer>();
			for(int i = 0; i < levelNum; i++) {
				TreeNode curNode = queue.poll();
				if(curNode != null) {
					subList.add(curNode.val);
					queue.offer(curNode.left);
					queue.offer(curNode.right);
				}
			}
			if(subList.size() > 0) {
				ans.add(0, subList);
			}
		}
		return ans;
    }
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(3);
		TreeNode temp = p;
		p.left = new TreeNode(9);
		p.right = new TreeNode(20);
		temp = p.right;
		temp.left = new TreeNode(15);
		temp.right = new TreeNode(7);
		
		Number107 n = new Number107();
		n.levelOrderBottom(p);
	}
}
