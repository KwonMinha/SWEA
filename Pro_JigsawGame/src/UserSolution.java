class UserSolution {
	int lastIdx;
	int[] wall;
	int mold[][][][][];

	void init() {
		lastIdx = 0;
		wall = new int[100001];
		mold = new int[11][11][11][11][11];
	}

	void makeWall(int mHeights[]) {
		if(lastIdx == 0) { // 벽 만들기 
			int max = 0;

			for(int i = 0; i < 5; i++) {
				wall[i] = mHeights[i];
				max = Math.max(max, mHeights[i]);
			}

			// 현 조각의 최대 max 길이 막대를 기준으로 남는 부분의 모양 몰드에 추가 (= 남는 부분이 바로 매칭 될 모양)
			mold[max - wall[0]][max - wall[1]][max - wall[2]][max - wall[3]][max - wall[4]]++;

			lastIdx += 5; // 추가했으니 추가한만큼 뒤로 인덱스 이동 
		} else { // 벽 연장 
			int preIdx = lastIdx;

			for(int i = 0; i < 5; i++) {
				wall[lastIdx++] = mHeights[i];
			}

			for(int i = preIdx - 4; i <= preIdx; i++) {
				int max = 0;

				for(int j = i; j < i + 5; j++) {
					max = Math.max(max, wall[j]);
				}

				// 새로운 조각과 합쳐서 포함되는 모양도 몰드에 추가 
				mold[max - wall[i]][max - wall[i + 1]][max - wall[i + 2]][max - wall[i + 3]][max - wall[i + 4]]++;
			}
		}
	}

	int matchPiece(int mHeights[]) {
		if(lastIdx < 5) { // 막대 개수가 5미만이라면 매칭할 수 없음 
			return -1;
		}

		int min = 10;

		for(int i = 0; i < 5; i++) { // 벽으로 만들어지는 모양과 달리 매칭될 모양은 min 값을 토대로 남는 모양 만듬 (180도 회전하기 때문) 
			min = Math.min(min, mHeights[i]);
		}

		// 매칭 조각의 최소 길이 막대를 기준으로 남는 부분의 모양이 몰드에 없다면 매칭될 수 없음
		if(mold[mHeights[4] - min][mHeights[3] - min][mHeights[2] - min][mHeights[1] - min][mHeights[0] - min] == 0) {
			return -1;
		}

		// 매칭 조각과 매칭될 벽 조각이 있음 
		for(int i = lastIdx - 5; i >= 0; i--) {
			// 매칭 조각과 벽 조각의 높이 합이 5개 모두 동일해서 직사각형을 만들 수 있는 경우 
			if(wall[i] + mHeights[4] == wall[i + 1] + mHeights[3] &&
					wall[i + 1] + mHeights[3] == wall[i + 2] + mHeights[2] &&
					wall[i + 2] + mHeights[2] == wall[i + 3] + mHeights[1] &&
					wall[i + 3] + mHeights[1] == wall[i + 4] + mHeights[0]) {

				// 매칭될 위치 -4, +4 부분부터 시작하는 조각 모양들 몰드에서 모두 삭제 (매칭될 부분이 삭제될 것이기 때문)
				for(int j = i - 4; j <= i + 4; j++) {
					if(j < 0) { // 0이하 - 왼쪽 벽 범위 초과 -> 다음 인덱스 확인 
						continue;
					}

					if(j + 4 >= lastIdx) { // 마지막 인덱스 이상 - 오른쪽 벽 범위 초과 -> 멈춤 
						break;
					}

					// 모양을 추가했던 것과 마찬가지로 몰드에서 삭제 
					int max = 0;

					for(int k = j; k < j + 5; k++) {
						max = Math.max(max, wall[k]);
					}

					mold[max - wall[j]][max - wall[j + 1]][max - wall[j + 2]][max - wall[j + 3]][max - wall[j + 4]]--;	
				}

				// 매칭된 모양 실제로 벽에서 삭제 -> 삭제된 부분만큼 뒤에 남은 조각 당겨오기 
				for(int j = i; j < 100001; j++) {
					if(wall[j] == 0) {
						break;
					}

					wall[j] = wall[j + 5];
				}

				lastIdx -= 5; // 삭제된만큼 마지막 인덱스도 변경

				// 삭제된 공간에 당겨온 조각이 포함되는 새로운 모양들 몰드에 추가
				for(int j = i - 4; j <= i - 1; j++) {
					if(j < 0) {
						continue;
					}

					if(j + 4 >= lastIdx) {
						break;
					}

					// 몰드에 추가 
					int max = 0;

					for(int k = j; k < j + 5; k++) {
						max = Math.max(max, wall[k]);
					}

					mold[max - wall[j]][max - wall[j + 1]][max - wall[j + 2]][max - wall[j + 3]][max - wall[j + 4]]++;	
				}
				
				return (i + 1);
			}
		}

		return -1;
	}
}