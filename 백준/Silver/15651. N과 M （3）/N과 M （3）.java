import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
/**
 * n,m이 주어졌을 때, 아래 조건을 만족하는 길이가 m인 수열을 모두 구하라 
 * - 1~N까지 자연수 중에서 M개를 고른 수열
 * - 같은 수를 여러 번 골라도 된다. 
 * 
 */
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
	static void dfs(int cnt, int[] selected) throws IOException {
		if(cnt==M) {
			// 현재까지 고른 얘들 출력 
			print(selected);
			return;
		}
		
		if(cnt>M) return;
		
		for(int i=1; i<=N; i++) {
			selected[cnt] = i;
			dfs(cnt+1, selected);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N  = sc.nextInt();
		M = sc.nextInt();
		
		int[] selected = new int[M];
		dfs(0, selected);
        bw.flush();
	}
}
