
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
/**
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
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
	
	// 조합
	static void dfs(int cnt, int[] selected, HashSet<Integer> hs) throws IOException {
		if(cnt == M) {
			print(selected);
			return;
		}
		
		// select[cnt]에 들어갈 값 선택
		for(int i=1; i<=N; i++) {
			if(!hs.contains(i)) {
				selected[cnt] = i;
				hs.add(i);
				dfs(cnt+1, selected, hs);
				hs.remove(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = sc.nextInt();
		M = sc.nextInt();
		HashSet<Integer> hs = new HashSet<>();
		 
		int[] selected = new int[M];
		dfs(0, selected, hs);
		bw.flush();
	}
}
