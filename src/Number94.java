import java.util.ArrayList;
import java.util.List;

/*
给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/

public class Number94 {
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> ans = new ArrayList<>();
        if(root == null) {
        	return ans;
        }
        
        List<TreeNode> stack = new ArrayList<>();
        TreeNode temp = root;
        while(temp != null || !stack.isEmpty()) {
        	while(temp != null) {
        		stack.add(temp);
        		temp = temp.left;
        	}
        	temp = stack.remove(stack.size() - 1);
        	ans.add(temp.val);
        	temp = temp.right;
        }
        return ans;
    }
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode temp = root;
		temp.right = new TreeNode(2);
		temp = temp.right;
		temp.left = new TreeNode(3);
		Number94 n = new Number94();
		n.inorderTraversal(root);
	}
}
