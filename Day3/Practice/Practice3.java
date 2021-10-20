/**
 * @author minha
 * 2021. 10. 20.
 * 고지식한 방법으로 문자열 패턴 찾기 
 */

package Practice;

import java.util.Scanner;

class Practice3 {
	public static void main(String args[]) throws Exception {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		
		String code = sc.nextLine();
		String pattern = sc.nextLine();
		
		char[] codeArr = code.toCharArray();
		char[] patternArr = pattern.toCharArray();
		
		for(int i = 0; i < codeArr.length - patternArr.length; i++) {
			boolean flag = true;
			
			for(int j = 0; j < patternArr.length; j++) {
				if(codeArr[i+j] != patternArr[j]) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				System.out.println(i);
				return;
			}
		}
	}
}