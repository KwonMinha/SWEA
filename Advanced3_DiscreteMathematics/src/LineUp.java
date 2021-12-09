/**
 * @author minha
 * 2021. 12. 10.
 * [3강 2번] 줄 세우기 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class LineUp {
	static int N, M;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static Stack<Integer> stack;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			visited = new boolean[N+1];
			stack = new Stack<>();
			
			for(int i = 1; i < N+1; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
			}
			
			for(int i = 1; i < N+1; i++) {
				if(!visited[i]) {
					dfs(i);
				}
			}
			
			sb.append("#" + test_case + " ");
			while(!stack.isEmpty()) {
				sb.append(stack.pop() + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
	
	static void dfs(int v) {
		visited[v] = true;
		
		for(int i : adjList[v]) {
			if(!visited[i]) {
				dfs(i);
			}
		}
		
		stack.push(v);
	}
	
}