import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] tree;			// tree[i] : i번 노드의 부모노드 
	static List<Integer>[] list;
	static int n;
	
	static void makeTree() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1); // root
		
		while(!q.isEmpty()) {
			int parent = q.remove();
			for(int child : list[parent]) {
				if(tree[child]==0) {
					tree[child] = parent;
					q.add(child);
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		tree = new int[n+1];
		tree[1] = 1;
		
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		makeTree();
		
		for(int i=2; i<=n; i++) {
			System.out.println(tree[i]);
		}
	}
}
