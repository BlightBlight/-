/*
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:
输入: 1->1->2
输出: 1->2

示例 2:
输入: 1->1->2->3->3
输出: 1->2->3
*/

public class Number83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dumb = new ListNode(-1);
        ListNode node = dumb;
        
        while(head != null) {
        	while(head != null && head.next != null && head.val == head.next.val) {
        		head = head.next;
        	}
        	dumb.next = head;
        	dumb = dumb.next;
        	head = head.next;
        }
        return node.next;
    }
    
	public static void main(String[] args) {
/*		ListNode ans = new ListNode(1);
		ListNode temp = ans;
		temp.next = new ListNode(1);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;*/
		ListNode ans = new ListNode(1);
		ListNode temp = ans;
		temp.next = new ListNode(1);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		Number83 n = new Number83();
    	System.out.println(n.deleteDuplicates(ans));
	}
}
