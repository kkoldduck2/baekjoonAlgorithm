import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static char[][] map;
	static int[] dr = {-1, 0, 1};		// 오른쪽 위 대각선부터 파이프를 놓는다. 
	// dc는 항상 1 (항상 오른쪽으로만 움직임)
	static int cnt = 0;
	
	static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=r || y<0 || y>=c) {
			return true;
		}
		return false;
	}
	static boolean dfs(int x, int y) {
		if(y==c-1) {
			cnt ++;
			return true;
		}
		
		boolean flag = false;
		for(int i=0; i<3; i++) {
			int nr = x+dr[i];
			int nc = y+1;
			if(!isOutOfBound(nr, nc) && map[nr][nc]=='.') {
				map[nr][nc]='X';
				flag = dfs(nr, nc);
				if(flag) break;				// 길 찾았으면 끝냄
			}
		}
		
		return flag;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		
		for(int i=0; i<r; i++) {
			String input = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		for(int i=0; i<r; i++) {
			dfs(i,0);
		}
		
		System.out.println(cnt);
	}
}
