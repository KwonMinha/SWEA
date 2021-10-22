/**
 * @author minha
 * @date 2020. 6. 6.
 * 2105. 디저트 카페 
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA_2105 {
	public static int[][] arr;
	public static boolean[][] visited;
	public static boolean[] visitedNum;
	public static int startX, startY, result, n;
	public static int[][] dir = {{1,1},{1,-1},{-1,-1},{-1,1}};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			n = N;
			arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[N][N];
			visitedNum = new boolean[101];
			result = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					startX = i;
					startY = j;
					visited[i][j] = true;
					visitedNum[arr[i][j]] = true;
					solve(i, j, 1, 0);
					visited[i][j] = false;
					visitedNum[arr[i][j]] = false;
				}
			}
			
			if(result < 4)
				result = -1;
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	public static void solve(int x, int y, int cnt, int d) {
		if(d == 4) 
			return;
		
		int dx = x + dir[d][0];
		int dy = y + dir[d][1];
		
		if(dx < 0 || dy < 0 || dx >= n || dy >= n)
			return;
		
		if(visited[dx][dy] || visitedNum[arr[dx][dy]]) {
			if(dx == startX && dy == startY) {
				result = Math.max(result, cnt);
			}
			return;
		}
		
		visited[dx][dy] = true;
		visitedNum[arr[dx][dy]] = true;
		solve(dx, dy, cnt+1, d);
		solve(dx, dy, cnt+1, d+1);
		visited[dx][dy] = false;
		visitedNum[arr[dx][dy]] = false;
	}
}