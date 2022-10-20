
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] s;
	static int minDiff = Integer.MAX_VALUE;
	static int calculateSkill(List<Integer> team) {
		int sum = 0;
		for(int i : team) {
			for(int j : team) {
				if(i==j) continue;
				sum += s[i][j];
			}
		}
		
		return sum;
	}
	static void makeTeam(int i, List<Integer> start, List<Integer> link) {
		if(i==n) {
			if(start.size()==0 || link.size()==0) return;
			
			// 각 팀 점수 계산 -> 차이 구하고 갱신하기
			int startScore = calculateSkill(start);
			int linkScore = calculateSkill(link);
//			System.out.println("start : "+start.toString()+", score : "+startScore);
//			System.out.println("link : "+link.toString()+", score : "+linkScore);
//			System.out.println();
			
			int diff = Math.abs(startScore - linkScore);
			minDiff = Math.min(diff, minDiff);
			return;
		}
		
		start.add(i);
		makeTeam(i+1, start, link);
		start.remove(start.size()-1);
		
		link.add(i);
		makeTeam(i+1, start, link);
		link.remove(link.size()-1);
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		s = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> start = new ArrayList<>();
		List<Integer> link = new ArrayList<>();
		
		makeTeam(0, start, link);
		
		System.out.println(minDiff);
	}
}
