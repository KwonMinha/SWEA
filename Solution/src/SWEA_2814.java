/**
 * @author minha
 * @date 2020. 6. 6.
 * 2814. 최장 경로 
 * https://minhamina.tistory.com/52
 */

import java.util.Scanner;

class SWEA_2814
{
	public static int[][] map;
	public static boolean[] visited;
	public static int max;
	public static int n;

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			max = Integer.MIN_VALUE;
			int N = sc.nextInt();
			int M = sc.nextInt();
			n = N;

			map = new int[N+1][N+1];
			visited = new boolean[N+1];


			for(int i = 0; i < M; i++) {
				int n1 = sc.nextInt();
				int n2 = sc.nextInt();

				map[n1][n2] = map[n2][n1] = 1;
			}

			for(int i = 1; i < N+1; i++) {
				//여기서 visited 배열을 초기화 시켜주면 아래의 visited[i] = false 문 필요없음 
				dfs(1, i);
				visited[i] = false;
			}

			System.out.println("#" + test_case + " " + max);
		}
	}

	public static void dfs(int cnt, int v) {
		visited[v] = true;

		for(int i = 0; i < n+1; i++) {
			if(map[v][i] == 1 && !visited[i]) {
				dfs(cnt+1, i);
				visited[i] = false;
			}
		}	
		max = Math.max(cnt, max);
	}
}