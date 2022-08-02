import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static char[][] map;
	static int[][] waterMap;		// waterMap[i][j] : i,j 위치에 water가 차는 시점
	static boolean[][] visit;
	static int ex;
	static int ey;
	static class Node {
		int x;
		int y;
		int dist;
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
		public String toString() {
			return x+", "+y+": "+dist;
		}
	}
	static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=R || y<0 || y>=C) return true;
		return false;
	}
	
	static int bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sr,sc,0));
		visit[sr][sc] = true;
		int answer = 0;
		
		while(!q.isEmpty()) {
			Node now = q.remove();
			if(now.x == ex && now.y == ey) {
				answer = now.dist;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(!isOutOfBound(nx, ny) && (map[nx][ny]=='.' || map[nx][ny]=='D') && !visit[nx][ny] && waterMap[nx][ny]>now.dist+1) {
					q.add(new Node(nx, ny, now.dist+1));
					visit[nx][ny] = true;
				}
			}
		}
		
		return answer;
	}
	
	static void waterbfs() {
		Queue<Node> water = new LinkedList<>();
		
		// 시작 지점 담기 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='*') {
					water.add(new Node(i, j, 0));
					waterMap[i][j] = 0;
				}
			}
		}
		
		// waterMap 채우기 
		while(!water.isEmpty()) {
			Node now = water.remove();
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(!isOutOfBound(nx, ny) && waterMap[nx][ny]==Integer.MAX_VALUE && map[nx][ny]=='.' ) {
					water.add(new Node(nx, ny, now.dist+1));
					waterMap[nx][ny] = now.dist+1;
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		waterMap = new int[R][C];
		visit = new boolean[R][C];
		
		
		int sr = 0;
		int sc = 0;
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				if(s.charAt(j)=='S') {
					sr = i;
					sc = j;
				}
				
				if(s.charAt(j)=='D') {
					ex = i;
					ey = j;
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				waterMap[i][j] = Integer.MAX_VALUE;
			}
		}
		
		waterbfs();
		int answer = bfs(sr, sc);
		if(answer==0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(answer);
		}
	}
}
