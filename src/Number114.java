/*
给定一个二叉树，原地将它展开为链表。
例如，给定二叉树

    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
*/

public class Number114 {
    public void flatten(TreeNode root) {
        while(root != null) {
        	if(root.left == null) {
                root = root.right;
            }else {
            	//找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                } 
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(1);
		TreeNode temp = p;
		p.left = new TreeNode(2);
		p.right = new TreeNode(5);
		temp = p.left;
		temp.left = new TreeNode(3);
		temp.right = new TreeNode(4);
		temp = p.right;
		temp.left = new TreeNode(13);
		temp.right = new TreeNode(4);
		temp = temp.right;
		temp.left = null;
		temp.right = new TreeNode(6);
		
		Number114 n = new Number114();
		n.flatten(p);
	}
}
