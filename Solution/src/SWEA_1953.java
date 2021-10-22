/**
 * @author minha
 * @date 2020. 6. 6.
 * 1953. 탈주범 검거
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class SWEA_1953 {
	public static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static int[][] map;
	public static boolean[][] visited;
	public static int N, M, R, C, L, ans;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 1;
			solve(R, C);

			sb.append("#" + t + " " +ans + "\n");
		}
		System.out.println(sb);
	}

	public static void solve(int r, int c) {
		int time = 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(r, c));
		visited[r][c] = true;

		while(!queue.isEmpty()) {
			int size = queue.size();
			if(++time >= L) return;

			for(int z = 0 ; z < size ; ++z) {

				Node cur = queue.poll();
				int type = map[cur.r][cur.c];

				int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
				for(int i = 0; i < 4; i++) {
					int dr = cur.r + dir[i][0];
					int dc = cur.c + dir[i][1];

					if(dr < 0 || dr >= N || dc < 0 || dc >= M) continue;
					//if(visited[dr][dc] || map[dr][dc] == 0) continue;

					if(!visited[dr][dc] && map[dr][dc] != 0) {
						int next = map[dr][dc];

						switch(i) {
						case 0: //위쪽으로 가는 경우 
							if(type == 1 || type == 2 || type == 4 || type == 7) { //현재 파이프 위쪽 열려있어야 함 
								if(next == 1 || next == 2 || next == 5 || next == 6) { //다음 파이프 아래쪽 열려있어야 함  
									visited[dr][dc] = true;
									queue.offer(new Node(dr, dc));
									ans++;
								}
							}
							break;
						case 1: //아래쪽으로 가는 경우 
							if(type == 1 || type == 2 || type == 5 || type == 6) { //현재 아래쪽 
								if(next == 1 || next == 2 || next == 4 || next == 7) { //다음 위쪽   
									visited[dr][dc] = true;
									queue.offer(new Node(dr, dc));
									ans++;
								}
							}
							break;
						case 2: //오른쪽으로 가는 경우 
							if(type == 1 || type == 3 || type == 6 || type == 7) { //현재 오른쪽  
								if(next == 1 || next == 3 || next == 4 || next == 5) { //다음 왼쪽 
									visited[dr][dc] = true;
									queue.offer(new Node(dr, dc));
									ans++;
								}
							}
							break;
						case 3: //왼쪽으로 가는 경우 
							if(type == 1 || type == 3 || type == 4 || type == 5) { //현재 왼쪽 
								if(next == 1 || next == 3 || next == 6 || next == 7) { //다음 오른쪽 
									visited[dr][dc] = true;
									queue.offer(new Node(dr, dc));
									ans++;
								}
							}
							break;
						}
					}
				}
			}			
		}
	}
}