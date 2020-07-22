import java.util.ArrayList;
import java.util.List;

/*
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
调用next()将返回二叉搜索树中的下一个最小的数。

示例：
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // 返回 3
iterator.next();    // 返回 7
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 9
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 15
iterator.hasNext(); // 返回 true
iterator.next();    // 返回 20
iterator.hasNext(); // 返回 false
 
提示：
next()和hasNext()操作的时间复杂度是O(1)，并使用O(h)内存，其中h是树的高度。
你可以假设next()调用总是有效的，也就是说，当调用next()时，BST中至少存在一个下一个最小的数。
*/

public class Number173 {
	class BSTIterator {
		List<TreeNode> stack = new ArrayList<>();
		TreeNode cur = null;
	    public BSTIterator(TreeNode root) {
	    	cur = root;
	    }
	    
	    public int next() {
	        int res = -1;
	        while(cur != null || !stack.isEmpty()) {
		        while(cur != null) {
		        	stack.add(cur);
		        	cur = cur.left;
		        }
		        cur = stack.remove(stack.size() - 1);
		        res = cur.val;
		        cur = cur.right;
		        break;
	        }
	        return res;
	    }
	    
	    public boolean hasNext() {
	        return (!stack.isEmpty() || cur != null);
	    }
	}
}
