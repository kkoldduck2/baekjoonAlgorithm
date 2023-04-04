import java.util.Stack;


class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();
        
        // i번째 집
        for(int i=0; i<n; i++) {
        	// i번째 집 delivery 개수
        	for(int j=0; j<deliveries[i]; j++) {
        		dStack.add(i+1);
        	}
        	
        	// i번째 집 pickup 개수
        	for(int j=0; j<pickups[i]; j++) {
        		pStack.add(i+1);
        	}
        }
        
        while(!dStack.isEmpty() && !pStack.isEmpty()) {
        	int lastD = dStack.peek();
        	int lastP = pStack.peek();
        	
        	for(int i=0; i<cap; i++) {
        		if(!dStack.isEmpty()) dStack.pop();
        		if(!pStack.isEmpty()) pStack.pop();
        	}
        	
        	answer += Math.max(lastD, lastP)*2L;
        }
        
        while(!dStack.isEmpty()) {
        	int lastD = dStack.peek();
        	
        	for(int i=0; i<cap; i++) {
        		if(!dStack.isEmpty()) dStack.pop();
        	}
        	
        	answer += lastD * 2L;
        }
        
        while(!pStack.isEmpty()) {
        	int lastP = pStack.peek();
        	
        	for(int i=0; i<cap; i++) {
        		if(!pStack.isEmpty()) pStack.pop();
        	}
        	
        	answer += lastP * 2L;
        }
        
        return answer;
    }
}