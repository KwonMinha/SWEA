/**
 * @author minha
 * 2021. 10. 21.
 * 1248. [S/W 문제해결 응용] 3일차 - 공통조상 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class LCA {
	static LinkedList<Integer>[] adjList;
	public static int[] parent;
	public static int[] depth;
	public static int count;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList = new LinkedList[V+1];
			parent = new int[V+1];
			depth = new int[V+1];
			count = 0;

			for(int i = 1; i <= V; i++)
				adjList[i] = new LinkedList<>();

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				adjList[v1].add(v2);
			}

			dfs(1, 0, -1);

			int depthA = depth[a];
			int depthB = depth[b];

			while(depthA > depthB) {
				a = parent[a];
				depthA--;
			}

			while(depthB > depthA) {
				b = parent[b];
				depthB--;
			}

			while(a != b) {
				a = parent[a];
				b = parent[b];
			}

			getCount(a, parent[a]);

			sb.append("#" + test_case + " " + a + " " + count + "\n");
		}

		System.out.println(sb.toString());
	}

	public static void dfs(int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;

		for(int next : adjList[cur])
			if(next != p)
				dfs(next, d+1, cur);
	}

	public static void getCount(int cur, int p) {
		count++;

		if(!adjList[cur].isEmpty())
			for(int next : adjList[cur])
				if(next != p)
					getCount(next, cur);
	}

}