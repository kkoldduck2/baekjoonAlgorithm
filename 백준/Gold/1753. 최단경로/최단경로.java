
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1753
class Edge{
	int to;
	int weight;
	public Edge(int to, int w) {
		this.to = to;
		this.weight = w;
	}
	
}
class Pair implements Comparable<Pair>{
	int num;
	int d;
	public Pair(int num, int d) {
		this.num = num;
		this.d = d;
	}
	
	@Override
	public int compareTo(Pair o) {
		return d - o.d;
	}
}
public class Main {
	static int inf = 200000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());  // 정점의 개수 (1 <= v <= 20,000)
		int e = Integer.parseInt(st.nextToken());	// 간선의 개수 (1<= e <= 300,000)
		int k = Integer.parseInt(br.readLine());	// 시작 정점의 번호
		
		// 각 간선을 나타내는 세 개의 정수 (u,v,w)가 순서대로 주어진다. u에서 v로 가는 가중치 w 인 간선이 존재한다는 뜻 w는 10이하의 자연수
		// 이때 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수 있다. 
		int[] d = new int[v+1]; // 출발지 -> 모든 노드로의 최단 경로  d[i] : 출발지 ~ i까지 최단경로
		boolean[] c = new boolean[v+1];	// 최단 거리를 이미 알아낸 노드들의 집합
		
		
		// 데이터 저장 
		List<Edge>[] graph = new List[v+1];	// create array    graph[i] :  i와 연결된 모든 에지 list 저장
		
		for(int i=1; i<=v; i++) {
			graph[i] = new ArrayList<Edge>();
			d[i] = inf;
		}
		
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b,w));
		}
		
		d[k] = 0;
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();	
		q.add(new Pair(k, d[k]));
		while(!q.isEmpty()) {
			Pair s = q.poll();
			c[s.num] = true;	// S에 포함을 시켜줌
			for(Edge eg : graph[s.num]) {	// s와 인접한 다른 노드(최단 경로가 정해지지 않은)들의 d값을 갱신
				
					if(d[eg.to] > d[s.num]+eg.weight) {
						d[eg.to] = d[s.num]+eg.weight;
						q.add(new Pair(eg.to, d[eg.to]));
					}
				
			}
		}
		
		for(int i=1; i<=v; i++) {
			if(d[i]==inf) {
				System.out.println("INF");
			}else {
				System.out.println(d[i]);
			}
		}
	}
}
