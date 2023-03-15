
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// true : 이김
	// false : 짐
	static int[] dx = {0, 1, 1, 1};
	static int[] dy = {1, -1, 0, 1};
	static int n = 19;
	static int[][] map;
	static boolean[][][] checked;
	
	static boolean dfs(int color, int r, int c, int cnt, int dir) {
		if(cnt >= 6) {
			return false;
		}
		
		// 같은 방향으로 이동했을 때 같은 색깔이 또 있는가?
		int nr = r + dx[dir];
		int nc = c + dy[dir];
		
		if(nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] == color) {
			checked[nr][nc][dir] = true;
			return dfs(color, nr, nc, cnt+1, dir);
		}
		
		// 더 이상 갈 수 없음
		if(cnt == 5) {
			return true;
		}
		
		return false;
	}
	
	static int[] solution(int[][] map) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] != 0) {
					// 근처에 같은 숫자 있는지 확인
					for(int k=0; k<4; k++) {
						int r = i + dx[k];
						int c = j + dy[k];
						
						// 연속해서 같은 돌 존재 
						if(r >= 0 && r < n && c >= 0 && c < n) {
							if(map[i][j]==map[r][c] && !checked[i][j][k]) {
								int color = map[i][j];
								checked[i][j][k] = true;
								checked[r][c][k] = true;
								
								if(dfs(color, r, c, 2, k)) {
									if(k == 1) {		
										int ax = i + dx[k]*4;
										int ay = j + dy[k]*4;
										return new int[] {color, ax+1, ay+1};
									}else {
										return new int[] {color, i+1, j+1};
									}
									
								}
							}
						}
						
					}
				}
			}
		}
		
		return new int[] {0};
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[n][n];
		checked = new boolean[n][n][4];	// 가로위치, 세로위치, 방향
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
			}
		}
		
		int[] result = solution(map);
		if(result[0] == 0) {
			System.out.println(0);
		}else {
			System.out.println(result[0]);
			System.out.println(result[1]+" "+result[2]);
		}
	}
}
