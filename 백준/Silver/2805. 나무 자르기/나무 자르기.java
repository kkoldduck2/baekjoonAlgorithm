import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 원래 문제 : 원하는 길이 M만큼을 얻을 수 있는 최내 높이는 얼마인가?
 * 뒤집은 문제 : 어떤 높이로 잘랐을 때, 원하는 길이 M만큼을 얻을 수 있는가?  Yes/No
 *
 */
public class Main {
	static int[] arr;
	static int m;
	static int n;
	
	static boolean determination(int h) {
		long sum = 0;
		for(int i=0; i<n; i++) {
			if(arr[i] > h) {
				sum += (arr[i]-h);
			}
		}
		
		return sum >= m;
	}
	static long solution() {
		Arrays.sort(arr);
		
		long l = 0;
		long r = 2000000000;
		long answer = 0;
		
		while(l<=r) {
			long mid = (l+r)/2;
			if(determination((int)mid)) {
				answer = mid;
				l = mid+1;
			}else {
				r = mid-1;
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());		// 나무 수
		m = Integer.parseInt(st.nextToken());		// 필요한 나무 길이 
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long h = solution();
		System.out.println(h);
	}
}
