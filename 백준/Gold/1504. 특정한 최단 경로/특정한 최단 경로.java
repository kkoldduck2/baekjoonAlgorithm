import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * 방향성이 없는 그래프
 * 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동
 * - 임의로 주어진 두 정점은 반드시 통과 (u, v)
 * - 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다
 *	
 * solution 
 * s -> u 까지 최단 거리 d1
 * u -> v 까지 최단 거리 d2
 * v -> d 까지 최단 거리 d3
 * d = d1 + d2 + d3
 * 
 */
public class Main {
	static int n;
	static int inf = 200000000;
	static class Edge{
		int dest;
		int w;
		public Edge(int dest, int w) {
			this.dest = dest;
			this.w = w;
		}
	}
	
	static class Pair implements Comparable<Pair>{
		// 정점 num까지 최소 거리 (shortestDist)
		int num;
		int shortestDist;
		
		public Pair(int num, int shortestDist) {
			this.num = num;
			this.shortestDist = shortestDist;
		}

		@Override
		public int compareTo(Pair o) {
			return this.shortestDist - o.shortestDist;
		}
		
	}
	
	public static int getShortestDistance(int s, int d, List<Edge>[] graph) {
		int[] dp = new int[n+1]; // dp[i] = 출발지부터 i까지 최단 거리
		//boolean[] check = new boolean[n+1]; // 방문 여부
		
		for(int i=1; i<n+1; i++) {
			dp[i] = inf;
		}
		
		dp[s] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(s, dp[s]));
		
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			//check[u.num]  = true; // 최단 거리 확정
			
			// relaxing
			for(Edge eg : graph[u.num]) {
				if(dp[eg.dest] > dp[u.num]+eg.w) {
					dp[eg.dest] = dp[u.num]+eg.w;
					pq.add(new Pair(eg.dest, dp[eg.dest]));
				}
			}
		}
		
		return dp[d];
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int e = sc.nextInt();
		List<Edge>[] graph = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		for(int i=0; i<e; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));
		}
		
		// 반드시 지나야 하는 정점
		int u = sc.nextInt();
		int v = sc.nextInt();
		
		// s -> u 최단 경로
		int d1 = getShortestDistance(1, u, graph);
		
		// u -> v 최단 경로
		int d2 = getShortestDistance(u, v, graph);
        
        // v -> d 최단 경로
		int d3 = getShortestDistance(v, n, graph);
        
        long ans1 = d1 + d2 + d3;
		
        // s -> u 최단 경로
		int d4 = getShortestDistance(1, v, graph);
		// v -> u 최단 경로 
		int d5 = getShortestDistance(v, u, graph);
		// u -> n 최단 경로 
		int d6 = getShortestDistance(u, n, graph);
        
        long ans2 = d4 + d5 + d6;
		
		long ans = Math.min(ans1, ans2);
		if(ans >= inf) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
		
	}
}
