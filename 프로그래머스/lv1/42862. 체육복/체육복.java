class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] student = new int[n+1];	// 잃어버림 = -1, 여벌 = 1, 여벌있는데 잃어버림 =0, 둘다 아님 =0
		
		for(int i=0; i<reserve.length; i++) {
			student[reserve[i]] = 1;
		}
		for(int i=0; i<lost.length; i++) {
			if(student[lost[i]]==1) {
				student[lost[i]] =0;
			}else {
				student[lost[i]] = -1;
			}
		}
		
		for(int i=1; i<=n; i++) {
			if(student[i]==-1) {
				if(i==1) {
					if(student[i+1]==1) {
						student[i+1] = 0;
						answer++;
					}
					continue;
				} 
				if(i==n) {
					if(student[i-1]==1) {
						student[i+1] = 0;
						answer++;
					}
					continue;
				}
				
				// 앞에 얘가 여벌이 있으면 앞에꺼부터 받는다.
				if(student[i-1]==1) {
					student[i-1]=0;
					answer++;
					continue;
				}
				// 없으면 어쩔수 없이 뒤에꺼를 받는다.
				if(student[i+1]==1) {
					student[i+1]=0;
					answer++;
					continue;
				}
			}else {
				answer++;
			}
		}
		return answer;
        
    }
}