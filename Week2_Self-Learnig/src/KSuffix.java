/**
 * @author minha
 * 2021. 11. 05.
 * 1256. [S/W 문제해결 응용] 6일차 - K번째 접미어
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class KSuffix {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int K = Integer.parseInt(br.readLine()); 
			String str = br.readLine();
			
			ArrayList<String> suffixList = new ArrayList<>();
			
			String suffix = "";
			
			for(int i = 0; i < str.length(); i++) {
				suffix = str.substring(i, str.length());
				suffixList.add(suffix);
			}
			
			Collections.sort(suffixList); // 오름차순 정렬 
			
			String result = "";
			
			if(suffixList.size() < K) {
				result = "none";
			} else {
				result = suffixList.get(K-1);
			}
			
			sb.append("#" + test_case + " " + result + "\n");
		}

		System.out.println(sb.toString());
	}
}