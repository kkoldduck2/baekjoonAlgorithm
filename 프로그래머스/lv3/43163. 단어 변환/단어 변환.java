import java.util.*;
class Solution {
    public boolean isDiffOne(String s1, String s2){
        int cnt=0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                cnt++;
            }
        }
        if(cnt==1){
            return true;
        }else{
            return false;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        List<String> li = new ArrayList<>();
        for(int i=0; i<words.length; i++) {
        	li.add(words[i]);
        }
        if(!li.contains(begin)){
            li.add(begin);
        }
        	// 마지막 인덱스에 저장되겠지..?
        int[] d = new int[words.length+1];	// 해당 노드를 방문하기까지 걸린 시간
        for(int i=0; i<words.length; i++) {
        	d[i] = -1;
        }
        if(!li.contains(target)) {
        	return 0;
        }
        
        // bfs 시작 (굳이 그래프를 구현할 필요x)
        Queue<String> q = new LinkedList<>();
        q.add(begin);// begin을 담는다.
        d[li.indexOf(begin)]=0;
        while(!q.isEmpty()){
            String now = q.remove();
            if(now.equals(target)) {
            	answer = d[li.indexOf(now)];
            	break;
            }
            for(int i=0; i<words.length; i++) {
            	if(d[i]==-1 && isDiffOne(now, words[i])) {
//            		System.out.println(words[i]);
            		d[i] = d[li.indexOf(now)]+1;
            		q.offer(words[i]);
            	}
            }
        }
        return answer;
    }
}