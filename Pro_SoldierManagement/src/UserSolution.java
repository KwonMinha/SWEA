import java.util.HashMap;

class UserSolution
{
	LinkedList[][] team;
	HashMap<Integer, Node> map;

	public void init()
	{
		team = new LinkedList[6][6];
		map = new HashMap<>();

		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				team[i][j] = new LinkedList();
			}
		}
	}

	public void hire(int mID, int mTeam, int mScore)
	{
		Node addNode = new Node(new Soldier(mID, mTeam));
		team[mTeam][mScore].add(addNode);
		map.put(mID, addNode);
	}

	public void fire(int mID)
	{
		Node removeNode = map.get(mID);
		removeNode.prev.next = removeNode.next;
		removeNode.next.prev = removeNode.prev;
		map.remove(mID);
	}

	public void updateSoldier(int mID, int mScore)
	{
		Node updateNode = map.get(mID);
		fire(mID);
		hire(mID, updateNode.data.team, mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		if(mChangeScore == 0) {
			return;
		}

		if(mChangeScore > 0) {
			for(int i = 4; i > 0; --i) {
				if(!team[mTeam][i].isEmpty()) {
					int score = i + mChangeScore > 5 ? 5 : i + mChangeScore;
					//if(score != i) { // 5~0
					team[mTeam][score].attachList(team[mTeam][i]);
					team[mTeam][i] = new LinkedList();
					//}

				}
			}
		} else {
			for(int i = 2; i < 6; ++i) {
				if(!team[mTeam][i].isEmpty()) {
					int score = i + mChangeScore < 1 ? 1 : i + mChangeScore;
					//if(score != i) { // 1~6
					team[mTeam][score].attachList(team[mTeam][i]);
					team[mTeam][i] = new LinkedList();
					//}
				}
			}
		}
	}

	public int bestSoldier(int mTeam)
	{
		for(int i = 5; i > 0; i--) {
			if(!team[mTeam][i].isEmpty()) {
				Node node = team[mTeam][i].head.next;
				int max = 0;

				while(node.next != null) {
					if(max < node.data.id) {
						max = node.data.id;
					}

					node = node.next;
				}
				return max;
			}
		}

		return 0;
	}
}

class Soldier {
	int id;
	int team;

	Soldier(int id, int team) {
		this.id = id;
		this.team = team;
	}
}

class Node {
	Soldier data;
	Node prev;
	Node next;

	Node(Soldier data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}

class LinkedList {
	Node head, tail;

	LinkedList() {
		head = new Node(new Soldier(0, 0));
		tail = new Node(new Soldier(0, 0));

		head.next = tail;
		tail.prev = head;
	}

	void add(Node node) {
		node.next = tail;
		node.prev = tail.prev;

		tail.prev.next = node;
		tail.prev = node;
	}

	void remove(Node node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
	}

	boolean isEmpty() {
		if(head.next == tail) {
			return true;
		}
		return false;
	}

	void attachList(LinkedList list) {
		Node last = tail.prev; // 현재 마지막 노드 

		last.next = list.head.next; // 리스트의 첫 노드를 현재의 마지막 노드에 붙임 
		list.head.next.prev = last;

		list.tail.prev.next = tail; // 리스트의 마지막 노드가 현재의 마지막 노드가 됨 
		tail.prev = list.tail.prev;
	}
}