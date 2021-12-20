import java.util.ArrayList;
import java.util.HashMap;

class UserSolution {
	
	class Node {
		int id;
		int size;
		int pos;
		
		Node(int id, int size, int pos) {
			this.id = id;
			this.size = size;
			this.pos = pos;
		}
	}
	
	HashMap<Integer, Node> nodeMap;
	ArrayList<Node> cacheList;
	ArrayList<Node> lruList;
	ArrayList<Node> cacheFreeList;
	
	int cacheSize;
	int cacheUsage;
	
	public void init(int N) {
		cacheSize = N;
		cacheUsage = 0;
		
		nodeMap  = new HashMap<>();
		cacheList = new ArrayList<>();
		lruList = new ArrayList<>();
		cacheFreeList = new ArrayList<>();
		
		Node node = new Node(-1, N, 0);
		cacheList.add(node);
		cacheFreeList.add(node);
		
		return;
	}

	public int access(int fileId, int fileSize) {
		Node currentNode = nodeMap.get(fileId);
		
		// 해당 파일이 캐시 내에 존재할 경우, LRU에서 마지막으로 접근된 시간 정보 변경 -> 맨 뒤로 보냄 
		if(currentNode != null) {
			lruList.remove(currentNode);
			lruList.add(currentNode);
		} else {
			Node freeNode = null;
			
			// 캐시에 파일을 저장할 공간 찾기  
			for(Node n : cacheFreeList) {
				if(n.size >= fileSize && (freeNode == null || n.pos < freeNode.pos)) {
					freeNode = n;
				}
			}
			
			// 빈 공간이 있는 경우 해당 공간에 넣기위해 freeSet에서 꺼냄  
			if(freeNode != null) {
				cacheFreeList.remove(freeNode);
			}
			
			currentNode = freeNode;
		
			// 빈 공간이 없는 경우 -> LRU에서 파일을 삭제하고 합쳐야 함
			while(currentNode == null) {
				Node deleteNode = lruList.get(0);
				cacheUsage -= deleteNode.size;
				lruList.remove(0);
				nodeMap.remove(deleteNode.id);
				
				int index = cacheList.indexOf(deleteNode);
				deleteNode.id = -1;
				
				if(index < cacheList.size() - 1) { // 오른쪽 정리 
					Node nextDeleteNode = cacheList.get(index + 1);
					
					if(nextDeleteNode.id == -1) {
						deleteNode.size += nextDeleteNode.size;
						cacheList.remove(index + 1); // 
						cacheFreeList.remove(nextDeleteNode);
					}
				}
				
				if(index > 0) { // 왼쪽 정리 
					Node preDeleteNode = cacheList.get(index - 1);
					
					if(preDeleteNode.id == -1) {
						deleteNode.size += preDeleteNode.size;
						deleteNode.pos = preDeleteNode.pos;
						cacheList.remove(index - 1);
						cacheFreeList.remove(preDeleteNode); 
					}
				}
				
				if(deleteNode.size >= fileSize) {
					currentNode = deleteNode;
				} else {
					cacheFreeList.add(deleteNode);
				}
			}
			
			// 새로운 노드가 들어갈 공간을 찾았고 삽입 
			Node newNode = new Node(fileId, fileSize, currentNode.pos);
			lruList.add(newNode);
			
			int insertIndex = cacheList.indexOf(currentNode);
			cacheList.add(insertIndex, newNode);
			
			currentNode.size -= fileSize;
			currentNode.pos += fileSize;
			
			if(currentNode.size == 0) { // 새로운 노드에 공간 다 주고 남은 공간이 없다면 삭제 
				cacheList.remove(currentNode);
			} else { // 남은 공간은 다시 FreeList에 추가 
				cacheFreeList.add(currentNode);
			}
			
			nodeMap.put(fileId, newNode);
			currentNode = newNode;
			
			cacheUsage += fileSize;
		}
		
		return currentNode.pos;
	}

	public int usage() {
		return cacheUsage;
	}
}