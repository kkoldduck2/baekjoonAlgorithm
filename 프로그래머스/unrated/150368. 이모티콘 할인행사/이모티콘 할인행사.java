import java.util.*;

class Solution {
    static int[] discounts = {10, 20, 30, 40}; // 선택 가능한 할인율
	static List<int []> results;
    int[] calculate(int[] emojiDiscount, int[] emoticons, int[][] users) {
		int plusEnroll = 0;
		int totalPrice = 0;
		int[] totalPriceByUser = new int[users.length];		// 각 사용자가 구매할 총 비용
		
		for(int i=0; i<users.length; i++) {
			for(int j=0; j<emoticons.length; j++) {
				// 구매할 사람
				if(users[i][0] <= emojiDiscount[j]) {
					totalPriceByUser[i] += (emoticons[j]*(1-(double)emojiDiscount[j]/100));
				}
			}
		}
		
		for(int i=0; i<users.length; i++) {
			if(totalPriceByUser[i] >= users[i][1]) {
				plusEnroll += 1;
			}else {
				totalPrice += totalPriceByUser[i];
			}
		}
//		System.out.println("emojiDiscount : "+Arrays.toString(emojiDiscount)+", plusEnroll : "+plusEnroll+", totalPrice : "+totalPrice );
		return new int[] {plusEnroll, totalPrice};
	}
    void dfs(int k, int[] emojiDiscount, int[] emoticons, int[][] users) {
		// 모든 이모티콘의 가격이 정해짐
		if(k == emoticons.length) {
			// 플러스 서비스 가입자 및 판매액 구하기 
			int[] result = calculate(emojiDiscount, emoticons, users);
			results.add(result);
			return;
		}
		
		// k번째 이모티콘의 가격
		for(int i=0; i<discounts.length; i++) {
			emojiDiscount[k] = discounts[i];
			dfs(k+1, emojiDiscount, emoticons, users);
		}
	}
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        
        results = new ArrayList<>();
        
        // 각 이모티콘에 대해서 할인율 정하고, 그때의 플러스 서비스 가입자 및 판매액 구하기 
        int[] emojiDiscount = new int[emoticons.length];
        dfs(0, emojiDiscount, emoticons, users);
        
        Collections.sort(results, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] != o2[0]) {
					return o2[0] - o1[0];
				}
				return o2[1] - o1[1];
			}
        });
        
        answer = results.get(0);
        
        return answer;
    }
}