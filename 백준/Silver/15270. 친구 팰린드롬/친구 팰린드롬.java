
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer>[] graph;
	static boolean[] paired;
	static int maxCnt = 0;
	static void dfs(int num, int cnt) {
		if(num == N+1) {
//			System.out.println(Arrays.toString(paired));
			maxCnt = Math.max(cnt, maxCnt);
			return;
		}
		
		// 이미 짝으로 결정됨
		if(paired[num]) {
			dfs(num+1, cnt);
			return;
		}
		
		// 짝을 안만듦
		dfs(num+1, cnt);
		
		paired[num] = true;
		// num과 짝할 수 있는 얘들
		for(int friend : graph[num]) {
			if(paired[friend]) continue;
			
			paired[friend] = true;
			dfs(num+1, cnt+2);
			paired[friend] = false;
		}
		paired[num] = false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		paired = new boolean[N+1];
		
		graph = new List[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		dfs(1, 0);
		
		if(maxCnt < N && maxCnt%2==0) {
			maxCnt += 1;
		}
		System.out.println(maxCnt);
	}
}
