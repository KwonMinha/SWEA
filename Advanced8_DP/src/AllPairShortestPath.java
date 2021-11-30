/**
 * @author minha
 * 2021. 11. 30.
 * [8강 4번] All Pair Shortest Path
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class AllPairShortestPath  {
	static final int INF = 100000 * 500;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(map[i], INF);
				map[i][i] = 0;
			}
	
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
			
				if(map[a][b] > c)
					map[a][b] = c;
			}
			
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					}
				}
			}
		
			sb.append("#" + test_case + " ");
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == INF)
						sb.append(-1 + " ");
					else 
						sb.append(map[i][j] + " ");
				}
			}
			
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}