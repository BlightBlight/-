import java.util.Deque;
import java.util.LinkedList;

/*
给定两个二叉树，编写一个函数来检验它们是否相同。
如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

示例 1:
输入:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]
输出: true

示例 2:
输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]
输出: false

示例 3:
输入:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]
输出: false
*/

public class Number100 {
	public boolean isSameTree(TreeNode p, TreeNode q) {
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        stack1.push(p);
        stack2.push(q);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode a = stack1.pop();
            TreeNode b = stack2.pop();
            if(a == null && b == null) {
            	continue;
            }
            if(a != null && b != null && a.val == b.val) {
                stack1.push(a.left);
                stack1.push(a.right);
                stack2.push(b.left);
                stack2.push(b.right);
            }else {
            	return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(1);
		p.left = new TreeNode(2);
		p.right = new TreeNode(1);
		
		TreeNode q = new TreeNode(1);
		q.left = new TreeNode(1);
		q.right = new TreeNode(2);
		
		Number100 n = new Number100();
		n.isSameTree(p, q);
	}
}
