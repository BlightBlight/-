import java.util.HashMap;
import java.util.Map;

/*
根据一棵树的中序遍历与后序遍历构造二叉树。
注意:
你可以假设树中没有重复的元素。
例如，给出
中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：
    3
   / \
  9  20
    /  \
   15   7
*/

public class Number106 {
	int[] postorder;
	private Map<Integer, Integer> hash;
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLen = inorder.length;
        this.postorder = postorder;
        this.hash = new HashMap<>();
        for(int i = 0; i < inLen; i++) {
            hash.put(inorder[i], i);
        }
        
        return backtrack(0, inLen - 1, 0, inLen - 1);
        
    }
    
    public TreeNode backtrack(int postLeft, int postRight, int inLeft, int inRight) {
    	//因为是递归调用的方法，按照国际惯例，先写递归终止条件
        if(inLeft > inRight || postLeft > postRight) {
            return null;
        }
        
        int pivot = postorder[postRight];
        int pivotIndex = hash.get(pivot);
        TreeNode root = new TreeNode(pivot);
        /*
         * 看图106理解如何计算边界，或者自己画个图吧
         * 中序遍历数组和后序遍历数组的长度是一样的
         *  (4  2   5)   1   (6    3   7)
         *   iL   pI-1   pI  pI+1      iR
         *  (4  5  2)  (6   7   3)   1
         *   pL   x-1   x      pR-1  pR
         *  以1为中点，左子树数量有3个，右子树数量也有3个，则后序遍历数组传入的左子树和右子树的数组数量应该都为3
         *  左子树后序遍历数组的左边界为pL，右边界为pI
         *  右子树后序遍历数组的左边界为x，右边界pR-1
         *  iR - (pI+1) = (pR-1) - x
         *  x = pR - iR + pI
         *  x - 1 = pr - iR + pI - 1，由于pR和iR相同，所以可以约去
         */
        root.left = backtrack(postLeft, pivotIndex - 1, inLeft, pivotIndex - 1);
        root.right = backtrack(postRight - inRight + pivotIndex, postRight - 1, pivotIndex + 1, inRight);
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
		
		Number106 n = new Number106();
		int[] inorder = {9,3,15,20,7};
		int[] postorder = {9,15,7,20,3};
		n.buildTree(inorder, postorder);
	}
}
