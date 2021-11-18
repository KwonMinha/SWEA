/**
 * @author minha
 * 2021. 11. 8.
 * [5강 1번] 비밀
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Secret {
	static int N;
	static int[][] adjMap;
	static boolean[] visited;
	static int length;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			adjMap = new int[N+1][N+1];
			visited = new boolean[N+1];
			length = 0;
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int m = Integer.parseInt(st.nextToken());
				
				int a = Integer.parseInt(st.nextToken());
				for(int j = 1; j < m; j++) {
					int b = Integer.parseInt(st.nextToken());
					
					if(adjMap[a][b] == 0)
						adjMap[a][b] = 1;
					a = b;
				}
			}
			
			sb.append("#" + test_case + " ");
			
			for(int i = 1; i < N+1; i++) {
				int sum = 0;
				
				for(int j = 1; j < N+1; j++) {
					sum += adjMap[i][j];
				}
				
				sb.append(sum + " ");
			}
			
			for(int i = 1; i < N+1; i++) {
				dfs(i, 1);
			}

			sb.append(length + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static void dfs(int v, int depth) {
		visited[v] = true;
		
		length = Math.max(length, depth);
		
		for(int i = 1; i < N+1; i++) {
			if(adjMap[v][i] != 0 && !visited[i]) {
				dfs(i, depth+1);
			}
		}
		
		visited[v] = false; // 그래프에 있는 모든 정점을 처리하기 위해서는 이미 갔던 곳도 다시 가야할 수 있기에 false로 만들어 줌 
	}
	
}