/**
 * @author minha
 * 2021. 10. 20.
 * StringBuffer 클래스의 reverse() 메서드없이 문자열 뒤집기 구현 
 */

package Practice;

public class Practice1 {

	public static void main(String[] args) {
		String str = "ABCDE";
		char[] strArr = str.toCharArray();
		
		for(int i = 0; i < strArr.length/2; i++) {
			char temp = strArr[i];
			strArr[i] = strArr[strArr.length-i-1];
			strArr[strArr.length-i-1] = temp;
		}
		
		for(int i = 0; i < strArr.length; i++) {
			System.out.print(strArr[i]);
		}
		System.out.println();
	}

}
