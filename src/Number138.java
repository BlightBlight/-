/*
给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
要求返回这个链表的深拷贝。 

示例：

输入：
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

解释：
节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 
提示：
你必须返回给定头的拷贝作为对克隆列表的引用。
*/

/*
 * 看图138_1
 * 遍历原来的链表并拷贝每一个节点，将拷贝节点放在原来节点的旁边，创造出一个旧节点和新节点交错的链表
 * 迭代这个新旧节点交错的链表，并用旧节点的random指针去更新对应新节点的random指针
 * 比方说，B的random指针指向A，意味着B'的random指针指向A'
 * 现在random指针已经被赋值给正确的节点，next指针也需要被正确赋值，以便将新的节点正确链接同时将旧节点重新正确链接
 */

public class Number138 {
	public Node138 copyRandomList(Node138 head) {
		if(head == null) {
			return null;
		}

		Node138 ptr = head;
		/*
		 * 遍历链表，复制每个节点，将新节点的next指向旧节点的next，旧节点的next指向新节点
		 * A -> B变成A -> A' -> B
		 */
		while(ptr != null) {
			Node138 newNode = new Node138(ptr.val);
			newNode.next = ptr.next;
			ptr.next = newNode;
			ptr = newNode.next;
		}

		ptr = head;
		/*
		 * 遍历链表，将每个新节点的random赋值为旧节点的相应random
		 * B -> A变成B' -> A'
		 */
		while(ptr != null) {
			ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
			ptr = ptr.next.next;
		}

		Node138 ptr_old_list = head;
		Node138 ptr_new_list = head.next;
		Node138 head_old = head.next;
		//将新旧节点链表拆分
		while (ptr_old_list != null) {
			ptr_old_list.next = ptr_old_list.next.next;
			ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
			ptr_old_list = ptr_old_list.next;
			ptr_new_list = ptr_new_list.next;
		}
		return head_old;
	}
    
	public static void main(String[] args) {
		Node138 head = new Node138();
		head.val = 1;
		Node138 temp = new Node138();
		temp.val = 2;
		head.next = temp;
		head.random = temp;
		temp.next = null;
		temp.random = temp;
		
		Number138 n = new Number138();
		n.copyRandomList(head);
	}
}
