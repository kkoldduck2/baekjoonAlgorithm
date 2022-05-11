import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 같은 양의 세 가지 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
 * 이 연구소에서는 같은 양의 세 가지 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다. 
 *
 */
public class Main {
	static long[] pick = new long[3];
	static long max = 3000000000L;
	
	// 하나를 미리 고르고(index) solution에서 다른 2가지를 고른다.
	public static void solution(long[] arr, int index) {
		int left = index+1;
		int right = arr.length-1;
		
		while(left < right) {
			long sum = arr[left] + arr[right] +arr[index];
			long absSum = Math.abs(sum);
			
			// 두 용액 갱신
			if(absSum < max) {
				pick[0] = arr[left];
				pick[1] = arr[right];
				pick[2] = arr[index];
				max = absSum;
			}
			
			if(sum > 0) {
				right --;
			}else {
				left ++;
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());		// 용액의 수
		long[] liquids = new long[n];		// 용액의 특성 값
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			liquids[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(liquids);
		
		for(int i=0; i<n-2; i++) {
			solution(liquids, i);
		}
		
		Arrays.sort(pick);
		
		for(int i=0; i<3; i++) {
			System.out.println(pick[i]+" ");
		}
		
	}
}
