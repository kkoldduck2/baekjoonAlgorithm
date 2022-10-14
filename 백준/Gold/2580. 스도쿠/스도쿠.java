import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[9][9];
	static List<int[]> blank;
	
	static boolean isPossible(int x, int y, int num) {
		for(int i=0; i<9; i++) {
			if(map[x][i] == num) {
				return false;
			}
			if(map[i][y] == num) {
				return false;
			}
		}
		
		int boxX = x/3*3;
		int boxY = y/3*3;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				int nx = boxX+i;
				int ny = boxY+j;
				
				if(map[nx][ny] == num) {
					return false;
				}
			}
		}
		
		return true;
	}
	static void printMap() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	// blank.get(idx)에 해당하는 칸에 들어갈 숫자를 정한다. (0~idx-1까지는 이미 정해졌다고 생각)
	static boolean dfs(int blank_idx) {
		if(blank_idx == blank.size()) {
			printMap();
			return true;
		}
		
		int x = blank.get(blank_idx)[0];
		int y = blank.get(blank_idx)[1];
		for(int i=1; i<=9; i++) {
			if(isPossible(x, y, i)) {
				map[x][y] = i;
				boolean rst = dfs(blank_idx+1);
				if(rst) return true;
				map[x][y] = 0;
			}
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		blank = new ArrayList<>();
		
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num == 0) {
					blank.add(new int[] {i, j});
				}
			}
		}
		
		if(blank.size()>0) {
			dfs(0);
		}
	}
}
