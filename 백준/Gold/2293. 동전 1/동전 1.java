
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * n가지 종류의 동전을 여러 번 사용하여 k원을 만들기 위한 경우의 수를 구하라
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());		// 동전 종류 개수 
		int k = Integer.parseInt(st.nextToken());		// 가치의 합
		
		int[] coins = new int[n];		// coins[i] : i번째 동전의 가치 
		
		for(int i=0; i<n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1]; 		// dp[i] : 가치의 합이 i가 되는 경우의 수 -> dp[k]를 구하면 됨
		
		// dp[i] = dp[i] + dp[i-coins[j]]  (0 <= j < n)
		dp[0] = 1;
//		for(int i=0; i<n; i++) {
//			dp[coins[i]] = 1;
//		}
		
		// 중복을 피해야 함!
//		for(int i=1; i<=k; i++) {
//			for(int j=0; j<n; j++) {
//				if(i-coins[j] >= 0) {
//					dp[i] += dp[i-coins[j]];
//				}
//			}
//		}
		
		/**
		 * for문 순서 주의!
		 */
		for(int i=0; i<n; i++) {
			for(int j=1; j<=k; j++) {
				if(j-coins[i] >= 0) {
					dp[j] += dp[j-coins[i]];
				}
			}
		}
		
		System.out.println(dp[k]);
		
	}
}
