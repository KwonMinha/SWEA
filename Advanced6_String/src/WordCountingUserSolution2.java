import java.util.ArrayList;

class WordCountingUserSolution2 {
	
	public static int FindString(int N, String A, int M, String B) {
		final long MOD = 100000007;
		long aHash = 0;
		long bHash = 0;
		long power = 1;
		int count = 0;
		
		for(int i = 0; i <= N - M; i++) {
			if(i == 0) {
				for(int j = 0; j < M; j++) {
					aHash += (A.charAt(M - 1 - j) * power) % MOD;
					bHash += (B.charAt(M - 1 - j) * power) % MOD;
					
					if(j < M -1) {
						power = power % MOD * 31 % MOD;
					}
				}
			} else {
				aHash = 31 * (aHash - A.charAt(i - 1) * power) % MOD + A.charAt(M - 1 + i);
				//aHash = 2 * (aHash - A.charAt(i - 1) * power) + A.charAt(M - 1 + i);
			}
			
			if(aHash == bHash) {
//				boolean flag = true;
//				
//				for(int j = 0; j < M; j++) {
//					if(A.charAt(i + j) != B.charAt(j)) {
//						flag = false;
//						break;
//					}
//				}
//				
//				if(flag) {
//					findList.add(i+1);
//				}
				
				count++;
			}
		}
		
		return count;
	}
}

