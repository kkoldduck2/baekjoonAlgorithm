import java.util.Scanner;

public class Main {
	static int ans;
	static int n;
	static int s;
	static int[] arr;
	
	public static void backTracking(int depth, int sum) {
		
		if(depth == n) {
			return;
		}
		
		if(sum+arr[depth] == s) {
//			System.out.println("depth: "+depth+": chosen = "+chosen);
			ans ++;
//			return;
		}
		
		// depth를 포함함
		backTracking(depth+1, sum+arr[depth] );
		
		// depth를 포함하지 않음
		backTracking(depth+1, sum);
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		s = sc.nextInt();
		arr = new int[n];
		ans = 0;
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		backTracking(0, 0);
		
		System.out.println(ans);
	}
}
