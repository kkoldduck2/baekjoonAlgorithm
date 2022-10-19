
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static List<Long> arr;
	static boolean isTriangle(long a, long b, long c) {
		return (a + b) > c;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr.add(Long.parseLong(st.nextToken()));
		}
		
		Collections.sort(arr);
		
		int lastIdx = 2;
		while(true) {
			if(lastIdx >= arr.size()) {
				break;
			}
			
			if(isTriangle(arr.get(0), arr.get(1), arr.get(lastIdx))) {
				lastIdx ++;
			}else {
				// triangle이 아님 -> 가장 앞에 있는 (0번째 인덱스) 숫자 삭제
				arr.remove(0);
			}
		}
		
		System.out.println(arr.size());
	}
}
