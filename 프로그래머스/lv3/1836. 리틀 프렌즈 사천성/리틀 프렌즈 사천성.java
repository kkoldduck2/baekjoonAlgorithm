import java.util.*;
class Solution {
    boolean canGo(int[] loc1, int[] loc2, char[][] map) {
		
		if(loc1[0]==loc2[0]) {		// 같은 행
//			System.out.println("같은 행 : "+map[loc1[0]][loc2[1]]);
			int from = Math.min(loc1[1], loc2[1]);
			int to = Math.max(loc1[1], loc2[1]);
			for(int i=from+1; i<to; i++) {
				if(map[loc1[0]][i] != '.') {
					return false;
				}
			}
		}else {			// 같은 열
			int from = Math.min(loc1[0], loc2[0]);
			int to = Math.max(loc1[0], loc2[0]);
			for(int i=from+1; i<to; i++) {
				if(map[i][loc1[1]] != '.') {
					return false;
				}
			}
		}
		
		return true;
	}
    public String solution(int m, int n, String[] board) {
        String answer = "";
        List<Character> result = new ArrayList<>();
        
        char[][] map = new char[m][n];
        HashMap<Character, Stack<int[]>> hm = new HashMap<>();
        
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		map[i][j] = board[i].charAt(j);
        		if(map[i][j]=='.' || map[i][j]=='*') continue;
        		if(!hm.containsKey(map[i][j])) {
        			hm.put(map[i][j], new Stack<>());
        		}
        		hm.get(map[i][j]).add(new int[] {i, j});
        	}
        }
        
        int cnt = 0;
        while(cnt++ < 27) {
        	List<Character> list = new ArrayList<>(hm.keySet());
        	Collections.sort(list);
        	for(Character c : list) {
        		if(hm.get(c).size()==0) continue;
        		int[] loc1 = hm.get(c).pop();
        		int[] loc2 = hm.get(c).pop();
        		boolean flag = false;
        		
//        		System.out.println("카드 : "+map[loc1[0]][loc2[1]]);
//        		System.out.println("카드 : "+c);
        		
        		if(loc1[0]==loc2[0]) {
        			flag = canGo(loc1, loc2, map);
        			if(flag) {
        				map[loc1[0]][loc1[1]] = '.';
            			map[loc2[0]][loc2[1]] = '.';
        			}
        		}else if(loc1[1]==loc2[1]) {
        			flag = canGo(loc1, loc2, map);
        			if(flag) {
        				map[loc1[0]][loc1[1]] = '.';
            			map[loc2[0]][loc2[1]] = '.';
        			}
        		}else {
        			int[] loc3 = new int[] {loc1[0], loc2[1]};
        			int[] loc4 = new int[] {loc2[0], loc1[1]};
        			
        			boolean flag1 = map[loc3[0]][loc3[1]]=='.' && canGo(loc1,loc3, map) && canGo(loc3, loc2, map);
        			boolean flag2 = map[loc4[0]][loc4[1]]=='.' && canGo(loc1,loc4, map) && canGo(loc4, loc2, map);
        			flag = flag1 || flag2;
        			
        			if(flag) {
        				map[loc1[0]][loc1[1]] = '.';
            			map[loc2[0]][loc2[1]] = '.';
        			}
        		}
        		
        		if(flag) {	// 지울 수 있음 -> 정답에 추가 
        			result.add(c); 
        			hm.remove(c);
//        			System.out.println("사라짐 : "+result.toString());
        			break;
        		}else {	// 못 지움
        			hm.get(c).add(loc1);
        			hm.get(c).add(loc2);
        		}
        	}
        }
        
        if(hm.size()!=0) return "IMPOSSIBLE";
        
//        Collections.sort(result);
        
        for(int i=0; i<result.size(); i++) {
        	answer += result.get(i);
        }
        return answer;
    }
}