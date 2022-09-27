import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 불 번짐을 먼저 돌리고 그 다음에 지훈이가 이동할 수 있는 공간을 탐색하여 최단 경로로 탈출하면 된다. 
 *
 * 1. 주어진 맵에서 지훈이의 위치와 불들의 위치를 각 큐에 저장한다. 
 * 2. 지훈이가 탈출하거나 더 이상 움직일 수 없을 때까지 시뮬레이션을 돌린다.
 * 3. 탈출했다면 최단 경로를 반환하고, 탈출하지 못했다면 Impossible을 출력한다. 
 */
public class Main {
	static int R;
	static int C;
	static char[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static boolean isOutOfBound(int r, int c) {
		if(r<0 || r>=R || c<0 || c>=C) {
			return true;
		}
		return false;
	}
	static void printMap(int[][] temp) {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(temp[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("============");
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		Queue<int[]> jq = new LinkedList<>();
		Queue<int[]> fq = new LinkedList<>();
		
		int [][] jvisit = new int[R][C];
		int [][] fvisit = new int[R][C];

		for(int i=0; i<R; i++) {
			String row = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = row.charAt(j);
				
				if(map[i][j]=='J') {
					jq.add(new int[] {i, j, 1});
					jvisit[i][j] = 1;
				}else if(map[i][j]=='F') {
					fq.add(new int[] {i, j});
					fvisit[i][j] = 1;
				}
			}
		}
		
		// fire
		while(!fq.isEmpty()) {
			int[] cur = fq.remove();
			int curR = cur[0];
			int curC = cur[1];
			
			for(int i=0; i<4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				
				if(!isOutOfBound(nr, nc) && map[nr][nc]!='#' && fvisit[nr][nc]==0) {
					fvisit[nr][nc] = fvisit[curR][curC]+1;
					fq.add(new int[] {nr, nc});
				}
			}
		}
		
		// jihun
		while(!jq.isEmpty()) {
			int[] cur = jq.remove();
			int jr = cur[0];
			int jc = cur[1];
			
			for(int i=0; i<4; i++) {
				int nr = jr + dr[i];
				int nc = jc + dc[i];
				
				if(!isOutOfBound(nr, nc) && map[nr][nc]!='#' && jvisit[nr][nc]==0) {
					if( fvisit[nr][nc] == 0 || jvisit[jr][jc]+1 < fvisit[nr][nc]) {		// 추가 조건 
						jvisit[nr][nc] = jvisit[jr][jc]+1;
						jq.add(new int[] {nr, nc});
					}
				}
			}
		}
		
//		printMap(fvisit);
//		printMap(jvisit);
		
		int minTime = Integer.MAX_VALUE;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(i==0 || j==0 || i==R-1 || j==C-1) {
					if(jvisit[i][j]!=0) {
						minTime = Math.min(minTime, jvisit[i][j]);
					}
				}
			}
		}
		if(minTime == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(minTime);
		}
	}
}
