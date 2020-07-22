import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。

示例：

输入：
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},
{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":
[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},
{"$ref":"4"}],"val":1}

解释：
节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
节点 4 的值是 4，它有两个邻居：节点 1 和 3 。

提示：
节点数介于 1 到 100 之间。
无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
必须将给定节点的拷贝作为对克隆图的引用返回。
*/

public class Number133 {
    public Node133 cloneGraph(Node133 node) {
    	//key为现节点，value为复制节点
        Map<Node133, Node133> map = new HashMap<>();
        return dfs(node, map);
    }
    
    public Node133 dfs(Node133 node, Map<Node133, Node133> map) {
    	if(node == null) {
    		return null;
    	}
    	if(map.containsKey(node)) {
    		return map.get(node);
    	}
    	
    	Node133 clone = new Node133(node.val, new ArrayList<Node133>());
    	map.put(node, clone);
    	
    	for(Node133 n : node.neighbors) {
    		clone.neighbors.add(dfs(n, map));
    	}
    	return clone;
    }
    
	public static void main(String[] args) {
		Node133 node = new Node133();
		node.val = 1;
		Node133 node1 = new Node133();
		node1.val = 2;
		Node133 node2 = new Node133();
		node2.val = 4;
		Node133 node3 = new Node133();
		node3.val = 3;
		List<Node133> neighbors = new ArrayList<>();
		neighbors.add(node1);
		neighbors.add(node2);
		node.neighbors = neighbors;
		neighbors = new ArrayList<>();
		neighbors.add(node);
		neighbors.add(node3);
		node1.neighbors = neighbors;
		neighbors = new ArrayList<>();
		neighbors.add(node);
		neighbors.add(node3);
		node2.neighbors = neighbors;
		neighbors = new ArrayList<>();
		neighbors.add(node1);
		neighbors.add(node2);
		node3.neighbors = neighbors;
		 
		Number133 n = new Number133();
		n.cloneGraph(node);
	}
}
