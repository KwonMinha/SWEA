/**
 * @author minha
 * 2021. 12. 2.
 * [4강 3번] Inversion Counting
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class InversionCounting {
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
		for(int size = 1; size <= right; size += size) {
			for(int l = 0; l <= right - size; l += (2 * size)) {
				int low = l;
				int mid = l + size - 1;
				int high = Math.min(l + (2 * size) - 1, right);

				merge(a, low, mid, high);
			}
		}
	}

	static void merge(int[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;	
		
		while(l <= mid && r <= right) {
			if(a[l] <= a[r]) {
				sorted[idx] = a[l];
				idx++;
				l++;
			} else {
				sorted[idx] = a[r];
				count += r - idx;
				idx++;
				r++;
			}
		}
		
		if(l > mid) {
			while(r <= right) {
				sorted[idx] = a[r];
				idx++;
				r++;
			}
		} else {
			while(l <= mid) {
				sorted[idx] = a[l];
				idx++;
				l++;
			}
		}
		
		for(int i = left; i <= right; i++) {
			a[i] = sorted[i];
		}
	}
	
}