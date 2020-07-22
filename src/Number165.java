/*
比较两个版本号 version1 和 version2。
如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
你可以假设版本字符串非空，并且只包含数字和 . 字符。
 . 字符不代表小数点，而是用于分隔数字序列。
例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 
示例 1:
输入: version1 = "0.1", version2 = "1.1"
输出: -1

示例 2:
输入: version1 = "1.0.1", version2 = "1"
输出: 1

示例 3:
输入: version1 = "7.5.2.4", version2 = "7.5.3"
输出: -1

示例 4：
输入：version1 = "1.01", version2 = "1.001"
输出：0
解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。

示例 5：
输入：version1 = "1.0", version2 = "1.0.0"
输出：0
解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
 
提示：
版本字符串由以点 （.） 分隔的数字字符串组成。这个数字字符串可能有前导零。
版本字符串不以点开始或结束，并且其中不会有两个连续的点。
*/

public class Number165 {
    public int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0;
        while(i < nums1.length || j < nums2.length) {
            String num1 = i < nums1.length ? nums1[i] : "0";
            String num2 = j < nums2.length ? nums2[j] : "0";
            int res = compare(num1, num2);
            if(res == 0) {
                i++;
                j++;
            }else {
                return res;
            }
        }
        return 0;
    }
    
    private int compare(String num1, String num2) {
        //将高位的 0 去掉
        num1 = removeFrontZero(num1);
        num2 = removeFrontZero(num2);
        //先根据长度进行判断
        if(num1.length() > num2.length()) {
            return 1;
        }else if (num1.length() < num2.length()) {
            return -1;
        }else {
            //长度相等的时候
            for(int i = 0; i < num1.length(); i++) {
                if(num1.charAt(i) - num2.charAt(i) > 0) {
                    return 1;
                } else if (num1.charAt(i) - num2.charAt(i) < 0) {
                    return -1;
                }
            }
            return 0;
        }
    }

    private String removeFrontZero(String num) {
        int start = 0;
        for(int i = 0; i < num.length(); i++) {
            if(num.charAt(i) == '0') {
                start++;
            }else {
                break;
            }
        }
        return num.substring(start);
    }
    
	public static void main(String[] args) {
		String version1 = "1.01", version2 = "1.001";
		
		Number165 n = new Number165();
		n.compareVersion(version1, version2);
	}
}
