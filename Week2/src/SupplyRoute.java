/**
 * @author minha
 * 2021. 10. 27.
 * 1249. [S/W 문제해결 응용] 4일차 - 보급로
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class SupplyRoute {
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N;
	static int[][] map;
	static int[][] count;
	static int result;
	static boolean[][] visited;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			count = new int[N][N];
			result = Integer.MAX_VALUE;
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(count[i], Integer.MAX_VALUE-1);
			}
			
			bfs();

			sb.append("#" + test_case + " " + result + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0));
		visited[0][0] = true;
		count[0][0] = 0;
		
		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.poll().y;
			
			if(cx == N-1 && cy == N-1) {
				result = Math.min(result, count[cx][cy]);
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
			
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) 
					continue;

				int nt = count[cx][cy] + map[nx][ny];
				
				if(!visited[nx][ny] || count[nx][ny] > nt) {
					visited[nx][ny] = true;
					count[nx][ny] = nt;
					queue.add(new Point(nx, ny));
				}
			}	
		}
	}
	
}