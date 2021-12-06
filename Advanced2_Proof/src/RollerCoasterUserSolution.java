
public class RollerCoasterUserSolution {
	static Element[] sorted;
	static Element[] arr;

	public static int MinRailSpeed(int N, int[] a, int[] b) {
		sorted = new Element[N];
		arr = new Element[N];
		
		for(int i = 0; i < N; i++)
			arr[i] = new Element(a[i], b[i]);
		
		merge_sort(0, N-1);
		
		int v = 1;
		for(int i = 0; i < N; i++)
			v = (int) (((long)(sorted[i].a) * v + sorted[i].b) % 1000000007);
		
		return v;
	}
	
	static void merge_sort(int left, int right) {
		if(left == right) return;

		int mid = (left + right) / 2;

		merge_sort(left, mid);		
		merge_sort(mid + 1, right);	
		merge(left, mid, right);
	}
	
	static void merge(int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;	

		while(l <= mid && r <= right) {
			if(arr[l].c >= arr[r].c) {
				sorted[idx++] = arr[l++];
			} else {
				sorted[idx++] = arr[r++];
			}
		}

		while(r <= right) {
			sorted[idx++] = arr[r++];
		}
		while(l <= mid) {
			sorted[idx++] = arr[l++];
		}

		for(int i = left; i <= right; i++) {
			arr[i] = sorted[i];
		}
	}
}

class Element {
	int a;
	int b;
	double c;
	
	Element(int a, int b) {
		this.a = a;
		this.b = b;
		this.c = (double) (a-1) / b;
	}
}