
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;	// 도시 개수
	static int M;	// 도로 개수
	static int K;	// 최단 거리
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());	// 출발 도시 정보
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		List<Integer>[] graph = new List[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		visited[x] = true;
		dist[x] = 0;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				if(visited[next]) continue;
				q.add(next);
				dist[next] = dist[cur]+1;
				visited[next] = true;
			}
		}
		
		boolean flag = false;
		for(int i=1; i<=N; i++) {
			if(dist[i]==K) {
				flag = true;
				System.out.println(i);
			}
		}
		
		if (!flag) System.out.println(-1);
	}
}
