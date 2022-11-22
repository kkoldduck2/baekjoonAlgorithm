
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;	// 파티장 크기
	static int M;	// 서비스를 요청한 손님 수
	static int[][] graph;
	static int[][] d;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		d = new int[N+1][N+1]; 
		
		for(int i=1; i<=N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int time = Integer.parseInt(st.nextToken());
				graph[i][j] = time;
				d[i][j] = time;
			}
		}
		
		// 모든 정점에서 다른 모든 정점까지의 최단거리 구하기 -> 플로이드 와샬
		// k = 거쳐가는 노드 
		for(int k=1; k<=N; k++) {
			// i = 출발노드
			for(int i=1; i<=N; i++) {
				// j = 도착 노드
				for(int j=1; j<=N; j++) {
					if(d[i][j] > d[i][k]+d[k][j]) {
						d[i][j] = d[i][k]+d[k][j];
					}
				}
			}
		}
		
		// A -> B까지의 최단 거리가 C보다 작은지 여부 구하기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(d[a][b] <= c) {
				System.out.println("Enjoy other party");
			}else {
				System.out.println("Stay here");
			}
		}
		
	}
}
