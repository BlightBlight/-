/*
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：
给定的 n 保证是有效的。
*/

public class Number19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode first = dummyNode;
        ListNode second = dummyNode;
        
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyNode.next;
    }
	
    public static void main(String[] args) {
    	ListNode list = new ListNode(1);
    	ListNode temp = list;
    	temp.next = new ListNode(2);
    	temp = temp.next;
    	temp.next = new ListNode(3);
    	temp = temp.next;
    	temp.next = new ListNode(4);
    	temp = temp.next;
    	temp.next = new ListNode(5);
    	temp = temp.next;
    	Number19 n = new Number19();
    	list = n.removeNthFromEnd(list, 2);
    	while(list != null) {
    		System.out.println(list.toString());
    		list = list.next;
    	}
    }
}
