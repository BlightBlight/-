/*
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:
输入: 1->2->3->3->4->4->5
输出: 1->2->5

示例 2:
输入: 1->1->1->2->3
输出: 2->3
*/

public class Number82 {
    public ListNode deleteDuplicates(ListNode head) {
    	ListNode dumb = new ListNode(-1);
        ListNode node = dumb;
        
        while(head != null) {
        	boolean dup = false;//头结点有没有改变
        	/*
        	 * 头节点改变了，就不动哑节点，继续头结点再次遍历，防止头节点多个数字重复
        	 * 如，2->3->3->4->4->5，头结点遍历到第二个3时则不满足while
        	 * 哑结点不动，头结点等于next，从4开始遍历，重复上面操作，直到头结点不改变为止
        	 * 把哑结点的next指向头节点，哑节点等于next
        	 */
	    	while(head != null && head.next != null && head.val == head.next.val) {
	    		head = head.next;
	    		dup = true;
	    	}
	    	if(!dup) {
	    		dumb.next = head;
	    		dumb = dumb.next;
	    	}
	    	head = head.next;
	    }
        dumb.next = null;//防止哑节点后面还有重复的节点
        return node.next;
    }
    
	public static void main(String[] args) {
/*		ListNode ans = new ListNode(1);
		ListNode temp = ans;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(4);
		temp = temp.next;
		temp.next = new ListNode(4);
		temp = temp.next;
		temp.next = new ListNode(5);
		temp = temp.next;*/
		ListNode ans = new ListNode(1);
		ListNode temp = ans;
		temp.next = new ListNode(1);
		temp = temp.next;
		temp.next = new ListNode(1);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		Number82 n = new Number82();
    	System.out.println(n.deleteDuplicates(ans));
	}
}
