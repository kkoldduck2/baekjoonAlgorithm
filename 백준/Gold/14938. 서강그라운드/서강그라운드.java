
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] items;
	static List<Edge>[] graph;
	static int n;
	static int m;
	static int r;
	static int inf = 1501;
	
	static class Edge implements Comparable<Edge>{
		int dest;
		int cost;
		public Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int getMaximumItem(int start) {
		int[] dist = new int[n+1];	// dist[i] : start ~ i까지 최단 거리
		for(int i=0; i<=n; i++) {
			dist[i] = inf;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.add(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.remove();
			
			for(Edge next : graph[now.dest]) {
				if(dist[next.dest] > dist[now.dest] + next.cost) {
					dist[next.dest] = dist[now.dest] + next.cost;
					pq.add(new Edge(next.dest, dist[now.dest] + next.cost));
				}
			}
		}
		
		int itemCnt = 0;
		for(int i=1; i<=n; i++) {
			if(dist[i] <= m) {
				itemCnt += items[i];
			}
		}
		return itemCnt;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());		// 지역 개수
		m = Integer.parseInt(st.nextToken());		// 수색 범위
		r = Integer.parseInt(st.nextToken());		// 길의 개수
		
		graph = new List[n+1];
		
		st = new StringTokenizer(br.readLine());
		items = new int[n+1];
		
		
		for(int i=1; i<=n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, w));
			graph[b].add(new Edge(a, w));
		}
		
		int maxResult = 0;
		for(int start=1; start<=n; start++) {
			int rst = getMaximumItem(start);
			maxResult = Math.max(maxResult, rst);
		}
		
		System.out.println(maxResult);
		
	}
}
