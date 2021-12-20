class UserSolution {

	boolean init(int N) {
		char[] c = {'\0'};

		for (int i = 0; i < 65536; i += 24) {
			Solution.memwrite(c, i, 1);
		}
		
		return true; 
	}

	void put(char[] key_in, char[] value_in) {
		int position = getPosition();

		Solution.memwrite(key_in, position, 12);
		Solution.memwrite(value_in, position + 12, 12);
	}
	
	void del(char[] key_in) {
		int position = findKey(key_in);
		
		key_in[0] = '\0';
		
		Solution.memwrite(key_in, position, 1);
	}

	void get(char[] key_in, char[] value_out) {
		int position = findKey(key_in);
		
		Solution.memread(value_out, position + 12, 12);
		
		value_out[12] = '\0';
	}

	void getkey(char[] value_in, char[] key_out) {
		int position = findValue(value_in);
		
		Solution.memread(key_out, position - 12, 12);
		
		key_out[12] = '\0';
	}

	int getPosition() {
		char[] c = new char[1];
		
		int position = 0;
		
		for (int i = 0; i < 65536; i += 24) {
			Solution.memread(c, i, 1);

			if (c[0] == '\0') {
				position = i;
				break;
			}
		}
		return position;
	}

	int findKey(char[] key_in) {
		char tmp_key[] = new char[13];

		int position = 0;
		
		for (int i = 0; i < 65536; i += 24) {
			Solution.memread(tmp_key, i, 12);

			if (mstrcmp(key_in, tmp_key)) {
				position = i;
				break;
			}
		}

		return position;
	}

	int findValue(char[] value_in) {
		char tmp_value[] = new char[13];

		int position = 0;
		
		for (int i = 12; i < 65536; i += 24) {
			Solution.memread(tmp_value, i, 12);

			if (mstrcmp(value_in, tmp_value)) {
				position = i;
				break;
			}    		
		}

		return position;
	}
	
	boolean mstrcmp(char[] a, char[] b) {
		int i = 0;
		while(a[i] != '\0' && a[i] == b[i])
			++i;
		return a[i] == b[i];
	}
}

