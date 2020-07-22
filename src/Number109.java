/*
给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
示例:
给定的有序链表： [-10, -3, 0, 5, 9],
一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
      0
     / \
   -3   9
   /   /
 -10  5
 */

public class Number109 {
	private ListNode head;

	private int findSize(ListNode head) {
		ListNode ptr = head;
		int c = 0;
		while(ptr != null) {
			ptr = ptr.next;
			c += 1;
		}
		return c;
	}
	
	/*
	 * 模拟中序遍历
	 * 注意这里并不需要在链表中找到确定的元素是哪个，只需要用一个变量mid告诉我们中间元素的下标。我们只需要递归调用这两侧
	 * 递归左半边，其中开始和结束的值分别为start, mid - 1
	 * 递归到空后，我们只需使用头指针指向的当前值作为根节点，并将指针后移一位，即head = head.next
	 * 递归右半边，其中开始和结束的值分别为mid + 1, end
	 */
	private TreeNode convertListToBST(int l, int r) {
		if(l > r) {
			return null;
		}

		int mid = (l + r) >>> 1;
		TreeNode left = this.convertListToBST(l, mid - 1);
		TreeNode node = new TreeNode(this.head.val);
		node.left = left;
		this.head = this.head.next;
		node.right = this.convertListToBST(mid + 1, r);
		return node;
	}

	public TreeNode sortedListToBST(ListNode head) {
		int size = this.findSize(head);
		this.head = head;
		return convertListToBST(0, size - 1);
	}
    
	public static void main(String[] args) {
		ListNode head = new ListNode(-10);
		ListNode temp = head;
		temp.next = new ListNode(-3);
		temp = temp.next;
		temp.next = new ListNode(0);
		temp = temp.next;
		temp.next = new ListNode(5);
		temp = temp.next;
		temp.next = new ListNode(9);
		temp = temp.next;
		
		Number109 n = new Number109();
		n.sortedListToBST(head);
	}
}
