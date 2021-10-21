/**
 * @author minha
 * 2021. 10. 21.
 * 1267. [S/W 문제해결 응용] 10일차 - 작업순서
 * 인접리스트 + DFS 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class WorkFlow3 {
	static LinkedList<Integer>[] adjList;
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

			adjList = new LinkedList[V];
			parentList = new LinkedList[V];
			headList = new ArrayList<>();
			visited = new boolean[V];
			
			for(int i = 0; i < V; i++) {
				adjList[i] = new LinkedList<>();
				parentList[i] = new LinkedList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				adjList[a].add(b);
				parentList[b].add(a);
			}

			for(int i = 0; i < V; i++)
				if(parentList[i].size() == 0)
					headList.add(i);

			sb.append("#" + test_case + " ");

			for(int i = 0; i < headList.size(); i++)
				dfs(headList.get(i));

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	static void dfs(int v) {
		visited[v] = true;

		sb.append((v+1) + " ");

		for(int i = 0; i < adjList[v].size(); i++) {
			int next = adjList[v].get(i);
			if(!visited[next]) {
				boolean flag = true;
				for(int j = 0; j < parentList[next].size(); j++) {
					if(!visited[parentList[next].get(j)]) {
						flag = false;
					}
				}

				if(flag) {
					dfs(next);
					visited[next] = true;
				}
			}
		}
	}
	
}