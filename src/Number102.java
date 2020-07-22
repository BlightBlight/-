import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
给定一个二叉树，返回其按层次遍历的节点值。(即逐层地，从左到右访问所有节点)
例如:
给定二叉树: [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：
[
  [3],
  [9,20],
  [15,7]
]
*/

public class Number102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
        	return ans;
        }
        
        ans.add(Arrays.asList(root.val));
        List<Integer> level = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
    	if(root.left != null) {
			q.add(root.left);
		}
		if(root.right != null) {
			q.add(root.right);
		}
		root = q.peek();
				
        while(root != null) {
        	int temp = q.size();
        	while(temp > 0) {
        		root = q.poll();
    			level.add(root.val);
    			if(root.left != null) {
    				q.add(root.left);
    			}
    			if(root.right != null) {
    				q.add(root.right);
    			}
    			temp--;
        	}
        	ans.add(level);
        	level = new ArrayList<>();
        	root = q.peek();
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
		Number102 n = new Number102();
		System.out.println(n.levelOrder(p).toString());
	}
}
