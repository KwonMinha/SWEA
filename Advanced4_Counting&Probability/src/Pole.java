/**
 * @author minha
 * 2021. 12. 2.
 * [4강 1번] Pole
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pole {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long[][][] dp = new long[N+1][L+1][R+1];
			dp[1][1][1] = 1;
			dp[1][0][0] = dp[1][0][1] = dp[1][1][0] = 0;
			
			for(int i = 2; i < N+1; i++) {
				for(int j = 1; j < L+1; j++) {
					for(int k = 1; k < R+1; k++) {
						dp[i][j][k] = dp[i-1][j-1][k] + dp[i-1][j][k-1] + ((i-2) * dp[i-1][j][k]);
					}
				}
			}

			sb.append("#" + test_case + " " + dp[N][L][R] + "\n");
		}

		System.out.println(sb.toString());
	}
	
}