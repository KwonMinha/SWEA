import java.util.HashMap;
import java.util.LinkedList;

class UserSolution {

	class Node {
		int id;
		Node parent;
		int type; // 0 : 디렉터리 / 1 : 파일 
		int size; // 파일은 자기 자신의 크기를 가지고, 디렉터리는 가지고 있는 파일들의 크기를 가질 것 
		int fileNum; // 파일은 자기 하나만 있으니 1일 것이고, 디렉터리는 가진 파일 개수만큼 가질 것 
		int infectSize = 0; // 감염되며 증가된 사이즈를 저장 
		LinkedList<Node> childList = new LinkedList<>();

		Node(int id, Node parent, int size) {
			this.id = id;
			this.parent = parent;
			this.size = size;
			if(size == 0) {
				this.type = 0;
				this.fileNum = 0;
			} else {
				this.type = 1;
				this.fileNum = 1;
			}
		}

		void addChild(Node node) {
			childList.add(node);
		}

		void removeChild(Node node) {
			childList.remove(node);
		}
	}

	HashMap<Integer, Node> nodeMap;

	public void init() {
		nodeMap = new HashMap<>();	
		nodeMap.put(10000, new Node(10000, null, 0)); // 루트 노드 추가 
	}

	public int cmdAdd(int newID, int pID, int fileSize) {
		// 부모 노드에 새로운 노드 만들어서 붙여줌 
		Node parent = nodeMap.get(pID);
		Node addNode = new Node(newID, parent, fileSize);
		nodeMap.put(newID, addNode);
		parent.addChild(addNode);

		if(addNode.type == 1) { // 파일인 경우만 사이즈 및 파일 개수 업데이트 
			addUpdate(addNode);
		}

		return parent.size;
	}

	public int cmdMove(int tID, int pID) {
		// 기존에 연결된 부모 노드와 연결 해제 
		Node moveNode = nodeMap.get(tID);
		moveNode.parent.removeChild(moveNode);
		removeUpdate(moveNode);
		
		// 새로운 부모 노드에 연결 
		Node parent = nodeMap.get(pID);
		parent.addChild(moveNode);
		moveNode.parent = parent;
		addUpdate(moveNode);
		
		return parent.size;
	}

	public int cmdInfect(int tID) {
		if(nodeMap.get(10000).fileNum == 0) { // 총 파일 개수 0인 경우 감염 X
			return 0;
		}
		
		Node infectNode = nodeMap.get(tID);
		int increaseSize = nodeMap.get(10000).size / nodeMap.get(10000).fileNum; // 증가 크기 
		infect(infectNode, increaseSize);

		return infectNode.size;
	}
	
	public void infect(Node node, int increaseSize) {
		if(node.type == 0) { // 디렉터리일 경우 
			for(int i = 0; i < node.childList.size(); i++) {
				infect(node.childList.get(i), increaseSize); // 파일들을 찾아서 감염시켜줌 
			}
		} else { // 파일일 경우 
			node.size += increaseSize;
			node.infectSize += increaseSize;
			infectUpdate(node, increaseSize);
		}
	}
	
	public int cmdRecover(int tID) {
		Node recoverNode = nodeMap.get(tID);
		recover(recoverNode);

		return recoverNode.size;
	}
	
	public void recover(Node node) {
		if(node.type == 0) { // 디렉터리일 경우 
			for(int i = 0; i < node.childList.size(); i++) {
				recover(node.childList.get(i)); // 파일들을 찾아서 회복시켜줌 
			}
		} else { // 파일일 경우 
			node.size -= node.infectSize;
			recoverUpdate(node, node.infectSize);
			node.infectSize = 0;
		}
	}

	public int cmdRemove(int tID) {
		if(tID == 10000) { // 루트 노드 인경우 초기화 
			init();
			return 0;
		}
		
		// 부모 노드와 연결 끊고 노드 삭제 
		Node removeNode = nodeMap.get(tID);
		removeNode.parent.removeChild(removeNode);
		removeUpdate(removeNode);
		
		return removeNode.size;
	}
	
	// Update 메서드들은 삽입 / 삭제 / 감염 / 회복을 하며 변경된 노드들의 파일 사이즈 및 개수를 업데이트 
	
	public void addUpdate(Node node) {
		Node current = node;

		while(current.parent != null) {
			current.parent.size += node.size;
			current.parent.fileNum += node.fileNum;
			current = current.parent;
		}
	}

	public void removeUpdate(Node node) {
		Node current = node;

		while(current.parent != null) {
			current.parent.size -= node.size;
			current.parent.fileNum -= node.fileNum;
			current = current.parent;
		}
	}
	
	public void infectUpdate(Node node, int increaseSize) {
		Node current = node;
		
		while(current.parent != null) {
			current.parent.size += increaseSize;
			current = current.parent;
		}
	}
	
	public void recoverUpdate(Node node, int infectSize) {
		Node current = node;
		
		while(current.parent != null) {
			current.parent.size -= infectSize;
			current = current.parent;
		}
	}
}