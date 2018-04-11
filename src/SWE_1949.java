import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWE_1949 {
	private static Scanner sc;
	
	static int[][] map;
	static int n, k;
	
	static int maxLength;
	
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for(int i=0;i<t;i++) {
			n = sc.nextInt();
			k = sc.nextInt();
			map = new int[n][n];
			
			maxLength = -1;
			
			int maxValue = -1;
			Queue<Point> q = new LinkedList<>();
			for(int j=0;j<n;j++) {
				for(int l=0;l<n;l++) {
					int temp = sc.nextInt();
					map[j][l] = temp;
					
					if(j==0 && l==0) {
						maxValue = temp;
						q.add(new Point(j, l));
					} else if (temp > maxValue) {
						maxValue = temp;
						q = new LinkedList<>();
						q.add(new Point(j, l));
					} else if (temp == maxValue) {
						q.add(new Point(j, l));
					}
				}
			}
			maxLength = 0;
			while(!q.isEmpty()) {
				Point p = q.poll();
//				isVisited = new boolean[n][n];				
				dfs(map, p.x, p.y, 1, k, new boolean[n][n]);
//				System.out.println();
			}
			System.out.println("#"+(i+1)+" "+maxLength);
		}
	}
	
	static void dfs(int[][] map, int x, int y, int length, int k, boolean[][] visited) {
		boolean[][] newVisited = copyArr(visited, x, y);
//		System.out.println("("+x+", "+y+"), "+length);
		if(length > maxLength) maxLength = length;
		for(int i=0;i<4;i++) {
			int newX = x+dx[i];
			int newY = y+dy[i];
			if (check(newX, newY) && !visited[newX][newY]) {
				if (map[newX][newY] < map[x][y]) dfs(map, newX, newY, length+1, k, newVisited);
				if (k != 0) {
					for(int T=1;T<=k;T++) {
						if(map[newX][newY] - T < map[x][y])
							dfs(copyArr(map, newX, newY, T), newX, newY, length+1, 0, newVisited);
					}
				}
			}
		}
	}
	
	static int[][] copyArr(int[][] map, int x, int y, int minus) {
		int[][] result = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				result[i][j] = map[i][j];
				if(i==x && j==y) result[i][j] -= minus;
			}
		}
		return result;
	}
	
	static boolean[][] copyArr(boolean[][] v, int x, int y){
		boolean[][] result = new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				result[i][j] = v[i][j];
				if(i==x && j==y) result[i][j] = true;
			}
		}
		return result;
	}
	
	static boolean check(int x, int y) {
		if (x < 0 || x>=n || y < 0 || y>=n) return false;
		return true;
	}
}

//class Point {
//	int x, y;
//	public Point(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//	
//	
//}
