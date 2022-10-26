
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 건물 중에서 2개의 건물을 골라서 치킨집
 * 모든 건물에서 가장 가까운 치킨 집까지 왕복하는 최단 시간의 총합을 최소화 할 수 있는 건물 2개를 골라서 치킨집을 열려고 하는 것이다.  
 * 최적의 위치가 될 수 있는 거물 2개의 번호 => 그 때의 모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합.
 * 
 * 건물 2개를 고른다 -> 그 경우 "모든 건물에서 가장 가까운 치킨 집까지 왕복하는 최단 시간의 총합"을 구한다. 
 */
public class Main {
	static int n;		// n개의 건물
	static int m;		// m개의 도로
	static List<Integer>[] graph;
	
	
	static int getShortestPath(List<Integer> selected) {
		int[] d = new int[n+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		
		Queue<Integer> q = new LinkedList<>();
		for(int i : selected) {
			q.add(i);
			d[i] = 0;
		}
		
		while(!q.isEmpty()) {
			int now = q.remove();
			
			for(int next : graph[now]) {
				if(d[next] > d[now]+1) {
					d[next] = d[now]+1;
					q.add(next);
				}
			}
		}
		
		int sum = 0;
		for(int i=1; i<=n; i++) {
			sum += d[i];
		}
		
		return sum*2;
	}
	
	static int minDist = Integer.MAX_VALUE;
	static List<int[]> candidates = new ArrayList<>();
	static void combin(int idx, List<Integer> selected) {
		if(selected.size()==2) {
			int rst = getShortestPath(selected);
			if(minDist > rst) {
				int[] tmp = new int[3];
				if(selected.get(0) < selected.get(1)) {
					tmp[0] = selected.get(0);
					tmp[1] = selected.get(1);
				}else {
					tmp[1] = selected.get(0);
					tmp[0] = selected.get(1);
				}
								
				tmp[2] = rst;
				minDist = rst;
				candidates.add(tmp);
			}
			
			return;
		}
		
		if(idx==n+1) return;
		
		selected.add(idx);
		combin(idx+1, selected);
		selected.remove(selected.size()-1);
		combin(idx+1, selected);
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());		
		m = Integer.parseInt(st.nextToken());		
		graph = new List[n+1];
		
		for(int i=1; i<n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		
		// 건물 2개 고르기
		combin(1, new ArrayList<>());
		
		Collections.sort(candidates, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] != o2[2]) {
					return o1[2] - o2[2];
				}
				if(o1[0] != o2[0]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});
		
		int[] answer = candidates.get(0);
		System.out.println(answer[0]+" "+ answer[1]+" "+answer[2]);
		
	}
}
