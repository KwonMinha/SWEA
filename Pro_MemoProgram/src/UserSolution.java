class UserSolution
{
	int w, h;
	int cursor;
	int lastIdx;
	char[] arr;
	
	void init(int H, int W, char mStr[])
	{
		w = W;
		h = H;
		cursor = 0;
		lastIdx = 0;
		arr = new char[90001];
		
		for(int i = 0; i < mStr.length; i++) {
			if(mStr[i] == '\0') {
				break;
			}
			
			lastIdx++;
			arr[i] = mStr[i];
		}
	}
	
	void insert(char mChar)
	{
		
		//System.out.println("cur : " + cursor + ", last : " + lastIdx);
		
		
		System.arraycopy(arr, cursor, arr, cursor+1, lastIdx - cursor);
		arr[cursor] = mChar;
		lastIdx++;
		cursor++;
	}

	char moveCursor(int mRow, int mCol)
	{
		int index = (mRow * w) - (w - mCol) - 1;
		
		if(index >= lastIdx) {
			cursor = lastIdx;
			return '$';
		} else {
			cursor = index;
			return arr[index];
		}
	}

	int countCharacter(char mChar)
	{
		if(cursor == lastIdx) {
			return 0;
		}
		
		int result = 0;
		
		for(int i = cursor; i < lastIdx; i++) {
			if(arr[i] == mChar) {
				result++;
			}
		}
		
		return result;
	}
}

class Node {
	char data;
	Node prev;
	Node next;
	
	Node(char data) {
		this.data = data;
	}
}