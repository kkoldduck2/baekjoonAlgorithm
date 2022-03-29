import java.util.*;
class Solution {
    static class Menu implements Comparable<Menu>{
		String menu;
		int cnt;
		public Menu(String menu, int cnt){
			this.menu = menu;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Menu o) {
			// 개수 순, 개수가 같으면 알파벳 순으로 정렬
			if(this.cnt != o.cnt) {
				return o.cnt - this.cnt;
			}else {
				return this.menu.compareTo(o.menu);
			}
		}
		
	}
	
	// key : 메뉴 구성, value : 주문 횟수 
	static HashMap<String, Integer> hm;
	// 현재까지 선택한 개수, 현재 선택여부 결정할 메뉴, 주문 내용, 선택해야할 개수 
	public static void makeCombinationMenu(int cnt, int i,String menu, String order, int courseCnt) {
		if(cnt == courseCnt) {
            char[] charArr = menu.toCharArray();
			Arrays.sort(charArr);
			menu = new String(charArr);
			if(hm.containsKey(menu)) {
				hm.put(menu, hm.get(menu)+1);
			}else {
				hm.put(menu, 1);
			}
//			System.out.println("order : "+order+", cnt : "+cnt+", menu : "+menu+", 주문 횟수 :"+hm.get(menu));
			return;
		}
		
		if(i >= order.length()) {
			return;
		}
		
		// 선택 여부 결정하기 
		// 선택 함
		makeCombinationMenu(cnt+1, i+1, menu+order.charAt(i), order, courseCnt);
		
		// 선택 안함
		makeCombinationMenu(cnt, i+1, menu, order, courseCnt);
	}
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
		// key : menuCombination, value : 선택 횟수
		hm = new HashMap<>();
		
		// 메뉴 조합 만들기
		for(int i=0; i<orders.length; i++) {
			for(int j=0; j<course.length; j++) {
				makeCombinationMenu(0, 0, "",orders[i], course[j]);	// 주문한 메뉴에서 course[i] 개수 만큼의 조합 만들어내기 
//				System.out.println("hash map : "+hm.keySet());
			}
		}
		
		ArrayList<String> li = new ArrayList<>();
		// course 개수 별로 가장 주문횟수가 많은 메뉴 반환
		for(int i=0; i<course.length; i++) {
			PriorityQueue<Menu> pq = new PriorityQueue<>();
			// 요리 course[i]개 코스 중 가장 많이 선택한 메뉴 구성
			for(String key:hm.keySet()) {
				if(key.length()==course[i] && hm.get(key)>=2) {
					pq.add(new Menu(key, hm.get(key)));
				}
			}
			
			// 해시맵 돌면서 key 값의 길이가 course[i]이고 && value가 2이상인 얘들을 -> Menu 객체로 만들어서 pq에 저장 
			// pq에서 하나씩 꺼내다가 이전과 값이 달라지면 더이상 추가 안하고 break;
			int before = -1;
			while(!pq.isEmpty() && pq.peek().cnt>=before) {
				Menu menu = pq.poll();
//				System.out.println("menu : "+menu.menu+", cnt : "+menu.cnt);
				before = menu.cnt;
				li.add(menu.menu);
			}
		}
		
		answer = li.toArray(new String[li.size()]);
		Arrays.sort(answer);
		return answer;
    }
}