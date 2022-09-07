import java.util.*;
class Solution {
    static List<Edge>[] graph;
	static class Edge {
		int to;
		int w;
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	
	private static int[] dijkstra(int n, int[] gates, int[] summits) {
		Queue<Edge> q = new LinkedList<>();
		int[] intensity = new int[n+1];	// intensity[i] : i번 노드까지 모든 출발지 -> 모든 노드까지의 모든 경로 중에서 최소 intensity가 담긴다. 
		
		Arrays.fill(intensity, Integer.MAX_VALUE);
		
		// 출입구 전부를 큐에 넣음
		for(int gate : gates) {
			q.add(new Edge(gate, 0));		// 이때 가중치를 0으로 넣음. 의미적으로 가중치가 아니라 intensity
			intensity[gate] = 0; 
		}
		
		while(!q.isEmpty()) {
			Edge now = q.remove();
			
			if(now.w < intensity[now.to]) continue;	// pruning
			
			for(Edge next : graph[now.to]) {
				// next.to까지의 최소 intensity 갱신
				int dis = Math.max(intensity[now.to], next.w);
				if(intensity[next.to] > dis) {
					intensity[next.to] = dis;
					q.add(new Edge(next.to, dis));
				}
			}
		}
		
		int rstSummit = Integer.MAX_VALUE;
		int rstIntensity = Integer.MAX_VALUE;
		
		Arrays.sort(summits);
		
		for(int summit : summits) {
			if(intensity[summit] < rstIntensity) {
				rstSummit = summit;
				rstIntensity = intensity[summit];
			}
		}
		
		return new int[] {rstSummit, rstIntensity};
	}
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        boolean[] isGate = new boolean[n+1];
		boolean[] isSummit = new boolean[n+1];
		for(int i=0; i<gates.length; i++) {
			isGate[gates[i]] = true;
		}
		for(int i=0; i<summits.length; i++) {
			isSummit[summits[i]] = true;
		}
		
        // 그래프 만들기
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<paths.length; i++) {
        	int u = paths[i][0];
        	int v = paths[i][1];
        	int w = paths[i][2];
        	
        	if(isGate[u] || isSummit[v]) {
        		graph[u].add(new Edge(v, w));
        	}else if(isGate[v] || isSummit[u]) {
        		graph[v].add(new Edge(u,w));
        	}else {
        		graph[u].add(new Edge(v, w));
        		graph[v].add(new Edge(u,w));
        	}
        }
        
        return dijkstra(n, gates, summits);
    }
}