/*
反转从位置m到n的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:
输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
*/

public class Number92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
		if(head == null) {
			return null;
		}
		//当前指向位置和前一个位置
		ListNode cur = head, prev = null;
		//m和n减是因为整体往后移动了
		while(m > 1) {
			prev = cur;
			cur = cur.next;
			m--;
			n--;
		}
		//存储前一个位置和当前指向位置，这两个坐标就是最终的头节点和尾节点
		ListNode con = prev, tail = cur;
		//当前位置下一个位置
		ListNode next = null;
		/*
		 * 取出下一个位置
		 * 当前位置指向前一个位置
		 * 当前位置和前一个位置往后移动
		 * 循环n次
		 * 相当于把1->2->3->4->5
		 * 转换成1-> <-2<-3<-4 5
		 * con = 1, tail = 2
		 * prev = 4, cur = 5
		 */
		while(n > 0) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			n--;
		}
		/*
		 * 把上面弄乱的头、尾节点重新整理
		 */
		//最终头节点指向prev
		if(con != null) {
			con.next = prev;
		}else {
			head = prev;
		}
		//最终尾节点指向cur
		tail.next = cur;
		return head;
	}
    
	public static void main(String[] args) {
/*		ListNode head = new ListNode(1);
		ListNode temp = ans;
		temp.next = new ListNode(1);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;*/
		ListNode head = new ListNode(1);
		ListNode temp = head;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(4);
		temp = temp.next;
		temp.next = new ListNode(5);
		int m = 2;
		int n1 = 4;
		Number92 n = new Number92();
    	n.reverseBetween(head, m, n1);
	}
}
