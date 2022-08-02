
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[] dx = {1,-1, 0, 0};
	static int[] dy = {0, 0, 1,-1};
	
	static class Node{
		int x;
		int y;
		int dist;
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=M) return true;
		return false;
	}
	
	static int bfs() {
		Queue<Node> q = new LinkedList<>();
		int[][] visit = new int[N][M];   // visit[i][j] : 0,0에서 i,j까지의 거리 
		
		q.add(new Node(0,0,1));
		int answer = 0;
		
		while(!q.isEmpty()) {
			Node now = q.remove();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(!isOutOfBound(nx, ny) && map[nx][ny]==1 &&visit[nx][ny]==0) {
					q.add(new Node(nx, ny, now.dist+1));
					visit[nx][ny] = now.dist+1;
				}
			}
		}
		
		return visit[N-1][M-1];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			String[] sarr = str.split("");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(sarr[j]);
			}
		}
		
		int answer = bfs();
		System.out.println(answer);
	}
}
