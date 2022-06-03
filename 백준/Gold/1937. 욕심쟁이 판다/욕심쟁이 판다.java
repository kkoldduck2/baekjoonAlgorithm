import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] dp;	// dp[i][j] : i,j 위치부터 갈 수 있는 최대 칸
	static int n;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int maxResult = 1;
	
	static boolean isOutOfBound(int r, int c) {
		if(r<0 || c<0 || r>=n || c>=n) return true;
		return false;
	}
	
	// r,c 부터 출발할 때 갈 수 있는 최대 경로
	static int dfs(int r, int c) {
		if(dp[r][c] != -1) return dp[r][c];
		
		int maxDist = 1;
		int now = map[r][c];
		
		for(int i=0; i<4; i++) {
			int nr = r+dx[i];
			int nc = c+dy[i];
			if(!isOutOfBound(nr, nc) && now < map[nr][nc]) {
				maxDist = Math.max(maxDist, dfs(nr, nc)+1);
			}
		}
		
		dp[r][c] = maxDist;
		maxResult = Math.max(maxResult, maxDist);
		return maxDist;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];
		
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(dp[i][j]==-1) {
					dfs(i, j);
				}
			}
		}
		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				maxResult = Math.max(maxResult, dp[i][j]);
//			}
//		}
		
		System.out.println(maxResult);
	}
}
