import java.util.*;
class Solution {
    static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int n;
    void bfs(int currentX, int currentY, boolean[][] visited, int[][] graph, int blockOrEmpty, List<List<int[]>> list) {
		Queue<int[]> q = new LinkedList<>();
		visited[currentX][currentY] = true;
		q.add(new int[] {currentX, currentY});
		
		List<int[]> block = new ArrayList<>();
		block.add(new int[] {currentX-currentX, currentY-currentY});
		
		while(!q.isEmpty()) {
			int[] point = q.remove();
			int px = point[0];
			int py = point[1];
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				
				if(!visited[nx][ny] && graph[nx][ny] == blockOrEmpty) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
					block.add(new int[] {nx-currentX, ny-currentY});
				}
			}
		}
		list.add(block);
	}
    int findBlock(List<List<int[]>> board, List<List<int[]>> table) {
		int size = 0;
		int tableLen = table.size();
		int boardLen = board.size();
		boolean[] usedBoard = new boolean[boardLen];
		
		for(int i=0; i<tableLen; i++) {
			List<int[]> tableBlock = table.get(i);
			for(int j=0; j<boardLen; j++) {
				List<int[]> boardBlock = board.get(j);
				
				// tableBlock과 boardBlock이 같은지 검사
				if(tableBlock.size() == boardBlock.size() && !usedBoard[j]) {	// 블록 크기가 같을때
					if(isSame(boardBlock, tableBlock)) { // 회전해서 둘이 같은지 확인
						size += tableBlock.size();
						usedBoard[j] = true;
						break;
					}
				}
			}
		}
		return size;
	}
    boolean isSame(List<int[]> bBlock, List<int[]> tBlock) {
		boolean isCorrect = false;
		
		// 
		bBlock.sort((o1, o2) -> {
			if(o1[0] != o2[0]) return o1[0] - o2[0];
			return o1[1] - o2[1];
		});
		
		// rotate 0, 90, 180, 270
		for(int i=0; i<4; i++) {
			tBlock.sort((o1, o2) -> {
				if(o1[0] != o2[0]) return o1[0] - o2[0];
				return o1[1] - o2[1];
			});
			
			// 원점 
			int ox = tBlock.get(0)[0];
			int oy = tBlock.get(0)[1];
			// 원점 기준으로 tBlock의 상대 위치 구하기
			for(int j=0; j<tBlock.size(); j++) {
				tBlock.get(j)[0] -= ox;
				tBlock.get(j)[1] -= oy;
			}
			
			boolean isCorrectPoint = true;
			for(int j=0; j<bBlock.size(); j++) {	// 좌표 비교
				int[] boardPoint = bBlock.get(j);
				int[] tablePoint = tBlock.get(j);
				
				if(boardPoint[0] != tablePoint[0] || boardPoint[1] != tablePoint[1]) {
					isCorrectPoint = false;
					break;
				}
			}
			
			if(isCorrectPoint) return true;
			// 다르면 -> 회전
			for(int j=0; j<tBlock.size(); j++) {
				int temp = tBlock.get(j)[0];
				tBlock.get(j)[0] = tBlock.get(j)[1];
				tBlock.get(j)[1] = -temp;
			}
		}
		
		return isCorrect;
	}
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        n = table.length;
        boolean[][] visitedTable = new boolean[n][n];
        boolean[][] visitedBoard = new boolean[n][n];
        List<List<int[]>> boardList = new ArrayList<>();
        List<List<int[]>> tableList = new ArrayList<>();
        
        // table block, board block -> list에 담기
        for(int i=0; i<n; i++) {
        	for(int j=0; j<n; j++) {
        		
        		if(table[i][j]==1 && !visitedTable[i][j]) {
        			bfs(i, j, visitedTable, table, 1, tableList);
        		}
        		
        		if(game_board[i][j]==0 && !visitedBoard[i][j]) {
        			bfs(i, j, visitedBoard, game_board, 0, boardList);
        		}
        		
        	}
        }
        
        answer = findBlock(boardList, tableList);
        
        return answer;
    }
}