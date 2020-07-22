/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 
示例:
给定 1->2->3->4, 你应该返回 2->1->4->3.
*/

public class Number24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
        	return head;
        }
        
        ListNode dumbNode = new ListNode(0);
        dumbNode.next = head;
        ListNode tempNode = dumbNode;
        ListNode firstNode = new ListNode(0);
        ListNode secondNode = new ListNode(0);
        
        while(tempNode.next != null && tempNode.next.next != null) {
        	firstNode = tempNode.next;
        	secondNode = tempNode.next.next;
        	tempNode.next = secondNode;
        	firstNode.next = secondNode.next;
        	secondNode.next = firstNode;
        	tempNode = firstNode;
        }
        return dumbNode.next;
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
    	
    	Number24 n = new Number24();
    	list = n.swapPairs(list);
    	
    	while(list != null) {
    		System.out.println(list);
    		list = list.next;
    	}
    }
}
