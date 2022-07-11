class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n+1];
        for(int i=1; i<=n; i++) {
        	students[i] =1;
        }
        
        for(int i=0; i<lost.length; i++) {
        	students[lost[i]]--;
        }
        
        for(int i=0; i<reserve.length; i++) {
        	students[reserve[i]]++;
        }
        
        int cnt = 0;
        for(int i=1; i<=n; i++) {
        	if(students[i]==0) {
        		cnt ++;
        	}
        }
        
        for(int i=1; i<=n; i++) {
        	if(students[i]==0) {
        		if(students[i-1]==2) {
        			students[i-1] --;
        			cnt --;
        		}else if(i<n && students[i+1]==2) {
        			students[i+1] --;
        			cnt --;
        		}
        	}
        }
        
        return n-cnt;
    }
}