import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 출발 : 가장 왼쪽 위
 * 도착 : 가장 오른쪽 아래
 * 항상 낮은 지점으로만 이동해서 도착지까지 갈 수 있는 경우의 수 
 */
public class Main {
	static int m;
	static int n;
	static int[][] map;
	static int[][] dp;	// (x,y)까지 갈 수 있는 경로의 수  -> (x,y) 부터 갈 수 있는 경로의 수 
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][] visited;
	
	public static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=m || y<0 || y>=n) {
			return true;
		}
		return false;
	}
	// 현재 위치에서 갈 수 있는 곳 탐색

//	public static void dfs(int x, int y) {
//		dp[x][y]+=1;
//		
//		for(int i=0; i<4; i++) {
//			int nx = x + dx[i];
//			int ny = y + dy[i];
//			if(!isOutOfBound(nx, ny) && map[nx][ny] < map[x][y]) {
//				dfs(nx, ny);
//			}
//		}
//	}
	
	public static int dfs(int x, int y) {
		if(x == m-1 && y == n-1) {
			return 1;
		}
		
		// 이미 방문했어도 dp값이 0 일 수 있음
		if(dp[x][y] != 0 || visited[x][y]) {
			return dp[x][y];
		}
		visited[x][y] = true;
		
		int cnt = 0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isOutOfBound(nx, ny) && map[nx][ny] < map[x][y]) {
				int temp = dfs(nx, ny);
				cnt += temp;
			}
		}
		dp[x][y] = cnt;
		return cnt;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new int[m][n];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[m][n];
		visited = new boolean[m][n];
		
		dfs(0,0);
		
		System.out.println(dp[0][0]);
	}
	
	public static void printMap() {
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(dp[i][j]);
			}
			System.out.println();
		}
		System.out.println("==================");
	}
}
