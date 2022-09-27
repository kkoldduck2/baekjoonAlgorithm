
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 문제 이해하기 
 * - 2차원 배열에서 (i,j) ~ (x,y)까지 저장되어 있는 수들의 합을 구하는 프로그램 작성
 * 
 * 문제 접근 방법
 * 1. bruteforce  O(NM)
 * 2. prefix sum (누적합) 이용하기
 * 
 * 구현 배경 지식
 ** 누적 합
 *	- 앞에서부터 차례대로 누적된 합을 구해놓고 이를 이용해서 구간의 합을 구할 수 있다. 
 * 	- S[i] = A[1] + ... + A[i] , S[0] = 0
 * 	- 따라서 A[l]+...+A[r] = S[r] - S[l-1] 이다. 
 * 
 ** 누적 합 적용 상황
 *  -> i~j까지 합을 구하는 연산을 최대 M번 수행해야하는 경우 
 *
 *	누적합은 배열 A에 들어있는 값이 바뀌지 않는다는 점을 이용한다. 배열이 변하지 않으니 구간의 합도 변하지 않는다. 
 *  따라서 앞에서부터 차례대로 누적된 합을 미리 구해놓고 M번 연산 시 누적합을 이용해서 O(1) 시간 안에 구한다. 
 * 
 * 접근 방법을 적용한 코드 
 * 
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 행
		int M = Integer.parseInt(st.nextToken());	// 열
		
		int[][] a = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] s = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				s[i][j] = s[i][j-1] + a[i][j];
			}
		}
		
		int K = Integer.parseInt(br.readLine());	// 열
		
		
		for(int k=0; k<K; k++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int z=i; z<=x; z++) {
				sum += (s[z][y] - s[z][j-1]);
			}
			
			System.out.println(sum);
		}
	}
}
