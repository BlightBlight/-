/*
给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符

示例 1:
输入: word1 = "horse", word2 = "ros"
输出: 3
解释: 
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

示例 2:
输入: word1 = "intention", word2 = "execution"
输出: 5
解释: 
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
*/

/*
 * dp[i][j]代表 word1到i位置转换成 word2到j位置需要最少步数
 * 当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；
 * 当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
 * 其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
 * 以word1为"horse"，word2为"ros"，且 dp[5][3]为例，即要将 word1的前5个字符转换为word2的前3个字符，也就是将horse转换为ros，因此有：
 * dp[i-1][j-1]，即先将word1的4个字符转换为word2的前2个字符 ，然后将第五个字符word1[4]由e替换为s，即替换操作
 * dp[i][j-1]，即先将word1的前5个字符转换为word2的前2个字符，然后在末尾补充一个 s，即插入操作
 * dp[i-1][j]，即先将word1的前4个字符转换为word2的前3个字符，然后删除word1的第5个字符，即删除操作
 * 注意，针对第一行，第一列要单独考虑，我们引入 '' 下图(question_72.png)所示：
 * 第一行，是 word1 为空变成 word2 最少步数，就是插入操作
 * 第一列，是 word2 为空，需要的最少步数，就是删除操作
 * 
 * 
 * 从零开始推断思路
 * 设word1，word2为空，则不用任何操作
 * (1)设word1为空，word2不为空，则最优操作为word1插入n次成为word2
 * (2)设word1不为空，word2为空，则最优操作为word1删除n次成为word2
 * 设word1，word2都不为空，word1=a，word2=a依次递增到abcdefg等等
 * 若word1==word2，则最优，不用操作
 * word1！=word2，则分别进行替换、删除、插入，选择所需代价最小
 * 规定替换、删除、插入必须在最后一位操作，即a替换成abc为把a替换成c，a前面转换为ab
 * 设现在为第三轮，word1=a，word2=abc
 * 替换操作为把word1的a替换成word2的c，把word1中a前的字符转换成word2中c前的字符，a前为空，空转换成ab，(1)已经算过
 * 删除操作为把word1的a删除，把word1的a前的字符转换成word2的字符，则空转换成ab，(1)已经算过
 * 插入操作为在word1后插入一个c，把word1插入的c前的字符转换成word2的c以前的字符，即把a转换成ab，上一轮就是a转换成ab，已经算过
 * word1=ab，word2=a依次递增到abcdefg和上面思路类似
 * 设现在为第一轮，word1=ab，word2=abc
 * 替换操作为把word1的b替换成word2的a，把word1中b前的字符转换成word2中c前的字符，a转换成空，(2)算过
 * 删除操作为把word1的b删除，把word1的b前的字符转换成word2的字符，则a转换成a，不操作
 * 插入操作为在word1后插入一个a，把word1插入的a前的字符转换成word2的c以前的字符，即把ab转换成空，(2)算过
 * 由此可以用动态规划做，每一次解都可以由上一轮解得出
 * dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
 * 当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；不操作
 * 当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1，替换删除插入都做一遍，选最小
 */
public class Number72 {
    public int minDistance(String word1, String word2) {
    	int n1 = word1.length();
    	int n2 = word2.length();
        int[][] dp = new int[n1+1][n2+1];
        
        //第一列，是 word2 为空，需要的最少步数，就是删除操作
        for(int i = 1; i < n1 + 1; i++) {
        	dp[i][0] = dp[i-1][0] + 1;
        }
        
        //第一行，是 word1 为空变成 word2 最少步数，就是插入操作
        for(int j = 1; j < n2 + 1; j++) {
        	dp[0][j] = dp[0][j-1] + 1;
        }
        
        for(int i = 1; i < n1 + 1; i++) {
        	for(int j = 1; j < n2 + 1; j++) {
        		//i-1和j-1是因为第一行和第一列是额外加进来的，所以i和j要从1开始
        		if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
        			dp[i][j] = dp[i-1][j-1];
        		}else {
        			dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
        		}
        	}
        }
        return dp[n1][n2];
    }
    
	public static void main(String[] args) {
		String word1 = "horse", word2 = "ros";
		Number72 n = new Number72();
    	System.out.println(n.minDistance(word1, word2));
	}
}
