import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] arr;
	static List<Integer>[] tree;
	static int remove;
	static void makeTree() {
		tree = new ArrayList[n];
		for(int i=0; i<n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n; i++) {
			int parent = arr[i];
			int child = i;
			if(parent == -1) {
//				tree[child].add(child);
				continue;
			} 
			tree[parent].add(child);
		}
	}
	
	static int getLeafCnt(int root) {
		Queue<Integer> q = new LinkedList<>();
		q.add(root);
		int num = 0;
		while(!q.isEmpty()) {
			int out = q.remove();
			if(tree[out].size()==0) {	//  리프노드
				num ++;
			} 
			for(int child : tree[out]) {
				q.add(child);
			}
		}
		return num;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int root = 0;
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i]==-1) root = i;
		}
		remove = Integer.parseInt(br.readLine());
		
		makeTree();
		
		int total = getLeafCnt(root);
		int sub = getLeafCnt(remove);
		int answer = total - sub;
		if(arr[remove]!=root && arr[remove]!=-1 ) {
			if(tree[arr[remove]].size()==1) {
				answer ++;
			}
		} 
		
		if(answer==0 && arr[remove]==root) {
			System.out.println(1);
		}else {
			System.out.println(answer);
		}
		
	}
}
