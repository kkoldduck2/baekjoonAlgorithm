import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통
 * a,b는 비어있고, c는 가득 차 있다.
 * 다른 물통으로 쏟아 부을때, 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 부을 수 있다. 
 * a가 비어있을 때, c에 담겨있을 수 있는 물의 양을 모두 구하시오
 * 
 * 상태 : A,B,C 에 들어 있는 물 용량  
 * -> 상태가 복잡해지면 구조체를 만드는게 좋다. 
 */
public class Main {
	static int[] Limit;
	static boolean[] possible;		// 가능한 정답들
	static boolean[][][] visit;
	static int[] df = {0, 0, 1, 1, 2, 2};
	static int[] dt = {1, 2, 0, 2, 0, 1};
	
	// 물통의 현재 상태와 물을 붓는 행위를 관리하는 구조체
	static class State{
		int[] X;
		public State(int[] _X) {
			X = new int[3];
			for(int i=0; i<3; i++) {
				X[i] = _X[i];
			}
		}
		
		State move(int from, int to) {
			// from 물통에서 to 물통으로 물을 옮긴다.
			int[] nX = new int[] {X[0], X[1], X[2]};
			if(X[from] + X[to] >= Limit[to]) {
				nX[from] -= (Limit[to]-X[to]);
				nX[to] = Limit[to];
			}else {
				nX[to] += nX[from];
				nX[from] = 0;
			}
			return new State(nX);
		}
		
	}

	// 물통 탐색 시작 
	static void bfs(int a, int b, int c) {
		Queue<State> q = new LinkedList<>();
		q.add(new State(new int[] {a,b,c}));
		visit[a][b][c] = true;
		
		while(!q.isEmpty()) {
			State now = q.remove();
			if(now.X[0]==0) possible[now.X[2]] = true;
			for(int i=0; i<6; i++) {
				State next = now.move(df[i], dt[i]);
				if(!visit[next.X[0]][next.X[1]][next.X[2]]) {
					q.add(next);
					visit[next.X[0]][next.X[1]][next.X[2]] = true;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		Limit = new int[3];
		
		Limit[0] = a;
		Limit[1] = b;
		Limit[2] = c;
		possible = new boolean[205];
		visit = new boolean[205][205][205];
		bfs(0, 0, Limit[2]);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<=Limit[2]; i++) {
			if(possible[i]) sb.append(i).append(' ');
		}
		System.out.println(sb);
	}
}
