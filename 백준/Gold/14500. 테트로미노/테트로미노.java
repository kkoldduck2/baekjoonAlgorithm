import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][][] tetromino;
	
	static int[][] rotate(int[][] tetromino){
		for(int i=0; i<4; i++) {
			int tmpy = tetromino[i][1];
			tetromino[i][1] = tetromino[i][0];
			tetromino[i][0] = tmpy*-1;
		}
		return tetromino;
	}
	
	static int[][] symmetry_lr(int[][] tetromino){
		for(int i=0; i<4; i++) {
			tetromino[i][1] = tetromino[i][1]*-1;
		}
		return tetromino;
	}
	
	static int[][] symmetry_ud(int[][] tetromino){
		for(int i=0; i<4; i++) {
			tetromino[i][0] = tetromino[i][0]*-1;
		}
		return tetromino;
	}
	
	static boolean isOutOfBound(int x, int y, int[][] tetromino) {
		for(int i=0; i<4; i++) {
			int nx = x + tetromino[i][0];
			int ny = y + tetromino[i][1];
			
			if(nx<0 || ny<0 || nx>=N || ny>=M) {
				return true;
			}
		}
		return false;
	}
	
	static int getSum(int x, int y, int[][] tetromino) {
		int sum = 0;
		for(int i=0; i<4; i++) {
			int nx = x + tetromino[i][0];
			int ny = y + tetromino[i][1];
			
			sum += map[nx][ny];
		}
		
		return sum;
	}
	static void printMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void printTetromino(int[][] rotated_tetromino) {
		for(int i=0; i<4; i++) {
			System.out.print(Arrays.toString(rotated_tetromino[i])+", ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		printMap();
		
		tetromino = new int[5][4][2];
		tetromino[0] = new int[][] {{0,0}, {0,1}, {0,2}, {0,3}};
		tetromino[1] = new int[][] {{0,0}, {1,0}, {0,1}, {1,1}};
		tetromino[2] = new int[][] {{0,0}, {1,0}, {2,0}, {2,1}};
		tetromino[3] = new int[][] {{0,0}, {1,0}, {1,1}, {2,1}};
		tetromino[4] = new int[][] {{0,0}, {0,1}, {1,1}, {0,2}};
		
		int maxVal = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 테트로미노를 하나씩 좌우 대칭, 위아래 대칭, 회전 시키면서 최대 합을 구한다. 
				for(int k=0; k<5; k++) {
					// 회전
					for(int r=0; r<4; r++) {
//						printTetromino(tetromino[k]);
						int[][] rotated_tetromino = rotate(tetromino[k]);
//						printTetromino(rotated_tetromino);
						// 대칭 
						for(int s1=0; s1<2; s1++) {
							rotated_tetromino = symmetry_lr(rotated_tetromino);
							for(int s2=0; s2<2; s2++) {
								rotated_tetromino = symmetry_ud(rotated_tetromino);
								if(!isOutOfBound(i, j, rotated_tetromino)) {
									int sum = getSum(i, j, rotated_tetromino);
									maxVal = Math.max(maxVal, sum);
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(maxVal);
		
	}
}
