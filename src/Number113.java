import java.util.ArrayList;
import java.util.List;

/*
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

/*
 * 这题有个关键条件千万别看漏，所有从根节点到叶子节点路径总和，必须是叶子结点
 */

public class Number113 {
	List<List<Integer>> ans;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	ans = new ArrayList<>();
        backtrack(root, new ArrayList<>(), sum);
        return ans;
    }
    
    public void backtrack(TreeNode root, List<Integer> stack, int sum) {
    	if(root == null) {
    		return;
    	}
    	stack.add(root.val);
    	if(root.left == null && root.right == null && sum - root.val == 0 ) {
    		ans.add(new ArrayList<>(stack));
    	}
    	backtrack(root.left, stack, sum - root.val);
    	backtrack(root.right, stack, sum - root.val);
    	stack.remove(stack.size() - 1);
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
		temp.left = new TreeNode(5);
		temp.right = new TreeNode(1);
		
		Number113 n = new Number113();
		n.pathSum(p, 22);
	}
}
