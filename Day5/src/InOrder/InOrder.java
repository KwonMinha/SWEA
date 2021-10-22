/**
 * @author minha
 * 2021. 10. 22.
 * 1231. [S/W 문제해결 기본] 9일차 - 중위순회
 * 2차원 배열 트리 
 */

package InOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class InOrder {
	static int[][] tree;
	static String[] alphabet;
	static StringBuilder sb;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			tree = new int[N+1][2];
			alphabet = new String[N+1];

			for(int i = 0; i < N; i++) {
				String[] arr = br.readLine().split(" ");
				int a = Integer.parseInt(arr[0]);
				alphabet[a] = arr[1];
				
				int b = 0; 
				int c = 0;
				if(arr.length == 3) {
					b = Integer.parseInt(arr[2]);
				} else if(arr.length == 4) {
					b = Integer.parseInt(arr[2]);
					c = Integer.parseInt(arr[3]);
				}
			
				tree[a][0] = b;
				tree[a][1] = c;
				
				alphabet[a] = arr[1];
			}

			sb.append("#" + test_case + " ");
			inOrder(1);
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	static void inOrder(int x) {
		if(tree[x][0] == 0 && tree[x][1] == 0) {
			sb.append(alphabet[x]);
		} else {
			if(tree[x][0] != 0) {
				inOrder(tree[x][0]);
			} 

			sb.append(alphabet[x]);
			
			if(tree[x][1] != 0) {
				inOrder(tree[x][1]);
			}
		}
	}
	
}