import java.util.ArrayList;
import java.util.List;

/*
给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

示例:
输入: 3
输出:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]

解释:
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

public class Number95 {
	//递归写法
	/*List<TreeNode> ans = new ArrayList<>();
    public List<TreeNode> generateTrees(int n) {
    	return getAns(1, n);
    }
    
    //递归
    public List<TreeNode> getAns(int start, int end) {
    	List<TreeNode> ans = new ArrayList<TreeNode>();
        //此时没有数字，将 null 加入结果中
        if(start > end) {
            ans.add(null);
            return ans;
        }
        //只有一个数字，当前数字作为一棵树加入结果中
        if(start == end) {
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }
        
        //根节点为i
    	for(int i = start; i <= end; i++) {
    		//得到(0, i-1)所有可能的左子树
            List<TreeNode> leftTrees = getAns(start, i - 1);
            //得到(i+1, n)所有可能的右子树
            List<TreeNode> rightTrees = getAns(i + 1, end);
            //左子树右子树两两组合
            for(TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
    	}
    	return ans;
	}*/
    
	/*
	 * 动态规划写法
	 * 首先我们每次新增加的数字大于之前的所有数字
	 * 所以新增加的数字出现的位置只可能是根节点或者是根节点的右孩子，右孩子的右孩子，右孩子的右孩子的右孩子等等，总之一定是右边。
	 * 其次，新数字所在位置原来的子树，改为当前插入数字的左孩子即可，因为插入数字是最大的。
	 * 对于下边的解 
			  2
			 /
			1
		然后增加 3
		1.把3放到根节点
			    3
			   /
			  2
			 /
			1
		2.把3放到根节点的右孩子
			   2
			  / \
			 1   3
		对于下边的解
			 1
			  \
			   2
		然后增加 3
		1.把3放到根节点
			     3
			    /
			   1
			    \
			     2
		2.把3放到根节点的右孩子，原来的子树作为 3 的左孩子       
			      1
			        \
			         3
			        /
			      2
		3.把3放到根节点的右孩子的右孩子
			  1
			    \
			     2
			      \
			       3
	* 以上就是根据[1,2]推出[1,2,3]的所有过程，可以写代码了。
	* 由于求当前的所有解只需要上一次的解，所有我们只需要两个list，pre保存上一次的所有解，cur计算当前的所有解。
	 */
	
	public List<TreeNode> generateTrees(int n) {
		//上一次的所有解
	    List<TreeNode> pre = new ArrayList<TreeNode>();
	    if(n == 0) {
	        return pre;
	    }
	    
	    pre.add(null);
	    //每次增加一个数字
	    for(int i = 1; i <= n; i++) {
	        List<TreeNode> cur = new ArrayList<TreeNode>();
	        //遍历之前的所有解
	        for(TreeNode root : pre) {
	            //插入到根节点
	            TreeNode insert = new TreeNode(i);
	            insert.left = root;
	            cur.add(insert);
	            /*
	             * 插入到右孩子
	             * 右孩子的右孩子
	             * 右孩子的右孩子的右孩子...
	             * 最多插入n次
	             */
	            for(int j = 0; j < n; j++) {
	                TreeNode root_copy = treeCopy(root); //复制当前的树
	                TreeNode parentNode = root_copy; //要插入节点的父节点
	                
	                /*
	                 * k是指第n个右节点
	                 * 最多遍历j次找当前右孩子的父节点
	                 */
	                for(int k = 0; k < j - 1; k++) {
	                    if(parentNode == null) {
	                        break;
	                    }
	                    parentNode = parentNode.right;
	                }
	                //到达null提前结束
	                if(parentNode == null) {
	                    break;
	                }
	                //保存父节点的右孩子作为插入节点的左孩子
	                TreeNode rightTree = parentNode.right;
	                insert = new TreeNode(i);
	                insert.left = rightTree; //插入节点的左孩子更新为插入位置之前的子树
	                parentNode.right = insert; //父节点的右孩子是插入节点
	                //加入结果中
	                cur.add(root_copy);
	            }
	        }
	        pre = cur;
	    }
	    return pre;
	}


	private TreeNode treeCopy(TreeNode root) {
	    if (root == null) {
	        return root;
	    }
	    TreeNode newRoot = new TreeNode(root.val);
	    newRoot.left = treeCopy(root.left);
	    newRoot.right = treeCopy(root.right);
	    return newRoot;
	}
	
	public static void main(String[] args) {
		int n1 = 3;
		Number95 n = new Number95();
		n.generateTrees(n1);
	}
}
