import java.util.LinkedList;
import java.util.List;

/*
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:
输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

public class Number77 {
	/*
	 * 回溯法
	 * 剪枝优化为(图77)
	 * 当选定了一个元素，即 curr.size() == 1 的时候，接下来要选择 2 个元素， i 最大的值是 4 ，因为从 5 开始选择，就无解了；
	 * 当选定了两个元素，即 curr.size() == 2 的时候，接下来要选择 1 个元素， i 最大的值是 5 ，因为从 6 开始选择，就无解了。
	 * max(i) + 接下来要选择的元素个数 - 1 = n，其中， 接下来要选择的元素个数 = k - curr.size()，整理得到：
	 * max(i) = n - (k - curr.size()) + 1
	 * i <= n - (k - curr.size()) + 1
	 */
	List<List<Integer>> output = new LinkedList<>();
	int n;
	int k;

	public void backtrack(int first, LinkedList<Integer> curr) {
		if(curr.size() == k) {
	    	output.add(new LinkedList<Integer>(curr));
	    	return;
	    }
    
	    for(int i = first; i <= n - (k - curr.size()) + 1; i++) {
	    	curr.add(i);
	    	backtrack(i + 1, curr);
	    	curr.removeLast();
	    }
	}

	public List<List<Integer>> combine(int n, int k) {
		this.n = n;
	    this.k = k;
	    backtrack(1, new LinkedList<Integer>());
	    return output;
	}
	
	  /*
	   * 将nums初始化为从1到k的整数序列。 将n+1添加为末尾元素，起到“哨兵”的作用。
	   * 将指针设为列表的开头j = 0.
	   * While j < k :
	   * 将nums中的前k个元素添加到输出中，换而言之，除了“哨兵”之外的全部元素。
	   * 找到nums中的第一个满足nums[j] + 1 != nums[j+1]的元素，并将其加一
	   * nums[j]++以转到下一个组合。
	   */
/*	
 * public List<List<Integer>> combine(int n, int k) {
		// init first combination
		LinkedList<Integer> nums = new LinkedList<Integer>();
		for(int i = 1; i < k + 1; ++i) {
			nums.add(i);
		}
		
		nums.add(n + 1);

		List<List<Integer>> output = new ArrayList<List<Integer>>();
		int j = 0;
		while (j < k) {
			output.add(new LinkedList<Integer>(nums.subList(0, k)));
			j = 0;
			while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1)) {
				nums.set(j, j++ + 1);
			}
			nums.set(j, nums.get(j) + 1);
		}
		return output;
	}*/
    
	public static void main(String[] args) {
		int n1 = 4, k = 2;
		Number77 n = new Number77();
    	List<List<Integer>> ans = n.combine(n1, k);
    	for(int i = 0; i < ans.size(); i++) {
    		System.out.println(ans.get(i));
    	}
	}
}
