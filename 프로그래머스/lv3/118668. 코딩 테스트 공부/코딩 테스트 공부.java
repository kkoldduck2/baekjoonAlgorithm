import java.util.Arrays;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int maxAlp = 0;
        int maxCop = 0;
        
        
        for(int i=0; i<problems.length; i++) {
        	maxAlp = Math.max(problems[i][0], maxAlp);
        	maxCop = Math.max(problems[i][1], maxCop);
        }
       
        int[][] dp = new int[maxAlp+1][maxCop+1];
        for(int i=0; i<dp.length; i++) {
        	Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        alp = Math.min(maxAlp, alp);
        cop = Math.min(maxCop, cop);
        dp[alp][cop] = 0;
        
        // for문으로 풀기 
        for(int i=alp; i<=maxAlp; i++) {
        	for(int j=cop; j<=maxCop; j++) {
        		if(i+1 <= maxAlp) {
        			dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
        		}
        		if(j+1 <= maxCop) {
        			dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
        		}
        		
        		for(int[] prob: problems) {
        			int alp_req = prob[0];
        			int cop_req = prob[1];
        			int alp_rwd = prob[2];
        			int cop_rwd = prob[3];
        			int cost = prob[4];
        			
        			if(i>=alp_req && j>=cop_req) {
        				int next_alp = Math.min(maxAlp, i+alp_rwd);
        				int next_cop = Math.min(maxCop, j+cop_rwd);
        				dp[next_alp][next_cop] = Math.min(dp[next_alp][next_cop],dp[i][j] + cost);
        			}
        		}
        	}
        }
        
        answer = dp[maxAlp][maxCop];
        return answer;
    }
}