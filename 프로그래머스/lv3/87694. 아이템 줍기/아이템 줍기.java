import java.util.*;
class Solution {
    static class Point{
		int x;
		int y;
		int cost;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		public String toString() {
			return x+", "+y+", "+cost;
		}
		
	}
	
	static class Rectangle{
		int x1;
		int y1;
		int x2;
		int y2;
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
		public boolean inRectangle(int x, int y) {
			if(x>x1 && x<x2 && y>y1 && y<y2) return true;
			return false;
		}
		
		public boolean isBoundOfRectangle(int nx1, int ny1, int nx2, int ny2) {
			if(nx1==nx2) {
				if((nx1==x1 || nx1==x2) && ny1>=y1 && ny1<=y2 &&  ny2>=y1 && ny2<=y2) return true;
				return false;
			}
			
			if(ny1==ny2) {
				if((ny1==y1 || ny1==y2) && nx1>=x1 && nx1<=x2 &&  nx2>=x1 && nx2<=x2) return true;
				return false;
			}
			return false;
		}
	}
	
	static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=maxX || y<0 || y>=maxY) return true;
		return false;
	}
	static int maxX = Integer.MIN_VALUE;
	static int maxY = Integer.MIN_VALUE;
	static List<Rectangle> list = new ArrayList<>();
	
	static boolean[][] makeMap(int[][] rectangle){
        for(int[] r : rectangle) {
        	int x1 = r[0]*2;
        	int y1 = r[1]*2;
        	int x2 = r[2]*2;
        	int y2 = r[3]*2;
        	list.add(new Rectangle(x1, y1, x2, y2));
        	maxX = Math.max(maxX, x2);
        	maxY = Math.max(maxY, y2);
        }
        
        maxX = maxX*2+1;
        maxY = maxY*2+1;
        
        boolean[][] map = new boolean[maxY][maxX];
        for(int[] r : rectangle) {
        	int x1 = r[0]*2;
        	int y1 = r[1]*2;
        	int x2 = r[2]*2;
        	int y2 = r[3]*2;
        	
        	for(int i=y1; i<=y2; i++) {
        		for(int j=x1; j<=x2; j++) {
        			boolean flag = true;
        			// i,j가 사각형 안에 존재하는지 검사 
        			for(Rectangle rec : list) {
        				if(rec.inRectangle(j, i)) {
//        					System.out.println(i+", "+j+"는 사각형 안에 존재");
        					flag = false;
        					break;
        				}
        			}
        			map[i][j] = flag;
        		}
        	}
        }
        
//        printMap(map);
        return map;
	}
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        boolean[][] map = makeMap(rectangle);		// map[x][y] == true : x,y 좌표에 길이 있다. 
        boolean[][] visited = new boolean[maxY][maxX];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        visited[characterY*2][characterX*2] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(characterX*2, characterY*2, 0));
        
        while(!q.isEmpty()) {
        	Point now = q.remove();
        	if(now.x == itemX*2 && now.y == itemY*2) {
        		System.out.println("도착");
        		answer = now.cost;
        		break;
        	}
        	
//        	System.out.println(now);
        	for(int i=0; i<4; i++) {
        		int nx = now.x + dx[i];
        		int ny = now.y + dy[i];
        		if(!isOutOfBound(nx, ny) && map[ny][nx] && !visited[ny][nx]) {
        			// now - next가 rectangle의 한 변에 속하나?
        			for(Rectangle rec : list) {
        				if(rec.isBoundOfRectangle(nx, ny, now.x, now.y)) {
//        					System.out.println(now.x+", "+now.y+",, "+nx+", "+ny);
        					q.add(new Point(nx, ny, now.cost+1));
                			visited[ny][nx] = true;
        					break;
        				}
        			}
        			
        		}
        	}
        }
        
        answer /= 2;
        return answer;
    }
}