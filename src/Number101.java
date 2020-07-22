import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树，检查它是否是镜像对称的。
例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
    1
   / \
  2   2
   \   \
   3    3
说明:
如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
*/

public class Number101 {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()) {
        	TreeNode left = q.poll();
        	TreeNode right = q.poll();
        	if(left == null && right == null) {
        		continue;
        	}else if(left == null || right == null) {
        		return false;
        	}else if(left.val != right.val) {
        		return false;
        	}
        	q.add(left.left);
        	q.add(right.right);
        	q.add(left.right);
        	q.add(right.left);
        }
        return true;
    }
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(1);
		TreeNode temp = p;
		p.left = new TreeNode(2);
		p.right = new TreeNode(2);
		temp = p.left;
		temp.left = new TreeNode(3);
		temp.right = new TreeNode(4);
		temp = p.right;
		temp.left = new TreeNode(4);
		temp.right = new TreeNode(3);
		
		Number101 n = new Number101();
		n.isSymmetric(p);
	}
}
