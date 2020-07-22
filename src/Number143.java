/*
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:
给定链表 1->2->3->4, 重新排列为 1->4->2->3.

示例 2:
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
*/

public class Number143 {
    public void reorderList(ListNode head) {
    	if (head == null || head.next == null || head.next.next == null) {
            return;
        }
    	
        ListNode slow = head;
        ListNode fast = head;
        //利用快慢指针找到中心节点
        while(fast.next != null && fast.next.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        
        //翻转后半部分链表
        newHead = reverseList(newHead);
        
        //链表节点依次连接
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = temp;
        }
    }
    
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode tail = head;
        head = head.next;
        tail.next = null;
        while(head != null) {
            ListNode temp = head.next;
            head.next = tail;
            tail = head;
            head = temp;
        }
        return tail;
    }
    
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode temp = head;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(4);
		temp = temp.next;
		temp.next = new ListNode(5);
		
		Number143 n = new Number143();
		n.reorderList(head);
	}
}
