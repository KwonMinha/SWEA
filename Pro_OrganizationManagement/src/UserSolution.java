import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class UserSolution
{
	class Node implements Comparable<Node> {
		String name;
		int num;
		int create;
		int depth;
		Node parent;
		LinkedList<Node> childList = new LinkedList<>();

		Node(String name, int num, int create, int depth, Node parent) {
			this.name = name;
			this.num = num;
			this.create = create;
			this.depth = depth;
			this.parent = parent;
		}

		void addChild(Node node) {
			childList.add(node);
		}

		void removeChild(Node node) {
			childList.remove(node);
		}

		@Override
		public int compareTo(UserSolution.Node o) {
			if(this.num == o.num) {
				return o.create - this.create;
			}
			return this.num - o.num;
		}
	}

	int createIdx;
	HashMap<String, Node> nodeMap;

	void init(int mNum) {
		nodeMap = new HashMap<>();
		Node root = new Node("root", mNum, 0, 0, null);
		nodeMap.put("root", root);
		createIdx = 1;
	}

	void destroy() {

	}

	int addDept(char mUpperDept[], char mNewDept[], int mNum) {
		String upperName = String.valueOf(mUpperDept).trim();
		String newName = String.valueOf(mNewDept).trim();

		Node upperNode = nodeMap.get(upperName);
		Node newNode = nodeMap.get(newName);

		// 부서를 새로 만들 수 없음 
		if(upperNode == null || upperNode.depth == 5 || upperNode.childList.size() == 10 || upperNode.num <= mNum || newNode != null) {
			return -1;
		}

		// 새로운 부서 생성 
		newNode = new Node(newName, mNum, createIdx++, upperNode.depth + 1, upperNode);
		nodeMap.put(newName, newNode);
		upperNode.addChild(newNode);

		// 상위 부서에서 직원 옮기기 
		upperNode.num -= mNum;

		// 상위 부서 직원 수 반환 
		return upperNode.num;
	}

	int mergeDept(char mDept1[], char mDept2[]) {
		String dept1Name = String.valueOf(mDept1).trim();
		String dept2Name = String.valueOf(mDept2).trim();

		Node dept1Node = nodeMap.get(dept1Name);
		Node dept2Node = nodeMap.get(dept2Name);

		// 부서가 존재하지 않는 경우 
		if(dept1Node == null || dept2Node == null) {
			return -1;
		}

		// mDept1 부서가 mDept2 부서의 하위 부서인 경우 
		if(isParent(dept1Node, dept2Node)) {
			return -1;
		}

		// 합치고 없앤 후 mDept1의 직속 하위 부서가 10개를 초과하는 경우 
		int total = dept1Node.childList.size() + dept2Node.childList.size();
		if(dept1Node.childList.contains(dept2Node)) {
			total--;
		} 
		if(total > 10) {
			return -1;
		}

		// 합치고 나면 루트의 6차 하위 부서가 생기는 경우 
		if(dept1Node.depth + maxDepth(dept2Node) >= 6) {
			return -1;
		}

		dept1Node.num += dept2Node.num; // 부서1에 부서2 직원 옮겨감 

		updateDepth(dept2Node, dept1Node.depth); // 부서2의 하위 부서 깊이 변경 (부서1 깊이 + 1로 다 만들어줌)

		for(Node child : dept2Node.childList) { 
			child.parent = dept1Node; // 부서2 하위 부서의 부모를 부서1로 변경 
			dept1Node.addChild(child); // 부서1의 하위 부서에 넣어줌 
		}

		dept2Node.parent.removeChild(dept2Node); // 부서2를 부모의 하위 리스트에서 삭제
		nodeMap.remove(dept2Node.name); // 전체 노드 맵에서도 삭제 

		return dept1Node.num;
	}

	public int maxDepth(Node node) {
		int max = 0;

		Queue<Node> queue = new LinkedList<>();
		queue.add(node);

		while(!queue.isEmpty()) {
			Node cur = queue.poll();

			max = Math.max(max, cur.depth);

			for(Node child : cur.childList) {
				queue.add(child);
			}
		}

		return max - node.depth;
	}

	// 부서1이 부서2의 하위인지 판별 
	public boolean isParent(Node node1, Node node2) {
		Node parent = node1.parent;

		while(parent != null) {
			if(parent == node2) { 
				return true;
			} else {
				parent = parent.parent;
			}
		}

		return false;
	}

	// 하위 부서의 깊이 변경 
	public void updateDepth(Node node, int depth) {
		node.depth = depth;

		for(Node child : node.childList) {
			updateDepth(child, depth+1);
		}
	}

	int moveEmployee(char mDept[], int mDepth, int mNum){
		String moveName = String.valueOf(mDept).trim();
		Node moveNode = nodeMap.get(moveName);

		// 부서가 존재하지 않는 경우 
		if(moveNode == null) {
			return -1;
		}

		// 하위 부서의 인원수 판단해 이동 
		for(int i = 0; i < moveNode.childList.size(); i++) {
			move(moveNode,moveNode.childList.get(i), mDepth, mNum);
		}

		return moveNode.num;
	}

	void move(Node moveNode, Node childNode, int depth, int num) {
		if(childNode.num > num) { // 하위 부서의 인원 수가 num명 초과인 경우 
			moveNode.num += num;
			childNode.num -= num;
		} else { // num명 이하여서 옮길 수 없는 경우 -> 1명만 남겨두고 옮김 
			moveNode.num += (childNode.num - 1);
			childNode.num = 1;
		}

		if(depth > 1) { 
			for(int i = 0; i < childNode.childList.size(); i++) {
				move(moveNode, childNode.childList.get(i), depth - 1, num);
			}
		}
	}

	void recruit(int mDeptNum, int mNum){
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for(String key : nodeMap.keySet()) {
			pq.add(nodeMap.get(key));
		}

		for(int i = 0; i < mDeptNum; i++) {
			Node node = pq.poll();
			node.num += mNum;
		}
	}
}