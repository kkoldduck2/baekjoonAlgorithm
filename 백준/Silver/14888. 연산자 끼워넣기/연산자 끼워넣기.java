import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int[] opCnt;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	
	static int calculate(int a, int b, int i) {
		switch(i) {
			case 0:
				return a+b;
			case 1:
				return a-b;
			case 2:
				return a*b;
			case 3:
				return a/b;
		}
		return 0;
	}
	
	static void dfs(int rst, int k) {
		if(k==N) {
			max = Math.max(max, rst);
			min = Math.min(min, rst);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(opCnt[i]>0) {
				opCnt[i]--;
				// dfs
				int newRst = calculate(rst, arr[k], i);
				dfs(newRst, k+1);
				opCnt[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		opCnt = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			opCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(arr[0], 1);
		System.out.println(max);
		System.out.println(min);
	}
}
