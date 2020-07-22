import java.util.Comparator;
import java.util.PriorityQueue;

/*
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
*/

public class Number23 {
    public ListNode mergeKLists(ListNode[] lists) {
    	if(lists == null || lists.length == 0) {
    		return null;
    	}
    	/*
    	 * 最小堆、优先队列都可以
    	 */
    	PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val < o2.val) {
                	return -1;
                }else if (o1.val == o2.val) {
                	return 0;
                }else {
                	return 1;
                }
            }
        });
        ListNode dumbNode = new ListNode(0);
        ListNode p = dumbNode;
        for(ListNode node : lists) {
            if(node != null) {
            	queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if(p.next != null) {
            	queue.add(p.next);
            }
        }
        return dumbNode.next;
    }
    
    public static void main(String[] args) {
    	ListNode list = new ListNode(1);
    	ListNode temp = list;
    	temp.next = new ListNode(4);
    	temp = temp.next;
    	temp.next = new ListNode(5);
    	temp = temp.next;
    	
    	ListNode list1 = new ListNode(1);
    	ListNode temp1 = list1;
    	temp1.next = new ListNode(3);
    	temp1 = temp1.next;
    	temp1.next = new ListNode(4);
    	temp1 = temp1.next;
    	
    	ListNode list2 = new ListNode(2);
    	ListNode temp2 = list2;
    	temp2.next = new ListNode(6);
    	temp2 = temp2.next;
    	
    	ListNode[] lists = new ListNode[] {list, list1, list2};
    	
    	Number23 n = new Number23();
    	n.mergeKLists(lists);
    }
}
