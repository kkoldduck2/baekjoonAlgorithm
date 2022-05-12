import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 *  A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하라
 */
public class Main {
	/**
	 * b[L...R]에서 x 미만의 수(x보다 작은 수) 중 제일 오른쪽 인덱스를 return 하는 함수 
	 * 그런게 없다면 L-1을 return 한다. 
	 */
	static int lower_bound(int[] b, int l, int r, int x) {
		int result = l-1;
		
		while(l <= r) {
			int mid = (l+r)/2;
			if(b[mid] < x) {
				result = mid;
				l = mid+1;
			}else {
				r= mid-1;
			}
		}
		
		return result+1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] a = new int[n];
			int[] b = new int[m];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(b);
			int cnt = 0;
			for(int i=0; i<n; i++) {
				cnt += lower_bound(b, 0, b.length-1, a[i]);
			}
			
			System.out.println(cnt);
		}
	}
}
