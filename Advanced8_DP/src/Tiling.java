/**
 * @author minha
 * 2021. 11. 25.
 * [8강 5번] 타일링 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Tiling {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			BigInteger[] dp = new BigInteger[N+1];
			
			dp[1] = BigInteger.ONE;
			
			if(N > 1)
				dp[2] = BigInteger.valueOf(3);
			
			for(int i = 3; i <= N; i++) {
				dp[i] = dp[i-2].multiply(new BigInteger("2")).add(dp[i-1]);
			}

			sb.append("#" + test_case + " " + dp[N] + "\n");
		}

		System.out.println(sb.toString());
	}
	
}