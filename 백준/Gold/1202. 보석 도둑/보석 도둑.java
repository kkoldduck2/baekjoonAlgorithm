import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	
	// 무게 -> 가치 순 정렬
	static class Gem implements Comparable<Gem>{ 
		int w;
		int v;
		public Gem(int w, int v) {
			this.w = w;
			this.v = v;
		}
		
		@Override
		public int compareTo(Gem o) {
			// 무게가 같을 때 : 가격 내림 차순
			if(this.w == o.w) {
				return o.v - this.v;
			}
			// 무게 오름 차순
			return this.w - o.w;
		}

		@Override
		public String toString() {
			return "무게 : "+w+", 가치 : "+v;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 보석 개수
		K = Integer.parseInt(st.nextToken());	// 가방 개수
		
		int[] M = new int[N]; 			// 보석 무게
		int[] V = new int[N]; 			// 보석 가치
		
		Gem[] gems = new Gem[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			M[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
			gems[i] = new Gem(M[i], V[i]);
		}
		
		Arrays.sort(gems);
		
		int[] C = new int[K]; 		// 각 가방에 담을 수 있는 최대 무게
		for(int i=0; i<K; i++) {
			C[i] = Integer.parseInt(br.readLine());
		}
		
//		작은 가방부터 각 가방에 담을 수 있는 최대 가치의 보석을 담는다. 
		Arrays.sort(C);
		/**
		 * C[i]는 지금까지 중 가장 작은 무게를 담을 수 있는 가방이다. 
		 * 따라서 이 가방에 담을 수 있는 보석 중 최대 무게 (그 중 최대 가치)의 보석을 담으면, 이후의 가방은 선택권이 더 많아진다. 
		 * (담을 수 있는 무게가 더 크므로)
		 */
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		long totalVal = 0;
		for(int i=0, j=0; i<K; i++) {
			// 현재 가방의 무게보다 작거나 같은 보석을 모두 우선순위 큐에 넣음
			while(j < N && gems[j].w <= C[i]) {
				pq.offer(gems[j++].v);
			}
			
			// 우선 순위 큐에 있는 요소를 하나 빼서 가방에 넣음
			// 이 때, 우선순위 큐는 내림차순 정렬이 되어 있으므로
			// 첫 번째 요소는 가장 큰 가격을 의미함
			if(!pq.isEmpty()) {
				totalVal += pq.remove();
			}
		}
		
		System.out.println(totalVal);
		
	}
}
