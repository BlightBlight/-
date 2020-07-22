import java.util.Stack;

/*
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。

示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
*/

public class Number155 {
	public class MinStack {
		// 数据栈
		private Stack<Integer> data;
		// 辅助栈
		private Stack<Integer> helper;

		public MinStack() {
			data = new Stack<>();
			helper = new Stack<>();
		}

		public void push(int x) {
			// 数据栈和辅助栈一定会增加元素
			data.add(x);
			if(helper.isEmpty() || helper.peek() >= x) {
				helper.add(x);
			}else {
				helper.add(helper.peek());
			}
		}
		
	    public void pop() {
	        // 两个栈都得 pop
	        if(!data.isEmpty()) {
	            helper.pop();
	            data.pop();
	        }
	    }

	    public int top() {
	        if(!data.isEmpty()){
	            return data.peek();
	        }
	        throw new RuntimeException("栈中元素为空，此操作非法");
	    }

	    public int getMin() {
	        if(!helper.isEmpty()){
	            return helper.peek();
	        }
	        throw new RuntimeException("栈中元素为空，此操作非法");
	    }
	}
	
	public static void main(String[] args) {
		Number155.MinStack minStack = new Number155().new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		minStack.getMin();
		minStack.pop();
		minStack.top();
		minStack.getMin();

	}
}
