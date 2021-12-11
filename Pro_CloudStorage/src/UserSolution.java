class UserSolution {
	Device device;
	Cmd[][] cmdLog;
	int[] cmdCnt;

	void init(int N) {
		device = new Device();
		cmdLog = new Cmd[2][50001];
		cmdCnt = new int[2];

		cmdLog[0][cmdCnt[0]++] = new Cmd(0, CmdType.INIT, N, 0);
		cmdLog[1][cmdCnt[1]++] = new Cmd(0, CmdType.INIT, N, 0);
	}

	void addDirectory(int mTime, int mDevice, int mPID, int mUID) {
		cmdLog[mDevice][cmdCnt[mDevice]++] = new Cmd(mTime, CmdType.ADD, mPID, mUID);
	}

	void removeDirectory(int mTime, int mDevice, int mUID) {
		cmdLog[mDevice][cmdCnt[mDevice]++] = new Cmd(mTime, CmdType.REMOVE, mUID, 0);
	}

	void moveDirectory(int mTime, int mDevice, int mPID, int mUID) {
		cmdLog[mDevice][cmdCnt[mDevice]++] = new Cmd(mTime, CmdType.MOVE, mPID, mUID);
	}

	void restore(int mTime, int mDevice, int mPrevTime) {
		cmdLog[mDevice][cmdCnt[mDevice]++] = new Cmd(mTime, CmdType.RESTORE, mDevice, mPrevTime);
	}

	void sync(int mTime, int mDevice, int mPrevTime) {
		cmdLog[mDevice][cmdCnt[mDevice]++] = new Cmd(mTime, CmdType.RESTORE, mDevice == 0 ? 1 : 0, mPrevTime);
	}

	int countDirectory(int mDevice, int mUID) {
		int idx = 0;

		Cmd[] cmdStack = new Cmd[50001];
		int d = mDevice;

		for(int i = cmdCnt[mDevice] - 1; i >= 0; i--) {
			Cmd cmd = cmdLog[d][i];

			if(cmd.type == CmdType.RESTORE) {
				d = cmd.param1;
				i = searchTime(d, cmd.param2);
				continue;
			}

			cmdStack[idx] = cmd;
			idx++;
		}

		for(int i = idx - 1; i >= 0; i--) {
			Cmd cmd = cmdStack[i];

			if(cmd.type == CmdType.INIT) {
				device.init(cmd.param1);
			} else if(cmd.type == CmdType.ADD) {
				device.add(cmd.param1, cmd.param2);
			} else if(cmd.type == CmdType.REMOVE) {
				device.remove(cmd.param1);
			} else if(cmd.type == CmdType.MOVE) {
				device.move(cmd.param1, cmd.param2);
			}
		}

		return device.getChildCnt(mUID);
	}

	int searchTime(int mDevice, int time) {
		int low = 0;
		int high = cmdCnt[mDevice];

		while(low + 1 < high) {
			int mid = (low + high) / 2;

			if(cmdLog[mDevice][mid].time <= time) {
				low = mid;
			} else {
				high = mid;
			}
		}

		return low + 1;
	}

	// Device Class

	class Device {
		int[] parentId = new int[50001];
		int[] childCnt = new int[50001];

		void init(int N) {
			for(int i = 0; i < N; i++) {
				parentId[i] = -1;
				childCnt[i] = 0;
			}
			childCnt[0] = 1;
		}

		void add(int pId, int uId) {
			parentId[uId] = pId;
			childCnt[uId] = 1;
			updateChildCount(pId, 1);
		}

		void remove(int uId) {
			int cnt = childCnt[uId];
			updateChildCount(uId, -cnt);
			parentId[uId] = -1;
		}

		void move(int pId, int uId) {
			int cnt = childCnt[uId];
			updateChildCount(parentId[uId], -cnt);
			parentId[uId] = pId;
			updateChildCount(pId, cnt);
		}

		void updateChildCount(int id, int cnt) {
			while(id >= 0) {
				childCnt[id] += cnt;
				id = parentId[id];
			}
		}

		int getChildCnt(int id) {
			return childCnt[id];
		}
	}

	// Cmd Class

	enum CmdType {
		INIT, ADD ,REMOVE, MOVE, RESTORE
	}

	class Cmd {
		CmdType type;
		int time;
		int param1;
		int param2;

		Cmd(int time, CmdType type, int param1, int param2) {
			this.type = type;
			this.time = time;
			this.param1 = param1;
			this.param2 = param2;
		}
	}
}

