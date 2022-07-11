import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int s = 0;
		int e = people.length - 1;
		
		int cnt = 0;
		Arrays.sort(people);
		
		while(s<=e) {
			if(people[s]+people[e] > limit) {
				cnt ++;
				e--;
			}else {
				cnt ++;
				s++;
				e--;
			}
		}

		return cnt;
    }
}