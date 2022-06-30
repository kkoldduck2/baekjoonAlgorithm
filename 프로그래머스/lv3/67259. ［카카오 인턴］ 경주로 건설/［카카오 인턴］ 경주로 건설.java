class Solution {
    static int[] dr = {1,0,-1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int n;
	static int[][][] dp;
	
	boolean isOutOfBound(int r, int c) {
		if(r>=n || c>=n || c<0 || r<0) return true;
		return false;
	}
	
	public void dfs(int r, int c, int cost, int dir, int[][] board) {
		if(r==n-1 && c==n-1) {
			// 최소 비용 계산 
			if(dp[r][c][dir]==0) {
				dp[r][c][dir] = cost;
			}else {
				dp[r][c][dir] = Math.min(dp[r][c][dir], cost);
			}
			return;
		}
		
		// 가지치기 -> 더 이상 탐색할 필요가 없는 경우 
		if(dp[r][c][dir] != 0 && dp[r][c][dir] < cost) {
			return;
		}
		
		dp[r][c][dir] = cost;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 방문했으면 board 값 2로 업데이트 할 것임
			if(!isOutOfBound(nr, nc) && board[nr][nc] == 0) {
				board[nr][nc] = 2;
				int updatedCost = Math.abs(dir-i)%2==1 ? cost+600 : cost+100;
				
				if(r==0 && c==0) {
					updatedCost = 100;
				}
				
				dfs(nr, nc, updatedCost, i, board);
				board[nr][nc] = 0;
			}
		}
	}
    
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        n = board.length;
        dp = new int[n][n][4];
        
        dfs(0,0,0,0,board);
        
        for(int i=0; i<4; i++) {
        	if(dp[n-1][n-1][i]!=0) {
        		answer = Math.min(answer, dp[n-1][n-1][i]);
        	}
        }
        return answer;
    }
}