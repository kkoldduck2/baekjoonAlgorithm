
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
 * N명의 학생이 X번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고,
 * i번째 길을 지나는데 Ti(1~100)의 시간을 소비한다.
 * 
 * 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 단, 최단거리로 이동
 * N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라 
 *
 */
public class Main {
	static int n;
	static int m;
	static int x;
	static class Edge implements Comparable<Edge>{
		int to;
		int cost;
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int[] dijkstra(int start, List<Edge>[] graph) {
		int[] dist = new int[n+1];		// dp[i] : start에서 i번 정점까지 최단 거리
		for(int i=1; i<=n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[start] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		while(!pq.isEmpty()) {
			Edge temp = pq.remove();
			int now = temp.to;
			int nowCost = temp.cost;
			
			if(nowCost > dist[now]) continue;
			for(Edge e : graph[now]) {
				if(dist[e.to] > nowCost + e.cost) {
					dist[e.to] = nowCost + e.cost;
					pq.add(new Edge(e.to, nowCost + e.cost));
				}
			}
		}
		
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 노드 수
		m = Integer.parseInt(st.nextToken());	// 에지 수
		x = Integer.parseInt(st.nextToken());	// 목적지
		
		List<Edge>[] graphGo = new List[n+1];
		List<Edge>[] graphBack = new List[n+1];
		for(int i=1; i<=n; i++) {
			graphGo[i] = new ArrayList<>();
			graphBack[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graphBack[s].add(new Edge(e,w));
			graphGo[e].add(new Edge(s,w));
		}
		
		
		// 모든 지점 -> x
		int[] go = dijkstra(x, graphGo);
		
		// x -> 모든 지점
		int[] back = dijkstra(x, graphBack);
		
		int max = 0;
		for(int i=1; i<=n; i++) {
			int sum = go[i] + back[i];
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}
}
