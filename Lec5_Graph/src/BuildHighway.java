/**
 * @author minha
 * 2021. 11. 18.
 * [5강 2번] 고속도로 건설 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class BuildHighway {
	static int N, M;
	static int[] parent;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			parent = new int[N+1];
			for(int i = 0; i < N+1; i++) {
				parent[i] = i;
			}
			
			ArrayList<Node> nodeList = new ArrayList<>();
	
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				nodeList.add(new Node(s, e, c));
			}
			
			Collections.sort(nodeList);
			
			int answer = 0;
			
			for(int i = 0; i < nodeList.size(); i++) {
				Node node = nodeList.get(i);
				
				int start = (int) node.x;
				int end = (int) node.y;
				
				if(find(start) != find(end)) {
					union(start, end);
					answer += node.w;
				}
			}

			sb.append("#" + test_case + " " + answer + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		parent[b] = a;
	}
	
}

class Node implements Comparable<Node> {
	int x;
	int y;
	double w;

	Node(int x, int y, double w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return o.w >= w ? -1 : 1;
	}
}