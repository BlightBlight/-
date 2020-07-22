
public class Node138 {
    public int val;
    public Node138 next;
    public Node138 random;

    public Node138() {}
    
    public Node138(int val) {
    	this.val = val;
    }
    
    public Node138(int _val,Node138 _next,Node138 _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}
