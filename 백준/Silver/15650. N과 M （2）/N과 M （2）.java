
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static BufferedWriter bw;
	
	static void print(int[] selected) throws IOException {
		for(int i=0; i<M; i++) {
			bw.write(selected[i]+" ");
		}
		bw.newLine();
		return;
	}
	
	// k~N 중에서 하나 선택, cnt : 현재까지 선택한 개수
	static void dfs(int k, int cnt, int[] selected) throws IOException {
		if(cnt == M) {
			// 출력
			print(selected);
			return;
		}
		if(k>N) return;
		
		for(int i=k; i<=N; i++) {
			selected[cnt] = i;
			dfs(i+1, cnt+1, selected);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = sc.nextInt();
		M = sc.nextInt();
		int[] selected = new int[M];
		
		dfs(1, 0, selected);
		bw.flush();
	}
}
