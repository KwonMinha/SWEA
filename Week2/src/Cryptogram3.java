/**
 * @author minha
 * 2021. 10. 27.
 * 1230. [S/W 문제해결 기본] 8일차 - 암호문3
 * 실패 - x가 위치가 아니라 암호라고 생각해서 x의 위치를 다시 찾음 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Cryptogram3 {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 암호문의 길이 
			st = new StringTokenizer(br.readLine()); // 암호문 
			
			LinkedList<String> cryptoList = new LinkedList<>();
			
			for(int i = 0; i < N; i++) {
				cryptoList.add(st.nextToken());	
			}
			
			N = Integer.parseInt(br.readLine()); // 명령어의 개수 
			st = new StringTokenizer(br.readLine()); // 명령어 
			
			for(int i = 0; i < N; i++) {
				char command = st.nextToken().charAt(0);
				
				if(command == 'I') { // I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입
					String x = st.nextToken();
					int y = Integer.parseInt(st.nextToken());
					int index = cryptoList.indexOf(x);
					
					for(int j = 0; j < y; j++) {
						cryptoList.add(index+1, st.nextToken());
						index++;
					}
				} else if(command == 'D') { // D(삭제) x, y : 앞에서부터 x의 위치 바로 다음부터 y개의 숫자를 삭제
					String x = st.nextToken();
					int y = Integer.parseInt(st.nextToken());
					int index = cryptoList.indexOf(x);
					
					for(int j = 0; j < y; j++) {
						if(index + 1 < cryptoList.size()) {
							cryptoList.remove(index+1);
						}
					}
				} else if(command == 'A') { // A(추가) y, s : 암호문의 맨 뒤에 y개의 숫자를 덧붙인다. s는 덧붙일 숫자들
					int y = Integer.parseInt(st.nextToken());
					
					for(int j = 0; j < y; j++) {
						cryptoList.addLast(st.nextToken());
					}
				}
			}
			
			sb.append("#" + test_case + " ");
			
			for(int i = 0; i < 10; i++) {
				sb.append(cryptoList.get(i) + " ");
			}
			
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}