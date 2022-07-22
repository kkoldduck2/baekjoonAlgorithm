import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int sum = 0;
		int idx = -1;
		for(int i=n-1; i>=0; i--) {
			if(arr[i]<0) {
				idx = i;
				break;
			}
			if(i==0) {
				sum += arr[i];
			}else {
				// arr[i]를 arr[i-1]과 곱할 것인가?
				// 곱하는 경우 : arr[i]*arr[i-1]>0 or arr[i]==0 , i--
				// 곱하지 않는 경우 
				if(arr[i]*arr[i-1]>(arr[i]+arr[i-1]) || arr[i]==0) {
					sum += arr[i]*arr[i-1];
					i--;
				}else {
					sum += arr[i];
				}
			}
		}
		
		// 음수 수열에 대해서 
		if(idx != -1) {
			for(int i=0; i<=idx; i++) {
				if(i==idx) {
					sum += arr[i];
				}else {
					if(arr[i]*arr[i+1]>(arr[i]+arr[i+1])) {
						sum += arr[i]*arr[i+1];
						i++;
					}else {
						sum += arr[i];
					}
				}
			}
		}
		System.out.println(sum);
	}
}
