/*
编写一个程序，找到两个单链表相交的起始节点。
如下面的两个链表：

在节点 c1 开始相交。

示例 1：

输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8(注意，如果两个列表相交则不能为 0)
从各自的表头开始算起，链表A为 [4,1,8,4,5]，链表B为 [5,0,1,8,4,5]
在A中，相交节点前有2个节点；在B中，相交节点前有3个节点。

示例 2：
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为2(注意，如果两个列表相交则不能为 0)
从各自的表头开始算起，链表A为[0,9,1,2,4]，链表B为[3,2,4]
在A中，相交节点前有3个节点；在B中，相交节点前有1个节点。

示例 3：
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表A为[2,6,4]，链表B为[1,5]
由于这两个链表不相交，所以intersectVal必须为0，而skipA和skipB可以是任意值。
解释：这两个链表不相交，因此返回 null。

注意：
如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
*/

/*
 * 创建两个指针pA和pB，分别初始化为链表A和B的头结点。然后让它们向后逐结点遍历
 * 当pA到达链表的尾部时，将它重定位到链表B的头结点
 * 类似的，当pB到达链表的尾部时，将它重定位到链表A的头结点
 * 若在某一时刻pA和pB相遇，则pA/pB为相交结点
 * 可以理解为先计算两个链表的长度，将长度较长的那个链表的临时指针向后滑动
 * 直到临时指针到最后一个节点的链表长度等于最短链表的长度
 * 然后将两个链表临时头指针每滑动一次比较一次，如果相等，该节点即为相交的节点，滑动到链表最后都不存在相同的节点，则说明链表不相交
 */
public class Number160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	if (headA == null || headB == null) {
    		return null;
    	}
    	
        ListNode tempA = headA;
        ListNode tempB = headB;
        
        /*
         * 若相交，则必然有相等
         * 若不相交，则最后一轮tempA、tempB都为空，则退出
         */
        while (tempA != tempB) {
        	tempA = tempA == null ? headB : tempA.next;
        	tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }
    
	public static void main(String[] args) {
		ListNode headA = new ListNode(4);
		ListNode temp = headA;
		temp.next = new ListNode(1);
		temp = temp.next;
		ListNode node8 = new ListNode(8);
		temp.next = node8;
		temp = temp.next;
		ListNode node4 = new ListNode(4);
		temp.next = node4;
		temp = temp.next;
		ListNode node5 = new ListNode(5);
		temp.next = node5;
		
		ListNode headB = new ListNode(5);
		temp = headB;
		temp.next = new ListNode(0);
		temp = temp.next;
		temp.next = new ListNode(1);
		temp = temp.next;
		temp.next = node8;
		temp = temp.next;
		temp.next = node4;
		temp = temp.next;
		temp.next = node5;
		
		Number160 n = new Number160();
		n.getIntersectionNode(headA, headB);
	}
}
