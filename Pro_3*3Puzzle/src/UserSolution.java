class UserSolution {

	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, 1, 0, -1};

	int[][] boards;
	boolean[][] checked;
	int[][] prev;
	int[][] queue;

	Point blank;

	public void solve(int[][] board, int[][] pattern, int callCntLimit) {
		boards = new int[5][5];
		checked = new boolean[5][5];
		prev = new int[5][5];
		queue = new int[25][2];
		
		for(int i = 0; i< 5; i++) {
			for(int j = 0; j < 5; j++) {
				boards[i][j] = board[i][j];
				if(board[i][j] == 0) {
					blank = new Point(i, j);
				}
			}
		}
		for(int i = 1; i <= 3; i++) {
			for(int j = 1; j <= 3; j++) {
				search(i, j, pattern[i-1][j-1]);
				checked[i][j] = true;
			}
		}
	}
	public void search(int x, int y, int color) {
		if(boards[x][y] == color) {
			return;
		}
		
		bfs(x, y);

		int qx = 0;
		int qy = 0;

		for(int i = 0; i < queue.length; i++) {
			qx = queue[i][0];
			qy = queue[i][1];     

			if(boards[qx][qy] == color) {
				break;
			}
		}

		int[][] temp = new int[5][5];
		for (int i = 0; i < 5; i++) { 
			System.arraycopy(prev[i], 0, temp[i], 0, prev[i].length);
		}

		while(qx != x || qy != y) {
			int dir = temp[qx][qy];

			int tx = qx - dx[dir];
			int ty = qy - dy[dir];

			checked[qx][qy] = true;

			move(tx, ty);

			checked[qx][qy] = false;

			Solution.swap(dir);

			update(qx, qy);

			qx = tx;
			qy = ty;
		}
	}

	public void bfs(int x, int y) {
		boolean[][] visited = new boolean[5][5];
		visited[x][y] = true;

		int current = 0;
		int next = 1;

		queue[current][0] = x;
		queue[current][1] = y;

		while(current != next) {
			for(int i = 0; i < 4; i++) {
				int nx = queue[current][0] + dx[i];
				int ny = queue[current][1] + dy[i];

				if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny] || checked[nx][ny]) {
					continue;
				}

				queue[next][0] = nx;
				queue[next++][1] = ny;
				prev[nx][ny] = i;
				visited[nx][ny] = true;
			}
			
			current++;
		}
	}

	public void move(int x, int y) {
		bfs(x, y);

		while(blank.x != x || blank.y != y) {
			int dir = prev[blank.x][blank.y]^2;

			Solution.swap(dir);

			int nx = blank.x + dx[dir];
			int ny = blank.y + dy[dir];

			update(nx, ny);
		}
	}

	public void update(int x, int y) {
		boards[blank.x][blank.y] = boards[x][y];
		boards[x][y]= 0;
		blank = new Point(x, y);
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}