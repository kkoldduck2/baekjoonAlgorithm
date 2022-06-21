import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] map =new int[n][3];
		int[][] dmax = new int[n][3];
		int[][] dmin = new int[n][3];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int c=0; c<3; c++) {
			dmax[0][c] = map[0][c];
			dmin[0][c] = map[0][c];
		}
		
		for(int r=1; r<n; r++) {
			for(int c=0; c<3; c++) {
				if(c==0) {
					dmax[r][c] = Math.max(dmax[r-1][c], dmax[r-1][c+1])+map[r][c];
					dmin[r][c] = Math.min(dmin[r-1][c], dmin[r-1][c+1])+map[r][c];
				}else if(c==2) {
					dmax[r][c] = Math.max(dmax[r-1][c], dmax[r-1][c-1])+map[r][c];
					dmin[r][c] = Math.min(dmin[r-1][c], dmin[r-1][c-1])+map[r][c];
				}else {
					dmax[r][c] = Math.max(dmax[r-1][c], Math.max(dmax[r-1][c+1], dmax[r-1][c-1]))+map[r][c];
					dmin[r][c] = Math.min(dmin[r-1][c], Math.min(dmin[r-1][c+1], dmin[r-1][c-1]))+map[r][c];
				}
			}
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int c=0; c<3; c++) {
			max = Math.max(max, dmax[n-1][c]);
			min = Math.min(min, dmin[n-1][c]);
		}
		
		System.out.println(max+" "+min);
		
	}
}
