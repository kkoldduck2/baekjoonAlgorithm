import java.util.*;

class Solution {
    int dateToInt(String date) {
		String[] dateArr = date.split("\\.");

		int dateInt = (Integer.parseInt(dateArr[0]))*12*28 + (Integer.parseInt(dateArr[1]))*28 + Integer.parseInt(dateArr[2]);
		return dateInt;
	}
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        
        HashMap<String, Integer> termHm = new HashMap<>();
        for(String term : terms) {
        	String[] termArr = term.split(" ");
        	int months = Integer.parseInt(termArr[1])*28;
        	termHm.put(termArr[0], months);
        }
        
        int todayNum = dateToInt(today);
        
        for(int i=0; i<privacies.length; i++) {
        	String[] privacyArr = privacies[i].split(" ");
        	int startDate = dateToInt(privacyArr[0]);
        	int endDate = startDate + termHm.get(privacyArr[1]);
        	if(endDate <= todayNum) {
        		result.add(i+1);
        	}
        }
        
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
        	answer[i] = result.get(i);
        }
        
        Arrays.sort(answer);
        
        
        return answer;
    }
}