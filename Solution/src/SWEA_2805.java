/**
 * @author minha
 * 2805. 농작물 수확하기 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2805 {
	public static int N;
	public static int[][] map;
	public static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int mid = N / 2;
			int cur = mid;
			int cnt = 1;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == mid) {
						ans += map[i][j];
					} else if(i < mid) {
						int c = i + cnt;
						while(c != 0) {
							ans += map[i][cur];
							c--;
						}
						ans += map[i][cur];
					}
				}
			}
			
			while(cnt != N) {
				if(cnt == 0 || cnt == mid + 1) {
					
				}
			}
		}
	}

}