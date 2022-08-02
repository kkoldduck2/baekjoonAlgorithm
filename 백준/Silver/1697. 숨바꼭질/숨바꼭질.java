import java.util.*;
// 틀...림.... 
// 다이나믹으로 풀어볼까 했는데 그 개념이 대충 맞음!
// 완전탐색 : 다이나믹 + 큐(BFS, 상태 공간 트리 생각해라)

public class Main {
	public static final int MAX = 1000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		boolean[] check = new boolean[MAX];
		int[] dist = new int[MAX]; 	// 다이내믹  dist[k] : n -> k까지  최소 시간(거리)
		
		
		/* 1) 시작을 큐에 */
		check[n] = true; // 수빈이가 현재 있는 위치는 이미 방문했음
		dist[n] = 0; // n->n 0초
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		
		while(!q.isEmpty()) {
			/* 2) pop : 현재 수빈이가 어디 있는지*/
			int now = q.poll();
			
			/* 3) 다음 정점 push : n-1, n+1, 2n */
			if(now-1 >=0 && check[now-1]==false) {
				q.add(now-1);
				check[now-1] = true;
				dist[now-1] = dist[now]+1;
			}
			
			if(now+1 <MAX && check[now+1]==false) {
				q.add(now+1);
				check[now+1] = true;
				dist[now+1] = dist[now]+1;
			}
			
			if(now*2 <MAX && check[now*2]==false) {
				q.add(now*2);
				check[now*2] = true;
				dist[now*2] = dist[now]+1;
			}
		}
		
		System.out.println(dist[k]);
		
	}	
}
