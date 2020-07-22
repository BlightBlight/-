import java.util.HashMap;
import java.util.Map;

/*
根据一棵树的前序遍历与中序遍历构造二叉树。
注意:
你可以假设树中没有重复的元素。
例如，给出
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
*/

public class Number105 {
	private int[] preorder;
	//把中序遍历的顺序存放在hashMap中，循环时不用遍历查找位置，直接在Map中get
    private Map<Integer, Integer> hash;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        this.preorder = preorder;
        this.hash = new HashMap<>();
        for(int i = 0; i < inLen; i++) {
            hash.put(inorder[i], i);
        }

        return buildTree(0, preLen - 1, 0, inLen - 1);
    }


    private TreeNode buildTree(int preLeft, int preRight, int inLeft, int inRight) {
        //因为是递归调用的方法，按照国际惯例，先写递归终止条件
        if(preLeft > preRight || inLeft > inRight) {
            return null;
        }
        //先序遍历的起点元素很重要
        int pivot = preorder[preLeft];
        TreeNode root = new TreeNode(pivot);
        int pivotIndex = hash.get(pivot);
        /*
         * pivotIndex - inLeft表示以pivotIndex为根节点时左子树的数量,前序遍历右边界最多为左子树数量
         * pivotIndex - inLeft + preLeft + 1表示排除左子树个数后下一个位置就是右子树左边界
         */
        root.left = buildTree(preLeft + 1, pivotIndex - inLeft + preLeft,
                inLeft, pivotIndex - 1);
        root.right = buildTree(pivotIndex - inLeft + preLeft + 1, preRight,
                pivotIndex + 1, inRight);
        return root;
    }
    
	public static void main(String[] args) {
		TreeNode p = new TreeNode(3);
		TreeNode temp = p;
		p.left = new TreeNode(9);
		p.right = new TreeNode(20);
		temp = p.right;
		temp.left = new TreeNode(15);
		temp.right = new TreeNode(7);
		
		Number105 n = new Number105();
		int[] preorder = {3,9,20,15,7};
		int[] inorder = {9,3,15,20,7};
		n.buildTree(preorder, inorder);
	}
}
