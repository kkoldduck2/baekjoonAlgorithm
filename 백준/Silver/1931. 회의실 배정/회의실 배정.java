import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node>{
		int s;
		int e;
		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		// 빨리 끝나는 순으로 정렬
		@Override
		public int compareTo(Node o) {
			if(this.e == o.e) {
				return Integer.compare(this.s, o.s);
			}
			return Integer.compare(this.e, o.e);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
//		ArrayList<Node> al = new ArrayList<Node>();
		for(int i=0; i<n; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			pq.add(new Node(s,e));
//			al.add(new Node(s,e));
		}
		
//		System.out.println("--------------------------------");
		int cnt=0;
		int nextTime;
		
//		while(al.size()!=0) {
//			Node now = al.get(0);
//			nextTime = now.e;
//			cnt++;
//			al.removeIf(node -> (node.s < nextTime));
//		}
		
		
		while(pq.isEmpty()==false) {
			Node now = pq.poll();
			nextTime = now.e;
			cnt++;
			while(!pq.isEmpty()&&(nextTime > pq.peek().s)) {
//				System.out.println(nextTime+", "+pq.peek().s);
				pq.remove();
			}
		}
		
		System.out.println(cnt);
	}
}
