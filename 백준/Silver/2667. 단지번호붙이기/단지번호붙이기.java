import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static boolean[][] visited;
	
	static boolean isOutOfBound(int r, int c) {
		if(r<0 || c<0 || r>=n || c>=n) {
			return true;
		}
		return false;
	}
	static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		visited[r][c] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] out = q.remove();
			for(int i=0; i<4; i++) {
				int nr = out[0] + dr[i];
				int nc = out[1] + dc[i];
				
				if(!isOutOfBound(nr, nc) && map[nr][nc]==1 && !visited[nr][nc]) {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = true;
					cnt ++;
				}
			}
		}
		
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			String[] arr = s.split("");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(arr[j]);
			}
		}
		
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					int cnt = bfs(i, j);
					list.add(cnt);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for(int x : list) {
			System.out.println(x);
		}
	}
}
