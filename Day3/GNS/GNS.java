/**
 * @author minha
 * 2021. 10. 20.
 * [S/W 문제해결 기본] 5일차 - GNS
 */

package GNS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class GNS {
	static String[] numbers = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			String caseNum = st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			int[] count = new int[10];
			
			for(int i = 0; i < N; i++) {
				String num = st.nextToken();
				
				if(num.equals("ZRO")) count[0]++;
				else if(num.equals("ONE")) count[1]++;
				else if(num.equals("TWO")) count[2]++;
				else if(num.equals("THR")) count[3]++;
				else if(num.equals("FOR")) count[4]++;
				else if(num.equals("FIV")) count[5]++;
				else if(num.equals("SIX")) count[6]++;
				else if(num.equals("SVN")) count[7]++;
				else if(num.equals("EGT")) count[8]++;
				else if(num.equals("NIN")) count[9]++;
			}
			
			sb.append("#" + test_case + " ");
			
			int num = 0;
			for(int i : count) {		
				while(i > 0) {
					sb.append(numbers[num] + " ");
					i--;
				}
				num++;
			}

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}