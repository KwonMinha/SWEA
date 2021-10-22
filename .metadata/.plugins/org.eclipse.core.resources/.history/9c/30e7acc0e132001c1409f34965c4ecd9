/**
 * @author minha
 * 2021. 10. 20.
 * [S/W 문제해결 기본] 3일차 - String
 */

package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class String1 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String caseNum = br.readLine();
			String pattern = br.readLine();
			String str = br.readLine();
			
			int result = (str.length() - str.replace(pattern, "").length()) / pattern.length();
			sb.append("#" + test_case + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
	}
}