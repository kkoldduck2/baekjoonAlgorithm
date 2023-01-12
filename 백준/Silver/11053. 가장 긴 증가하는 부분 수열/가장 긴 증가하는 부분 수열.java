
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
	// 시간 초과 O(n^2)
	static void solution1(int n, int[] arr, int[] dp) {
		// dp[i] : i부터 시작하는(i를 반드시 포함하는) 가장 긴 부분 수열
		dp[n-1] = 1;
		
		for(int i=n-2; i>=0; i--) {
			// dp[i]를 구해야 함. arr[j] > arr[i] (j>i) 인 dp[j] 중 가장 큰 값 + 1
			int max = 0;
			for(int j=i+1; j<n; j++) {
				if(arr[j] > arr[i]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max +1;
		}
		
		int answer = 0;
		for(int i=0; i<n; i++) {
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		int[] dp = new int[n];			// dp[i] : 자기 자신을 포함한 가장 
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		solution1(n, arr, dp);
		
	}
}
