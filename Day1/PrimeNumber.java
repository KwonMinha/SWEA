/**
 * @author minha
 * 2021. 10. 20.
 * 1266. [S/W 문제해결 응용] 9일차 - 소수 완제품 확률
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class PrimeNumber {
	static int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17};

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			// 5분 안에 완제품을 만들 확률
			double skillA = Double.parseDouble(st.nextToken()) / 100;
			double skillB = Double.parseDouble(st.nextToken()) / 100;

			double A = 0, B = 0;

			for(int i = 0; i < primeNumbers.length; i++) {
				// 18개중 소수 N만큼 뽑는 경우의 수  * 각 경우에서 N개 만드는데 성공할 확률 * 각 경우에서 N개 만드는데 실패할 확률
				A += combination(18, primeNumbers[i]) * Math.pow(skillA, primeNumbers[i]) * Math.pow(1-skillA, 18-primeNumbers[i]);
				B += combination(18, primeNumbers[i]) * Math.pow(skillB, primeNumbers[i]) * Math.pow(1-skillB, 18-primeNumbers[i]);
			}
			
			//double result = 1 - (1 - A) * (1 - B);
			double result =  A + B - (A * B);

			sb.append("#" + test_case + " " + String.format("%.6f", result) + "\n");			
		}

		System.out.println(sb.toString());
	}

	// 조합의 수 구하기 - nCr
	public static int combination(int n, int r) {
		if(n == r || r == 0) 
			return 1; 
		else 
			return combination(n - 1, r - 1) + combination(n - 1, r); 
	}

}