/**
 * @author minha
 * 2021. 10. 21.
 * 1267. [S/W 문제해결 응용] 10일차 - 작업순서
 * 인접행렬 + BFS
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class WorkFlow2 {
	static int[][] adjArr;
	static LinkedList<Integer>[] parentList;
	static ArrayList<Integer> headList;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			adjArr = new int[V][V];
			visited = new boolean[V];
			parentList = new LinkedList[V];
			headList = new ArrayList<>();
			
			for(int i = 0; i < V; i++) 
				parentList[i] = new LinkedList<>();

			st = new StringTokenizer(br.readLine());

			for(int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				adjArr[a][b] = 1;
				parentList[b].add(a);
			}
			
			for(int i = 0; i < V; i++)
				if(parentList[i].size() == 0)
					headList.add(i);
			
			sb.append("#" + test_case + " ");
			
			for(int i = 0; i < headList.size(); i++)
				bfs(headList.get(i));

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		visited[v] = true;

		while (!queue.isEmpty()) {
			v = queue.poll();
			
			sb.append((v+1) + " ");

			for (int i = 0; i < adjArr.length; i++) {
				if (adjArr[v][i] == 1 && !visited[i]) {
					boolean flag = true;
					for(int j = 0; j < parentList[i].size(); j++) 
						if(!visited[parentList[i].get(j)])
							flag = false;
					
					if(flag) {
						queue.add(i);
						visited[i] = true;
					}
				}
			}
		}
	}
	
}