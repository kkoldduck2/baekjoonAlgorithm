import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int D;
	static char[][] map;
	static List<Umbrella> umbrellas;
	static int minimum;
	static int[] end;

	static class Umbrella{
		int x;
		int y;
		boolean used;
		public Umbrella(int x, int y) {
			this.x = x;
			this.y =y;
			this.used = false;
		}
	}
	static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=N) {
			return true;
		}
		return false;
	}
	static int getDist(int[] from, int[] to) {
		int dist = Math.abs(from[0]-to[0]) + Math.abs(from[1]-to[1]);
		return dist;
	}
	
	static void dfs(int[] now, int h, int moveCnt, int umbrella) {
//		if(map[now[0]][now[1]] == 'E') {
//			minimum = Integer.min(moveCnt, minimum);
//			return;
//		}
		// 현재 위치에 우산 -> umbrella update
		if(map[now[0]][now[1]] == 'U') {
			umbrella = D;
		}
		
		// 도착 지점까지 갈 수 있음
		if(getDist(now, end) <= h+umbrella) {
			umbrella -= getDist(now, end);
			if(umbrella < 0) {
				h += umbrella;
			}
//			System.out.println("도착지점까지 이동 가능 -> 현재 위치 : "+Arrays.toString(now)+", 남은 체력 : "+h);
			int total_dist = moveCnt+getDist(now, end);
			minimum = Integer.min(total_dist, minimum);
			return;
		}
		
		// 못 감 -> umbrella 중 하나 거쳐서 가야 함
//		System.out.println("도착지까지 바로 못 감 -> 현재 위치 : "+Arrays.toString(now));
		for(Umbrella um : umbrellas) {
			int togo = getDist(now, new int[] {um.x, um.y});
			if(!um.used && togo <= h+umbrella) {
				um.used = true;
				int tmpUm = umbrella;
				int tmpH = h;
				
				umbrella -= togo;
				if(umbrella < 0) {
					h += umbrella;
					umbrella = 0;
				}
				
				dfs(new int[] {um.x, um.y}, h,moveCnt+togo, umbrella);
				
				// backtracking
				um.used = false;
				umbrella = tmpUm;
				h = tmpH;
			}
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken()); 	
		D = Integer.parseInt(st.nextToken());
		minimum = Integer.MAX_VALUE;
		map = new char[N][N];
		umbrellas = new ArrayList<>();
		
		int[] start = new int[2];
		end = new int[2];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				}
				
				if(map[i][j] == 'E') {
					end[0] = i;
					end[1] = j;
				}
				
				if(map[i][j] == 'U') {
					umbrellas.add(new Umbrella(i, j));
				}
			}
		}
		
		dfs(start, H, 0, 0);
		
		if(minimum == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(minimum);
		}
	}
}
