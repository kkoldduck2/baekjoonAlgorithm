import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 정수로 이뤄진 수열
 * 크기가 양수인 부분 수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하라
 *
 */
public class Main {
	static int n;
	static int s;
	static int[] arr;
	static int result=0;
	
	static void dfs(int rst, int k, int cnt) {
		if(k==n) {
			if(rst==s && cnt>0) {
				result++;
			}
			return;
		}
		
		dfs(rst+arr[k], k+1, cnt+1);	// arr[k]를 부분수열의 원소로 선택함
		dfs(rst, k+1, cnt);	// arr[k]를 부분수열의 원소로 선택 안함
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, 0);
		
		System.out.println(result);
	}
}
