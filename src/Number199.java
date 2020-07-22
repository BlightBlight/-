import java.util.ArrayList;
import java.util.List;

/*
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/

public class Number199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        
        if(root == null) {
        	return ans;
        }
        
        List<TreeNode> stack = new ArrayList<>();
        stack.add(root);
        while(root != null || !stack.isEmpty()) {
        	int size = stack.size();
        	while(size > 0) {
        		root = stack.remove(0);
        		if(root.left != null) {
        			stack.add(root.left);
        		}
        		if(root.right != null) {
        			stack.add(root.right);
        		}
        		size--;
        	}
        	ans.add(root.val);
        	root = stack.isEmpty() ? null : stack.get(0);
        }
        return ans;
    }
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode temp = root;
		temp.left = new TreeNode(2);
		temp.right = new TreeNode(3);
		temp = root.left;
		temp.right = new TreeNode(5);
		temp = root.right;
		temp.right = new TreeNode(4);
		
		Number199 n = new Number199();
		System.out.println(n.rightSideView(root));
	}
}
