/**
 * @author minha
 * 2021. 10. 20.
 * ItoA()
 */

package Practice;

public class Practice2 {

	public static void main(String[] args) {
		int x = 123;
		
		int count = 0;
		int i = 0;
		int y;
		
		char[] str = Integer.toString(x).toCharArray();
		
		while(true) {
			y = x % 10;
			str[i++] = (char) (y + '0');
			x /= 10;
			if(x == 0) break;
		}
		
		for(i = 0; i < str.length; i++) {
			System.out.print(str[i]);
		}
	}

}
