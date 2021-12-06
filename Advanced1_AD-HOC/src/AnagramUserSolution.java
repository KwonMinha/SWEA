class AnagramUserSolution {
	
	public static int FindAnagram(int L1, String S1, int L2, String S2) {
		int[] alpha1 = new int[26];
		int[] alpha2 = new int[26];
		
		for(int i = 0; i < L1; i++)
			alpha1[S1.charAt(i) - 97]++;
		
		for(int i = 0; i < L1; i++)
			alpha2[S2.charAt(i) - 97]++; 
		
		int result = 0;
		
		for(int i = 0; i <= L2 - L1; i++) {
			boolean flag = true; 
			
			for(int j = 0; j < 26; j++) {
				if(alpha2[j] < alpha1[j]) {
					flag = false;
					break;
				}
			}
			
			if(flag) result++;
			
			if(i == L2 - L1) break;
			
			alpha2[S2.charAt(i)-97]--;
			alpha2[S2.charAt(i+L1)-97]++;
		}
		
		return result;
	}
}