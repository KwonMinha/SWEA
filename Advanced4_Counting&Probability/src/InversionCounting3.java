/**
 * @author minha
 * 2021. 12. 2.
 * [4강 3번] Inversion Counting
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class InversionCounting3 {
	static int[] sorted;
	static long count;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			int[] arr = new int[N];
			sorted = new int[N];
			count = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			merge_sort(arr, 0, arr.length - 1);

			sb.append("#" + test_case + " " + count + "\n");
		}

		System.out.print(sb.toString());
	}

	static void merge_sort(int[] a, int left, int right) {
		if(left == right) return;

		int mid = (left + right) / 2;

		merge_sort(a, left, mid);		
		merge_sort(a, mid + 1, right);	
		merge(a, left, mid, right);
	}

	static void merge(int[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;	

		while(l <= mid && r <= right) {
			if(a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			} else {
				count += mid - l + 1;
				sorted[idx++] = a[r++];
			}
		}

		while(r <= right) {
			sorted[idx++] = a[r++];
		}
		while(l <= mid) {
			sorted[idx++] = a[l++];
		}

		for(int i = left; i <= right; i++) {
			a[i] = sorted[i];
		}
	}

}