import java.util.ArrayList;
import java.util.List;

/*
二叉搜索树中的两个节点被错误地交换。
请在不改变其结构的情况下，恢复这棵树。

示例 1:
输入: [1,3,null,null,2]
   1
  /
 3
  \
   2
输出: [3,1,null,null,2]
   3
  /
 1
  \
   2

示例 2:
输入: [3,1,4,null,null,2]
  3
 / \
1   4
   /
  2
输出: [2,1,4,null,null,3]
  2
 / \
1   4
   /
  3
 
进阶:
使用O(n)空间复杂度的解法很容易实现。
你能想出一个只使用常数空间的解决方案吗？
*/

/*
 * 交换的位置的话就是两种情况
 * 相邻的两个数字交换
 * [1， 2，3，4，5]中2和3进行交换[1，3，2，4，5]
 * 这样的话只产生一组逆序的数字(正常情况是从小到大排序，交换后产生了从大到小)，3， 2。
 * 我们只需要遍历数组，找到后，把这一组的两个数字进行交换即可。
 * 不相邻的两个数字交换
 * [1， 2，3，4，5]中2和5进行交换[1， 5，3，4，2]
 * 这样的话其实就是产生了两组逆序的数字对，5，3 和4，2。
 * 所以我们只需要遍历数组，然后找到这两组逆序对，然后把第一组前一个数字和第二组后一个数字进行交换即完成了还原。
 * 只需要利用一个pre节点和当前节点比较
 * 如果pre节点的值大于当前节点的值，那么就是我们要找的逆序的数字
 * 分别用两个指针first和second保存即可
 * 如果找到第二组逆序的数字，我们就把second更新为当前节点
 * 最后把first和second两个的数字交换即可
 * O(1)情况的就是使用Morris遍历
 */
public class Number99 {
	TreeNode first = null;
	TreeNode second = null;
    public void recoverTree(TreeNode root) {
        List<TreeNode> stack = new ArrayList<>();
        TreeNode pre = null;
        
        //中序遍历树
        while(root != null || !stack.isEmpty()) {
        	while(root != null) {
        		stack.add(root);
        		root = root.left;
        	}
        	root = stack.remove(stack.size() - 1);
        	//找到逆序数字对
        	if(pre != null && root.val < pre.val) {
                if (first == null) {
                    first = pre;
                    second = root;
                }else {
                    second = root;
                }
            }
            pre = root;
            root = root.right;
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode temp = root;
		temp.left = new TreeNode(1);
		temp.right = new TreeNode(4);
		temp = temp.right;
		temp.left = new TreeNode(2);
		Number99 n = new Number99();
		n.recoverTree(root);
	}
}
