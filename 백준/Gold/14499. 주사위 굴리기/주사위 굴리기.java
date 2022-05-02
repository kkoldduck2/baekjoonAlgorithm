import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 주사위가 굴러갈 때마다 각 면의 상대 위치가 달라짐 
 * 예) 어떤 면의 북쪽 방향이 변함 
 * 따라서 매 주사위가 이동할 때마다 주사위 상태를 update 해줘야 함
 */
public class Main {
	static int[] dice= new int[6];		// 주사위 상태, 각 인덱스는 위치를 가리킴
	static int[][] map;
	static int N;
	static int M;
	static int K;
	static int x;
	static int y;
	/**
	 * dice[0] : 윗면 *
	 * dice[1] : 북
	 * dice[2] : 동
	 * dice[3] : 서
	 * dice[4] : 남
	 * dice[5] : 바닥면 *
	 */
	public static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=M) {
			return true;
		}
		return false;
	}
	
	// 이동 가능한지 검사 후, 이동 가능하면 현재 위치 update
	public static boolean canMove(int dir) {
		boolean flag = true;
		switch(dir) {
			case 1:	// 동
				if(isOutOfBound(x, y+1)) {
					flag = false;
				}else {
					y = y+1;
				}
				break;
			case 2:	// 서
				if(isOutOfBound(x, y-1)) {
					flag = false;
				} else {
					y = y-1;
				}
				break;
			case 3:	// 북
				if(isOutOfBound(x-1, y)) {
					flag = false;
				} else {
					x = x-1;
				}
				
				break;
			case 4:	// 남
				if(isOutOfBound(x+1, y)){
					flag = false;
				} else {
					x = x+1;
				}
				
				break;
			default:
				break;
		}
		return flag;
	}
	// 주사위 상태를 변화 시키고, 윗면을 반환
	public static int rollingDice(int dir) {
		int temp;
		switch(dir) {
			case 1:	// 동
				temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[5];
				dice[5] = dice[2];
				dice[2] = temp;
				break;
			case 2:	// 서
				temp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[5];
				dice[5] = dice[3];
				dice[3] = temp;
				
				break;
			case 3:	// 북
				temp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[4];
				dice[4] = temp;
				break;
			case 4:	// 남
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5];
				dice[5] = dice[1];
				dice[1] = temp;
				break;
			default:
				break;
		}
		
		if(map[x][y]==0) {
			map[x][y] = dice[5];
		}else {
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
		return dice[0];
			
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 세로 크기
		M = Integer.parseInt(st.nextToken());	// 가로 크기
		
		x = Integer.parseInt(st.nextToken());  // 시작 x 좌표
		y = Integer.parseInt(st.nextToken());  // 시작 y 좌표
		
		K = Integer.parseInt(st.nextToken());  // 명령의 개수
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] move = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		String result = "";
		
		for(int i=0; i<K; i++) {
			int dir = move[i];
			if(!canMove(dir)) continue;
			
			// 이동 가능할 경우에만 명령 수행 
			int up = rollingDice(dir);
			System.out.println(up);
//			result += up;
		}
//		System.out.println(result);
	}
}
