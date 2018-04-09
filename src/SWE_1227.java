import java.util.Scanner;

public class SWE_1227 {
	private static Scanner sc;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] maze;
	static int isOK = 0;
	
	private final static int MAX = 100;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		for(int T=0;T<10;T++) {
			int caseNum = sc.nextInt();
			maze = new int[MAX][MAX];
//			isVisited = new boolean[MAX][MAX];
			
			int startX = -1;
			int startY = -1;
//			int endX = -1;
//			int endY = -1;
			
			for(int i=0;i<MAX;i++) {
				String str = sc.next();
//				System.out.println(str);
				for(int j=0;j<MAX;j++) {
					maze[i][j] = str.charAt(j) - '0';
					if(maze[i][j] == 2) {
						startX = i;
						startY = j;
					} 
//					else if(maze[i][j] == 3) {
//						endX = i;
//						endY = j;
//					}
				}
				isOK = 0;
			}
			dfs(startX, startY, new boolean[MAX][MAX]);
			
			System.out.println("#"+(T+1)+" "+isOK);
		}
	}
	
	static void dfs(int x, int y, boolean[][] visited) {
//		System.out.println(x+", "+y);
//		isVisited[x][y] = true;
//		visited[x][y] = true;
		
		for(int i=0;i<4;i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if(check(newX, newY) && !visited[newX][newY]) {
				if (maze[newX][newY] == 0) dfs(newX, newY, copyArray(visited, x, y));
				else if (maze[newX][newY] == 3) {
					isOK = 1;
					return;
				}
			}
		}
	}
	
	static boolean check(int x, int y) {
		if(x< 0 || y<0 || x>=MAX || y>=MAX) return false;
		return true;
	}
	
	static boolean[][] copyArray(boolean[][] a, int x, int y){
		boolean[][] result = new boolean[MAX][MAX];
		for(int i=0;i<MAX;i++) {
			for(int j=0;j<MAX;j++) {
				result[i][j] = a[i][j];
			}
		}
		result[x][y] = true;
		return result;
	}
}