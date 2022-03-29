import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *  input : 맵의 크기와 상태
 *  process : 좌, 우, 위, 아래 방향 중 1가지 골라서 x만큼 이동. 동전이 구멍에 빠지거나, 보드의 바깥으로 나가면 끝남
 *	output : 최대 이동 거리. 무한히 움직일 수 있다면 -1 출력
 *	
 *	solution
 *	- dfs로 현재 위치(r,c)에서 갈 수 있는 모든 경우의 수를 재귀적으로 구한다. 그 중에서 최대 값을 선택한다. => 시간초과
 *	 	i) 구멍에 빠지거나 보드의 바깥으로 나간다 => 현재까지 이동한 거리 return 
 *		ii) 무한 루프 (방문했던 곳을 또 방문 할 수 있음 ) => -1 return
 *	- 가지치기를 통해 굳이 탐색하지 않아도 되는 곳은 탐색하지 않도록 한다. 
 *		iii) dp에 현재 위치에 대해 저장된 값이 지금까지 온 cnt 보다 더 큰 경우 => 어차피 최대 이동 거리가 못 됨  
 *
 */
public class Main {
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int n;
	static int m;
	static char[][] map;
	static int[][] dp;
	static boolean isInfinite;
	static boolean[][] visited;
	static int max = 0;
	
	public static boolean isOutOfMap(int r, int c) {
		if(r<0 || r>=n || c<0 || c>=m) {
			return true;
		}
		return false;
	}
	
	// (r,c) : 현재 위치, visited : 지금까지 방문했던 경로, cnt : 지금까지 움직인 거리
	public static void dfs(int r, int c, int cnt) {
		// 현재까지 경로의 길이가 최대 값보다 크다면 -> 갱신
		if(cnt > max) {
			max = cnt;
		}
		
		dp[r][c] = cnt;	// cnt가 최대 값일 수 밖에 없으므로 캐싱
		
		int x = map[r][c]-'0';
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i]*x;
			int nc = c + dc[i]*x;
			
			if(!isOutOfMap(nr, nc) && map[nr][nc] != 'H') {
				// 무한 루프 체크
				if(visited[nr][nc]) {
					isInfinite = true;
					return;
				}
				
				// 가지치기. 방문하려는 곳이 현재 count보다 크면 굳이 방문하지 않는다. 
				if(dp[nr][nc] > cnt) {
					continue;
				}
				
				visited[nr][nc] = true;
				dfs(nr, nc, cnt+1);
				visited[nr][nc] = false;
				
			}
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		dp = new int[n][m];
		
		for(int i=0; i<n; i++) {
			String row = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = row.charAt(j);
			}
		}
//		printMap();
		
		visited = new boolean[n][m];
		visited[0][0] = true;
		dfs(0,0,1);


		if(isInfinite) {
			System.out.println(-1);
		}else {
			System.out.println(max);
		}
		
	}
	
	public static void printMap() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
