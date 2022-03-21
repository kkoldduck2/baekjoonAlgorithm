import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
	static class Alphabet implements Comparable<Alphabet>{
		char alpha;
		int digitSum;	// 자릿수의 합
		int val;
		public Alphabet(char alpha, int digitSum) {
			this.alpha = alpha;
			this.digitSum = digitSum;
		}
		
		// lev 기준으로 내림차순 정렬
		@Override
		public int compareTo(Alphabet o) {
			return o.digitSum - this.digitSum;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String[] slist = new String[n];
		
		for(int i=0; i<n; i++) {
			slist[i] = br.readLine();
		}
		
		HashMap<Character, Alphabet> hm = new HashMap<>();
		
		for(int k=0; k<n; k++) {
			String s1 = slist[k];
			for(int i=s1.length()-1; i>=0; i--) {
				char c = s1.charAt(i);
				int digit = (int) Math.pow(10, s1.length()-1-i);
				
				if(hm.containsKey(c)) {
					int digitSum = hm.get(c).digitSum+digit;
					hm.put(c, new Alphabet(c, digitSum));
				}else {
					hm.put(c, new Alphabet(c, digit));
				}
			}
		}
		
		List<Alphabet> list = new ArrayList<>(hm.values());
		Collections.sort(list);
		
		hm.clear();
		
		int val = 9;
		for(Alphabet a : list) {
//			System.out.println("debug : "+ a.alpha+", "+a.lev);
			if(!hm.containsKey(a.alpha)) {
				a.val = val;
				hm.put(a.alpha, a);
				val --;
			}else {
				
			}
		}
		
//		printTest(hm);
		
		int result =0;
		for(int k=0; k<n; k++) {
			String s1 = slist[k];
			for(int i=s1.length()-1; i>=0; i--) {
				Alphabet a = hm.get(s1.charAt(i));
				result += (a.val * Math.pow(10, s1.length()-1-i));
			}
		}
		
		System.out.println(result);
		
	}
	
	public static void printTest(HashMap<Character, Alphabet> hm) {
		for( char a : hm.keySet()) {
			System.out.println(a+"의 val : "+hm.get(a).val );
		}
	}
}
