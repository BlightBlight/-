/*
给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
你应当保留两个分区中每个节点的初始相对位置。

示例:
输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5
*/

public class Number86 {
	public ListNode partition(ListNode head, int x) {
        ListNode before_head = new ListNode(0);
        //比x值小的放在这个链表
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        //比x值大的放在这个链表
        ListNode after = after_head;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        //合并两个链表
        before.next = after_head.next;
        return before_head.next;
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
		temp.next = new ListNode(4);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(5);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;
		int x = 3;
		Number86 n = new Number86();
    	System.out.println(n.partition(ans, x));
	}
}
