import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class UserSolution {
	
	class Post {
		int uID;
		int pID;
		int like;
		int timestamp;
		int remainTS;
		
		Post(int uID, int pID, int like, int timestamp, int remainTS) {
			this.uID = uID;
			this.pID = pID;
			this.like = like;
			this.timestamp = timestamp;
			this.remainTS = remainTS;
		}
	}
	
	static ArrayList<Post> allPostList;
	static ArrayList<Integer>[] followList;
	static ArrayList<Post>[] postList;
	
	public void init(int N)
	{
		allPostList = new ArrayList<>();
		followList = new ArrayList[1005];
		postList = new ArrayList[1005];
		
		for(int i = 0; i < 1005; i++) {
			followList[i] = new ArrayList<>();
			postList[i] = new ArrayList<>();
		}
	}

	public void follow(int uID1, int uID2, int timestamp)
	{
		followList[uID1].add(uID2);
	}

	public void makePost(int uID, int pID, int timestamp)
	{
		Post post = new Post(uID, pID, 0, timestamp, 0);
		postList[uID].add(post);
		allPostList.add(post);
	}

	public void like(int pID, int timestamp)
	{
		allPostList.get(pID-1).like += 1;
	}

	public void getFeed(int uID, int timestamp, int pIDList[])
	{
		ArrayList<Post> underFeedList = new ArrayList<>();
		ArrayList<Post> overFeedList = new ArrayList<>();
		
		// 사용자 + 사용자의 팔로워 아이디 idList에 추가 
		ArrayList<Integer> idList = new ArrayList<>();
		idList.add(uID);
		for(int id : followList[uID]) {
			idList.add(id);
		}
		
		// idList에 저장된 아이디의 게시물을 모두 feedList에 저장 
		for(int i = 0; i < idList.size(); i++) {
			int id = idList.get(i);
			
			for(int j = 0; j < postList[id].size(); j++) {
				Post post = postList[id].get(j);
				int remainTs = timestamp - post.timestamp;
				post.remainTS = timestamp - post.timestamp;
				
				if(remainTs <= 1000) {
					underFeedList.add(post);
				} else {
					overFeedList.add(post);
				}
			}
		}
		
		// 1000초 이내 게시물 정렬 - like 기준 오름차순 -> timestamp 기준 오름차순 
		Collections.sort(underFeedList, new Comparator<Post>() {
			@Override
			public int compare(UserSolution.Post o1, UserSolution.Post o2) {
				if(o1.like > o2.like) {
					return -1;
				} else if (o1.like == o2.like) {
					if(o1.timestamp > o2.timestamp) {
						return -1;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			}
		});
		
		// 1000초 초과 게시물 정렬 - timestamp 기준 오름차순 
		Collections.sort(overFeedList, new Comparator<Post>() {
			@Override
			public int compare(UserSolution.Post o1, UserSolution.Post o2) {
				if(o1.timestamp > o2.timestamp) {
					return -1;
				} else if(o1.timestamp < o2.timestamp) {
					return 1;
				}
				return 0;
			}
		});
		
		Arrays.fill(pIDList, 0);
		
		ArrayList<Integer> resultList = new ArrayList<>();
		int index = 0;
		while(resultList.size() < 10 && index < underFeedList.size()) {
			resultList.add(underFeedList.get(index).pID);
			index++;
		}
		
		index = 0;
		while(resultList.size() < 10 && index < overFeedList.size()) {
			resultList.add(overFeedList.get(index).pID);
			index++;
		}
		
		for(int i = 0; i < resultList.size(); i++) {
			pIDList[i] = resultList.get(i);
		}
		
	}
}