import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 수빈 : x-1, x+1 -> 1초, 2*x -> 0초
 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후 인가.
 *
 */
public class Main {
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
	
	static Edge move(int i, int now, int cost) {
		switch(i) {
			case 0:
				return new Edge(now+1, cost+1);
			case 1:
				return new Edge(now-1, cost+1);
			case 2:
				return new Edge(now*2, cost);
		}
		
		return null;
	}
	static int maxVal = 200001;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());		// 수빈이 현재 위치
		int k = Integer.parseInt(st.nextToken());		// 동생 위치
		int[] dist = new int[maxVal];		// 출발지(n)에서 i번째 노드까지 최단거리 
		Arrays.fill(dist, maxVal);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int i=0; i<3; i++) {
			Edge init = move(i,n,0);
            if(init.to < 0 || init.to >= maxVal) continue;
			pq.add(init);
			dist[init.to] = init.cost;
		}
		
		dist[n] = 0;
		// 다익스트라
		while(!pq.isEmpty()) {
			Edge e = pq.poll();		// 최단거리 정해짐
//			System.out.println(e.to+", "+e.cost);
			
			for(int i=0; i<3; i++) {
				Edge next = move(i, e.to, e.cost);
				
				if(next.to < 0 || next.to >= maxVal) continue;
				
				if(dist[next.to] > next.cost) {
					dist[next.to] = next.cost;
					pq.add(next);
				}
			}
		}
		
		// 최단거리
		System.out.println(dist[k]);
	}
}
