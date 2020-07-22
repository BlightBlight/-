/*
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL

解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL

示例 2:
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL

解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
*/

public class Number61 {
    public ListNode rotateRight(ListNode head, int k) {
    	if(head == null) {
    		return null;
    	}
        ListNode tail = head;//尾部
        int length = 1;
        while(tail.next != null) {
        	tail = tail.next;
        	length++;
        }
        
        k = k % length;
        
        tail.next = head;
        for(int i = 0; i < length - k - 1; i++) {
        	head = head.next;
        }
        tail = head.next;
        head.next = null;
        return tail;
    }
    
	public static void main(String[] args) {
		ListNode temp = new ListNode(1);
		ListNode head = temp;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(4);
		temp = temp.next;
		temp.next = new ListNode(5);
		temp = temp.next;
		int k = 2;
    	Number61 n = new Number61();
    	ListNode ans = n.rotateRight(head, k);
    	while(ans != null) {
    		System.out.println(ans);
    		ans = ans.next;
    	}
    }
}
