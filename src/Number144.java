import java.util.ArrayList;
import java.util.List;

/*
给定一个二叉树，返回它的前序遍历。

示例:
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [1,2,3]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/

public class Number144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) {
        	return ans;
        }
        
        List<TreeNode> stack = new ArrayList<>();
        stack.add(root);
        while(!stack.isEmpty()) {
        	while(root != null) {
        		ans.add(root.val);
        		stack.add(root);
        		root = root.left;
        	}
        	root = stack.remove(stack.size() - 1);
        	root = root.right;
        }
        return ans;
    }
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode temp = root;
		root.right = new TreeNode(2);
		temp = root.right;
		temp.left = new TreeNode(3);
		
		Number144 n = new Number144();
		n.preorderTraversal(root);
	}
}
