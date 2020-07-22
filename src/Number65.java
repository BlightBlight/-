/*
验证给定的字符串是否可以解释为十进制数字。

例如:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：

数字 0-9
指数 - "e"
正/负号 - "+"/"-"
小数点 - "."
当然，在输入中，这些字符的上下文也很重要。

"  -90.231e-23"
*/


/*
 * 暴力枚举可以做
 * 好思路是用状态机做，具体看图就懂
 */
public class Number65 {
	public int make(char c) {
        switch(c) {
            case ' ': return 0;
            case '+':
            case '-': return 1;
            case '.': return 3;
            case 'e': return 4;
            default:
                if(c >= 48 && c <= 57) return 2;
        }
        return -1;
    }
    
    public boolean isNumber(String s) {
        int state = 0;
        //表示以什么状态结尾，合法为1，不合法为0
        int finals = 0b101101000;
        /*
         * .1居然也是合法的。。。
        	state	blank	+/-		0-9		.		e		other
			0		 0		 1		6		 2		-1		-1
			1		-1		-1		6		 2		-1		-1
			2		-1		-1		3		-1		-1		-1
			3		 8		-1		3		-1		 4		-1
			4		-1		 7		5		-1		-1		-1
			5		 8		-1		5		-1		-1		-1
			6		 8		-1		6		 3		 4		-1
			7		-1		-1		5		-1		 1		-1
			8		8		-1		-1		-1		-1		-1
         */
        int[][] transfer = new int[][]{{ 0, 1, 6, 2,-1},
                                       {-1,-1, 6, 2,-1},
                                       {-1,-1, 3,-1,-1},
                                       { 8,-1, 3,-1, 4},
                                       {-1, 7, 5,-1,-1},
                                       { 8,-1, 5,-1,-1},
                                       { 8,-1, 6, 3, 4},
                                       {-1,-1, 5,-1,-1},
                                       { 8,-1,-1,-1,-1}};
        char[] ss = s.toCharArray();
        for(int i = 0; i < ss.length; i++) {
            int id = make(ss[i]);
            if(id < 0) {
            	return false;
            }
            state = transfer[state][id];
            if(state < 0) {
            	return false;
            }
        }
        return (finals & (1 << state)) > 0;
    }
    
	public static void main(String[] args) {
		String s = ".";
		Number65 n = new Number65();
    	System.out.println(n.isNumber(s));
    } 
}
