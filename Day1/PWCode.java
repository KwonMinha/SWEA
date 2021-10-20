/**
 * @author minha
 * 2021. 10. 20.
 * [S/W 문제해결 응용] 1일차 - 단순 2진 암호코드
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class PWCode {
	public static void main(String args[]) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		String[] pwArr = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			 
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			boolean isFind = false;
			ArrayList<Integer> codeList = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				int lastIndex = str.lastIndexOf("1");
				
				if(lastIndex != -1 && !isFind) {
					for(int j = 0; j < 8; j++) {
						String code = str.substring(lastIndex-6, lastIndex+1);
						lastIndex -= 7;
						codeList.add(Arrays.asList(pwArr).indexOf(code));
					}
					isFind = true;
				}
			}

			int result = 0;
			int sum = 0;
			for(int i = 0; i < codeList.size(); i++) {
				if(i % 2 == 0)
					result += codeList.get(i);
				else
					result += codeList.get(i) * 3;
				
				sum += codeList.get(i);
			}
			
			sb.append("#" + test_case + " ");
			if(result % 10 == 0) 
				sb.append(sum + "\n");
			else
				sb.append("0\n");
		}
		
		System.out.println(sb.toString());
	}
}