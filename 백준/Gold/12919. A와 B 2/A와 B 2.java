
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static boolean isPossible(String S, String T) {
		if(S.equals(T)) {
			return true;
		}
		
		if(S.length() >= T.length()) return false;
		
		boolean rst = false;
		if(T.charAt(0) == 'B') {
			String substr = T.substring(1);
			StringBuilder sb = new StringBuilder(substr);
			String str = sb.reverse().toString();
			rst = rst || isPossible(S, str);
		}
		
		if(T.charAt(T.length()-1) == 'A') {
			rst = rst || isPossible(S, T.substring(0, T.length()-1));
		}
		
		return rst;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		
		// S -> T로 만들수 있으면 1, 없으면 0을 출력한다. 
		boolean answer = isPossible(S, T);
		if(answer) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
}	
