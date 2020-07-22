import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的 后序 遍历。

示例:

输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/

/*
 * 对于后序遍历，当我们到达根节点的时候并不能立刻把根节点弹出，因为遍历完右子树，我们还需要将这个根节点加入到list中。
 * 所以我们就需要判断是从左子树到的根节点，还是右子树到的根节点
 * 如果是从左子树到的根节点，此时应该转到右子树
 * 如果是从右子树到的根节点，那么就可以把当前节点弹出，并且加入到list中
 * 当然，如果是从左子树到的根节点，此时如果根节点的右子树为 null， 此时也可以把当前节点弹出，并且加入到list中。
 * 解决办法：
 * 把每个节点push两次，然后判断当前pop节点和栈顶节点是否相同。
 * 相同的话，就意味着是从左子树到的根节点。
 * 不同的话，就意味着是从右子树到的根节点，此时就可以把节点加入到 list 中。
 */

public class Number145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if(cur == null) {
                continue;
            }
            if(!stack.isEmpty() && cur == stack.peek()) {
                stack.push(cur.right);
                stack.push(cur.right);
                stack.push(cur.left);
                stack.push(cur.left);
            }else {
                ans.add(cur.val);
            }
        }
        return ans;
    }
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode temp = root;
		root.right = new TreeNode(2);
		temp = root.right;
		temp.left = new TreeNode(3);
		
		Number145 n = new Number145();
		System.out.println(n.postorderTraversal(root).toString());
	}
}
