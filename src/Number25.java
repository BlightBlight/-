/*
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
k 是一个正整数，它的值小于或等于链表的长度。
如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

示例 :
给定这个链表：1->2->3->4->5
当 k = 2 时，应当返回: 2->1->4->3->5
当 k = 3 时，应当返回: 3->2->1->4->5

说明 :
你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
*/

/*
 * 分成k个子链表翻转
 */
public class Number25 {
	public ListNode reverseKGroup(ListNode head, int k) {
	    ListNode dumbNode = new ListNode(0);
	    dumbNode.next = head;

	    ListNode preNode = dumbNode;//总链表头节点前一个节点
	    ListNode curNode = dumbNode;//总链表当前结点
	    ListNode startNode = new ListNode(0);//翻转子链表头节点
	    ListNode nextNode = new ListNode(0);//翻转子链表尾节点下一个节点
	    while (curNode.next != null) {
	        for(int i = 0; i < k && curNode != null; i++) { 
	        	curNode = curNode.next;
	        }
	        //全部翻转
	        if(curNode == null) {
	        	break;
	        }
	        startNode = preNode.next;
	        nextNode = curNode.next;
	        curNode.next = null;
	        preNode.next = reverseList(startNode);//翻转子链表
	        startNode.next = nextNode;
	        
	        //新一轮开始
	        curNode = preNode = startNode;
	    }
	    return dumbNode.next;
	}
	/*
	 * 翻转子链表
	 * null->1->2->3 变成  null<-1<-2<-3
	 */
	private ListNode reverseList(ListNode headNode) {
	    ListNode preNode = null;
	    ListNode curNode = headNode;
	    ListNode nextNode = new ListNode(0);
	    while (curNode != null) {
	        nextNode = curNode.next;
	        curNode.next = preNode;
	        preNode = curNode;
	        curNode = nextNode;
	    }
	    return preNode;
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
    	
    	Number25 n = new Number25();
    	list = n.reverseKGroup(list, 2);
    	
    	while(list != null) {
    		System.out.println(list);
    		list = list.next;
    	}
    }
}
