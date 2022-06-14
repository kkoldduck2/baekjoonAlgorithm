import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static void printMap(int[][] map, int n) {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	static boolean isOutOfBound(int n, int x, int y) {
		if(x<=0 || y<=0 || x>n || y>n) return true;
		return false;
	}
	
	static int solution(int n, int k, int[][] map, char[] cdir) {
		int dirIdx = 2;
		int cnt = 0;
		// 머리 위치 
		int hx=1;
		int hy=1;
		
		// 꼬리 위치
		int tx=1;
		int ty=1;
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {1, 1});
		
		while(!q.isEmpty()) {
			cnt ++;
			
			int nx = hx+dx[dirIdx];
			int ny = hy+dy[dirIdx];
			
			if(isOutOfBound(n, nx, ny) || map[nx][ny] == -1) break;
			
			// 이동 가능
			q.add(new int[] {nx, ny});
			
			if(map[nx][ny]!=1) {   			// 사과가 없으면 꼬리가 위치한 칸을 비워준다. 
				map[tx][ty] = 0;
				q.remove();	// 기존 꼬리 제거 
				int[] nextTail = q.peek();
				tx = nextTail[0];
				ty = nextTail[1];
			}
			
			// 사과가 있으면 head만 이동시키면 됨 
			map[nx][ny] = -1;
			hx = nx;
			hy = ny;
			
			if(cnt <= 10000 && cdir[cnt]=='L') dirIdx=(dirIdx+3)%4;
			else if(cnt <= 10000 && cdir[cnt]=='D') dirIdx= (dirIdx+1)%4;
			
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());	// 맵 크기
		int k = Integer.parseInt(br.readLine());	// 사과 개수 
		
		int[][] map = new int[n+1][n+1];
		char[] cdir = new char[10001];
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = 1;		// 사과 존재
		}
		
		map[1][1] = -1; // 뱀 
		
		int l = Integer.parseInt(br.readLine());	// 뱀 변환 횟수 
		for(int i=0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			cdir[x] = c;
		}
		
		int answer = solution(n, k, map, cdir);
		System.out.println(answer);
	}
}
