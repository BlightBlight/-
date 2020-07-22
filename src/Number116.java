/*
给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个next指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
初始状态下，所有 next 指针都被设置为 NULL。

示例：
输入：
{"$id":"1","left":
{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":
{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":
{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":
{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

输出：
{"$id":"1","left":
{"$id":"2","left":
{"$id":"3","left":null,"next":
{"$id":"4","left":null,"next":
{"$id":"5","left":null,"next":
{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":
{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
提示：
你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
*/

public class Number116 {
    public Node connect(Node root) {
        Node start = root;
        while(start != null) {
            Node cur = start;
            while(cur != null) {
                if(cur.left != null) {
                	cur.left.next = cur.right;
                }
                if(cur.right != null && cur.next != null) {
                	cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            start = start.left;
        }
        return root;
    }
    
	public static void main(String[] args) {
		Node root = new Node(1);
		Node temp = root;
		root.left = new Node(2);
		root.right = new Node(3);
		temp = root.left;
		temp.left = new Node(4);
		temp.right = new Node(5);
		temp = root.right;
		temp.left = new Node(6);
		temp.right = new Node(7);
		
		Number116 n = new Number116();
		n.connect(root);
	}
}
