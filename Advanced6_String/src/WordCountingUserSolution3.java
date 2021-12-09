class WordCountingUserSolution3 {
	
	public static int FindString(int N, String A, int M, String B) {
		long aHash = 0;
		long bHash = 0;
		int power = 1;
		int count = 0;
		
		for(int i = 0; i <= N - M; i++) {
			if(i == 0) {
				for(int j = 0; j < M; j++) {
					aHash += A.charAt(M - 1 - j) * power;
					bHash += B.charAt(M - 1 - j) * power;
					
					if(j < M -1) {
						power = power * 2;
					}
				}
			} else {
				aHash = 2 * (aHash - (A.charAt(i - 1) * power)) + A.charAt(M - 1 + i);
			}
			
			if(aHash == bHash) {
				boolean flag = true;
				
				for(int j = 0; j < M; j++) {
					if(A.charAt(i + j) != B.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					count++;
				}
			}
		}
		
		return count;
	}
}