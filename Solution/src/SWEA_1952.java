/**
 * @author minha 
 * @date 2020. 6. 6.
 * 1952. 수영장  
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA_1952 {
	public static int[] fee, plan;
	public static int min;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());


		for(int t = 1; t <= T; t++) {
			//이용권 요금 초기화 
			StringTokenizer st = new StringTokenizer(br.readLine());
			fee = new int[4];
			for(int i = 0; i < 4; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			min = fee[3]; //1년 이용은 DFS 할 필요 없으니 초기값으로 지정 

			//12개월 계획 초기화 
			st = new StringTokenizer(br.readLine());
			plan = new int[12];
			for(int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());	
			}

			solve(0, 0);

			sb.append("#" + t + " " + min + "\n");
		}
		
		System.out.println(sb);
	}

	//DFS 완전 탐색 
	public static void solve(int sum, int m) {
		if(m >= 12) {
			min = Math.min(sum, min);
			return;
		}

		if(plan[m] == 0) { //이용하지 않는 달은 넘어감 
			solve(sum, m + 1);
		} else {
			solve(sum + plan[m] * fee[0], m + 1); //1일 이용으로 계산 
			solve(sum + fee[1], m + 1); //1달 이용으로 계산 
			solve(sum + fee[2], m + 3); //3달 이용으로 계산 
		}
	}
}