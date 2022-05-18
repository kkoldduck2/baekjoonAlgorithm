
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 괄호는 반드시 숫자의 앞이나 뒤에 옴
 * 바로 이전에 (가 놓였으면 )가 놓여야 함
 * 바로 이후에 )가 놓였으면 (가 놓여야 함.
 * 
 * 숫자 앞 -> (가 놓임
 * 숫자 뒤 -> )가 놓임
 *  
 * 수식 맨 앞 : (만 놓일 수 있음
 * 수식 맨 뒤 : )만 놓일 수 있음
 * 
 * ************ 단, 괄호 안에는 연산자가 하나만 들어 있어야 한다.
 */
public class Main {
	static HashMap<String, String> hm;
	static int max = Integer.MIN_VALUE;
	static ArrayList<Character> ops;
	static ArrayList<Integer> nums;
	
	
	private static int calculateSimple(int a, int b, char c) {
		switch(c) {
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '*':
				return a*b;
		}
		
		return 0;
	}
	
	
	// 괄호 연산을 하는 경우, 하지 않는 경우 
	private static void dfs(int result, int opIdx) {
		if(opIdx >= ops.size()) {
			// 연산 끝, result와 max 비교
			max = Math.max(result, max);
			return;
		}
		
		// i) 괄호 없이 바로 연산하는 경우  
		int res1 = calculateSimple(result, nums.get(opIdx+1), ops.get(opIdx));
		dfs(res1, opIdx+1);
		
		// ii) result의 오른쪽에 있는 값을 먼저 연산한 뒤, 그 결과를 result와 연산함
		if(opIdx+1 < ops.size()) {
			int prepare = calculateSimple(nums.get(opIdx+1), nums.get(opIdx+2), ops.get(opIdx+1));
			int res2 = calculateSimple(result, prepare, ops.get(opIdx));
			dfs(res2, opIdx+2);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		ops = new ArrayList<>();
		nums = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			char c = input.charAt(i);
			if(c=='+'|| c=='-'|| c=='*') {
				ops.add(c);
				continue;
			}
			nums.add(c-'0');
		}
		
		dfs(nums.get(0), 0);
		
		System.out.println(max);
		
	}
}
