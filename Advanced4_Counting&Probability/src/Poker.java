/**
 * @author minha
 * 2021. 12. 2.
 * [4강 2번] Poker
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Poker {
	static String[] poker = {"Top", "1 Pair", "2 Pair", "Triple", "Straight", "Flush", "Full House", "4 Card", "Straight Flush"};
	static int[][] map;
	static int[] pattern;
	static int[] num;
	static int result;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			map = new int[4][14];
			pattern = new int[4];
			num = new int[14];
			result = 0;

			for(int i = 0; i < 7; i++) {
				st = new StringTokenizer(br.readLine());

				// Pattern
				String strP = st.nextToken();
				int p = 0;

				if(strP.equals("S")) p = 0;
				else if(strP.equals("D")) p = 1;
				else if(strP.equals("C")) p = 2;
				else p = 3;

				// Number
				int n = Integer.parseInt(st.nextToken())-1; // 0번 카드부터 시작 

				map[p][n]++;
				pattern[p]++;
				num[n]++;
				
				if(n == 0) { // ACE
					map[p][13]++; 
					num[13]++;
				}
			}

			sb.append("#" + test_case + " ");

			if(straightFlush()) {
				sb.append(poker[8] + "\n");
				continue;
			}

			if(fourCard()) {
				sb.append(poker[7] + "\n");
				continue;
			}

			if(fullHouse()) {
				sb.append(poker[6] + "\n");
				continue;
			}

			if(flush()) {
				sb.append(poker[5] + "\n");
				continue;
			}

			if(straight()) {
				sb.append(poker[4] + "\n");
				continue;
			}
			
			sb.append(poker[result] + "\n");
		}

		System.out.println(sb.toString());
	}

	static boolean straightFlush() {
		for(int i = 0; i < 4; i++) {
			if(pattern[i] >= 5) {
				for(int j = 0; j < 10; j++) {
					boolean flag = true;

					for(int k = j; k < j+5; k++) {
						if(map[i][k] < 1) {
							flag = false;
							break;
						}
					}

					if(flag) return true;
				}
			}
		}

		return false;
	}

	static boolean fourCard() {
		for(int i : num)
			if(i == 4) return true;
		return false;
	}

	static boolean fullHouse() { // 여기서 Triple, 2 Pair, 1 Pair, Top 다 거름 
		boolean triple = false;
		int tri = -1; // 페어를 찾을 때 트리플에 포함된 카드를 배제하기 위한 값 
		
		for(int i = 0; i < 14; i++) {
			if(num[i] == 3) {
				triple = true;
				tri = i;
				result = 3;
				break;
			}
		}
		
		int pairCnt = pair(tri);
		
		if(result != 3) result = pairCnt; // 트리플 아닐 때에만 2, 1 Pair 할당 
		
		if(triple && pairCnt != 0) return true;
		
		return false;
	}
	
	static boolean flush() {
		for(int i : pattern)
			if(i >= 5) return true;
		return false;
	}

	static boolean straight() {
		for(int i = 0; i < 10; i++) {
			boolean flag = true;

			for(int j = i; j < i+5; j++) {
				if(num[j] < 1) {
					flag = false;
					break;
				}
			}

			if(flag) return true;
		}

		return false;
	}

	static int pair(int tri) {
		int cnt = 0;
		
		for(int i = 0; i < 13; i++) { // ACE 14중복으로 13까지만 확인 
			if(num[i] >= 2) {
				if(tri == i) continue; // Triple에 속한 카드라면 패스 
				cnt++;
			}
			
			if(cnt == 2) return cnt; // 2 Pair까지 확인되면 그 이상 3개까지 안봐도 됨 (2 인덱스를 만들어주기 위함) 
		}
		
		return cnt;
	}
	
}