/**
 * @author minha
 * 2021. 12. 9.
 * [6강 3번] 문자열의 거듭제곱
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class PowerOfString {
	static String S;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			S = br.readLine();

			int result = 0;

			int len = S.length();
			for(int i = 1; i <= len; i++) {
				if(len % i == 0) {
					if(isEqual(i)) {
						result = len / i;
						break;
					}	
				}
			}

			sb.append("#" + test_case + " " + result + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static boolean isEqual(int divide) {
		
		for(int i = divide; i <= S.length() - divide; i += divide) {
			String first = S.substring(i-divide, i);
			String second = S.substring(i, i + divide);
			
			if(!first.equals(second)) {
				return false;
			}
		}
		
		return true;
	}
	
}