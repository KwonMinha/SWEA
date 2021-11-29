/**
 * @author minha
 * 2021. 11. 29.
 * [8강 2번] Largest Empty Square 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class LargestEmptySquare  {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			int[][] dp = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			int max = 0;
			
			for(int i = 1; i < N; i++) {
				for(int j = 1; j < N; j++) {
					if(map[i][j] == 1) {
						dp[i][j] = 0;
					} else {
						dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
						max = Math.max(max, dp[i][j]);
					}
				}
			}

			sb.append("#" + test_case + " " + max + "\n");
		}

		System.out.println(sb.toString());
	}

}