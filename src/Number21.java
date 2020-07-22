/*
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
*/

public class Number21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode tempNode = new ListNode(0);
        ListNode ansNode = tempNode;
        while(l1 != null || l2 != null) {
        	if(l1!= null && l2 != null) {
        		if(l1.val >= l2.val) {
        			tempNode.next = l2;
        			tempNode = tempNode.next;
        			l2 = l2.next;
        		}else {
        			tempNode.next = l1;
        			tempNode = tempNode.next;
        			l1 = l1.next;
        		}
        	}else if(l1 != null) {
        		tempNode.next = l1;
        		tempNode = tempNode.next;
        		l1 = l1.next;
        	}else {
        		tempNode.next = l2;
        		tempNode = tempNode.next;
    			l2 = l2.next;
        	}
        }
        return ansNode.next;
    }
    
    public static void main(String[] args) {
    	ListNode list = new ListNode(1);
    	ListNode temp = list;
    	temp.next = new ListNode(2);
    	temp = temp.next;
    	temp.next = new ListNode(4);
    	temp = temp.next;
    	
    	ListNode list1 = new ListNode(1);
    	ListNode temp1 = list1;
    	temp1.next = new ListNode(3);
    	temp1 = temp1.next;
    	temp1.next = new ListNode(4);
    	temp1 = temp1.next;
    	
    	Number21 n = new Number21();
    	ListNode l = n.mergeTwoLists(list, list1);
    	while(l != null) {
    		System.out.println(l);
    		l = l.next;
    	}
    }
}
