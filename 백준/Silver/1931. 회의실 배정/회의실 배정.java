import java.util.Arrays;
import java.util.Scanner;
/**
 * 각 회의실의 시작 시간과 끝나는 시간이 주어짐
 * 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있음
 * 회의의 시작시간과 끝나는 시간이 같을 수 있음
 * 
 * 최대 사용할 수 있는 회의의 "최대 개수"를 출력 => optimization problem : 사용할 수 있는 회의의 개수는 여러 개 but 그 중에서 최대인 해를 찾는 것
 *  
 * solution 1 : DP
 *  
 * solution 2 : greedy
 *  
 */
public class Main {
	static class Activity implements Comparable<Activity>{
		int start;
		int finish;
		public Activity(int start, int end) {
			this.start = start;
			this.finish = end;
		}
		@Override
		public int compareTo(Activity o) {
			if(this.finish == o.finish) {
				return this.start - o.start;
			}
			return this.finish - o.finish;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();		// 회의의 수
		
		Activity[] activities = new Activity[n];
		
		for(int i=0; i<n; i++) {
			int start = sc.nextInt();
			int finish = sc.nextInt();
			activities[i] = new Activity(start, finish);
		}
		
		Arrays.sort(activities);
		
		/**
		 * solution 1 : DP
		 * d[i] : activities[i]를 시작으로 하는 최대 회의실 개수, 
		 * d[i] = d[j]+1 (i<j, fi <= sj)
		 */
//		int[] d = new int[n]; 	
//		d[n-1] = 1;	// activities는 finish를 기준으로 정렬되어 있다고 가정한다. 왜 인지는 아직 잘 모르겠음
//		
//		// O(n^2) 
//		for(int i=n-2; i>=0; i--) {	
//			for(int j=i+1; j<n; j++) {	
//				if(activities[i].finish <= activities[j].start) {
//					d[i] = Math.max(d[i], d[j]+1);
//				}
//			}
//		}
//		
//		int ans = 0;
//		for(int i=0; i<n; i++) {
//			ans = Math.max(ans, d[i]);
//		}
//		
//		System.out.println(ans);
		
		
		/**
		 * solution 2 : greedy
		 * greedy란? 그때 그때 최적의 해를 찾음 -> 전체적으로 최적의 해를 찾게 됨
		 * -> 매번 activity를 선택할 때마다 남은 resource가 최대가 되도록 한다.
		 * -> activity를 선택할 때 finish 시간이 가장 작은 얘를 선택한다.
		 * 
		 * greedy로 풀 수 있는 문제에서 dp로 풀면 시간 초과가 된다. 즉, overkill하게 됨
		 * 
		 */
		
		// activities는 이미 끝나는 시간 순으로 정렬되어 있음 
		int finish  = activities[0].finish;
		int count = 1;
		for(int i=1; i<n; i++) {
			if(activities[i].start >= finish) {
				count++;
				finish = activities[i].finish;
			}
		}
		
		System.out.println(count);
	}
}

