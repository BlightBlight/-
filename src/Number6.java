import java.util.ArrayList;
import java.util.List;

/*
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
L   C   I   R
E T O E S I I G
E   D   H   N

之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
请你实现这个将字符串进行指定行数变换的函数：
string convert(string s, int numRows);

示例 1:
输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"

示例 2:
输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:
L     D     R
E   O E   I I
E C   I H   N
T     S     G
*/

/*
 * 很秀的写法:通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行
 * 例如:第一个字符，第一行，第二个字符，第二行等，当达到最后一行时，改变方向，当前行数递减直到第一行
 */
public class Number6 {
	public String convert(String s, int numRows) {
		if(numRows == 1) {
			return s;
		}
		
        List<StringBuilder> rows = new ArrayList<>();//这个集合每个元素就是每行
        for(int i = 0; i < Math.min(s.length(), numRows); i++) {
        	rows.add(new StringBuilder());
        }
        
        int curRow = 0;//当前行数
        boolean goingDown = false;//行走方向
        
        for(char c : s.toCharArray()) {
        	rows.get(curRow).append(c);//当前行增加字符
        	if(curRow == 0 || curRow == numRows - 1) { 
        		//第一行和最后一行时换方向
        		goingDown = !goingDown;
        	}
        	curRow += (goingDown == true? 1 : -1);
        }
        StringBuilder answer = new StringBuilder();
        for(StringBuilder temp : rows) {
        	answer.append(temp);
        }
        return answer.toString();
    }
	
	public static void main(String[] args) {
		String s = "LEETCODEISHIRING";
		Number6 n = new Number6();
		System.out.println(n.convert(s, 3));
	}
}
