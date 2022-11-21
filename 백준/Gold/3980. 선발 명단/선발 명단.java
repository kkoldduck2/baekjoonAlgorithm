
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int maxSum = 0;
	static int[][] s;
	static void lineup(int position, int sum, boolean[] used) {
		// 끝
		if(position == 11) {
			maxSum = Math.max(maxSum, sum);
			return ;
		}
		
		// 해당 position에 배치할 선수
		for(int i=0; i<11; i++) {
			if(!used[i] && s[i][position] > 0) {
				used[i] = true;
				lineup(position+1, sum+s[i][position], used);
				used[i] = false;
			}
		}

	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int c = Integer.parseInt(br.readLine());
		
		while(c -- > 0) {
			s = new int[11][11];
			// 입력
			for(int i=0; i<11; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<11; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[] used = new boolean[11];
			
			// 각 포지션에 선수를 배치
			lineup(0, 0, used);
			System.out.println(maxSum);
			maxSum = 0;
		}
		
	}
}
