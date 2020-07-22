import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层次遍历如下：

[
  [3],
  [20,9],
  [15,7]
]
*/

public class Number103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(root == null) {
            return ans;
        }
        // tmp记录当前层的节点val
        List<Integer> tmp = new ArrayList<>();
        // count记录当前层次的节点个数
        int count = 1;
        //flag = false表示奇数层，flag = true表示偶数层，root为奇数
        boolean flag = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode cur;
        while(!queue.isEmpty()){
            //奇数层:弹出队首元素; 左右孩子加入队尾
            if(flag == false){
                cur = queue.pollFirst();
                tmp.add(cur.val);
                if(cur.left != null){
                    queue.addLast(cur.left);
                }
                if(cur.right != null){
                    queue.addLast(cur.right);
                }
            }
            //偶数层:弹出队尾元素; 右左孩子加入队首
            else{
                cur = queue.pollLast();
                tmp.add(cur.val);
                if(cur.right != null){
                    queue.addFirst(cur.right);
                }
                if(cur.left != null){
                    queue.addFirst(cur.left);
                }
            }
            count--;
            if(count == 0){
                count = queue.size();
                flag = !flag;
                ans.add(new ArrayList<Integer>(tmp));
                tmp.clear();
            }
        }
        return ans;
    }
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(3);
		TreeNode temp = p;
		p.left = new TreeNode(9);
		p.right = new TreeNode(20);
		temp = p.right;
		temp.left = new TreeNode(15);
		temp.right = new TreeNode(7);
		Number103 n = new Number103();
		System.out.println(n.zigzagLevelOrder(p).toString());
	}
}
