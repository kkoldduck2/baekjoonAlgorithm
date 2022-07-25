import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        StringTokenizer st;
        int[] answer;
        
        PriorityQueue<Integer> maxq = new PriorityQueue<>(new Comparator<>() {
        	public int compare(Integer n1, Integer n2) {
        		return n2 - n1;
        	}
        });
        
        PriorityQueue<Integer> minq = new PriorityQueue<>(new Comparator<>() {
        	public int compare(Integer n1, Integer n2) {
        		return n1 - n2;
        	}
        });
        
        for(String op : operations) {
        	st = new StringTokenizer(op);
        	String order = st.nextToken();
        	int num = Integer.parseInt(st.nextToken());
        	
        	if(order.equals("I")) {
        		maxq.add(num);
        		minq.add(num);
        	}else {
        		if(maxq.isEmpty()) continue;
        		if(num > 0) {
        			// 최댓값 삭제
        			int out = maxq.remove();
//        			System.out.println("max 큐에서 꺼냄 : "+out);
        			minq.clear();
        			minq.addAll(maxq);
        		}else {
        			// 최솟값 삭제
        			int out = minq.remove();
//        			System.out.println("min 큐에서 꺼냄 : "+out);
        			maxq.clear();
        			maxq.addAll(minq);
        		}
        	}
        }
        
        if(minq.isEmpty() && maxq.isEmpty()) return new int[] {0,0};
      
        answer = new int[2];
        answer[0] = maxq.remove();
        answer[1] = minq.remove();
        
        return answer;
    }
}