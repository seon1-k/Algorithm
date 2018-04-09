import java.util.Scanner;

public class SWE_1953 {
	private static Scanner sc;
	
	static int[][] tunnel;
	static boolean[][] isVisited;
	static int n, m, l;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=0;t<T;t++) {
			n = sc.nextInt();
			m = sc.nextInt();
			int r = sc.nextInt();
			int c = sc.nextInt();
			l = sc.nextInt();
			
			tunnel = new int[n][m];
			isVisited = new boolean[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					tunnel[i][j] = sc.nextInt();
				}
			}
			dfs(r, c, 1, new boolean[n][m]);
			int count = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if (isVisited[i][j]) {
//						System.out.println("true : ("+i+", "+j+")");
						count++;
					}
				}
			}
			
			System.out.println("#"+(t+1)+" "+count);
		}
	}
	
	static void dfs(int x, int y, int length, boolean[][] visited) {
		if(length == l+1) return;
		int direction = tunnel[x][y];
		if(direction == 0) return;
		isVisited[x][y] = true;
//		System.out.println("("+x+", "+y+")");
		
		switch (direction) {
		case 1:
			if(check(x-1, y) && !visited[x-1][y] && isConnected(x, y, x-1, y)) dfs(x-1, y, length+1, copyArr(visited, x, y)); // ╩С
			if(check(x+1, y) && !visited[x+1][y] && isConnected(x, y, x+1, y)) dfs(x+1, y, length+1, copyArr(visited, x, y)); // го
			if(check(x, y-1) && !visited[x][y-1] && isConnected(x, y, x, y-1)) dfs(x, y-1, length+1, copyArr(visited, x, y)); // аб
			if(check(x, y+1) && !visited[x][y+1] && isConnected(x, y, x, y+1)) dfs(x, y+1, length+1, copyArr(visited, x, y)); // ©Л
			break;
		case 2:
			if(check(x-1, y) && !visited[x-1][y] && isConnected(x, y, x-1, y)) dfs(x-1, y, length+1, copyArr(visited, x, y));
			if(check(x+1, y) && !visited[x+1][y] && isConnected(x, y, x+1, y)) dfs(x+1, y, length+1, copyArr(visited, x, y));
			break;
		case 3:
			if(check(x, y-1) && !visited[x][y-1] && isConnected(x, y, x, y-1)) dfs(x, y-1, length+1, copyArr(visited, x, y));
			if(check(x, y+1) && !visited[x][y+1] && isConnected(x, y, x, y+1)) dfs(x, y+1, length+1, copyArr(visited, x, y));
			break;
		case 4:
			if(check(x-1, y) && !visited[x-1][y] && isConnected(x, y, x-1, y)) dfs(x-1, y, length+1, copyArr(visited, x, y)); // ╩С
			if(check(x, y+1) && !visited[x][y+1] && isConnected(x, y, x, y+1)) dfs(x, y+1, length+1, copyArr(visited, x, y)); // ©Л
			break;
		case 5:
			if(check(x+1, y) && !visited[x+1][y] && isConnected(x, y, x+1, y)) dfs(x+1, y, length+1, copyArr(visited, x, y)); // го
			if(check(x, y+1) && !visited[x][y+1] && isConnected(x, y, x, y+1)) dfs(x, y+1, length+1, copyArr(visited, x, y)); // ©Л
			break;
		case 6:
			if(check(x+1, y) && !visited[x+1][y] && isConnected(x, y, x+1, y)) dfs(x+1, y, length+1, copyArr(visited, x, y)); // го
			if(check(x, y-1) && !visited[x][y-1] && isConnected(x, y, x, y-1)) dfs(x, y-1, length+1, copyArr(visited, x, y)); // аб
			break;
		case 7:
			if(check(x-1, y) && !visited[x-1][y] && isConnected(x, y, x-1, y)) dfs(x-1, y, length+1, copyArr(visited, x, y)); // ╩С
			if(check(x, y-1) && !visited[x][y-1] && isConnected(x, y, x, y-1)) dfs(x, y-1, length+1, copyArr(visited, x, y)); // аб
			break;
		}
	}
	
	static boolean isConnected(int x, int y, int newX, int newY) {
		int newDir = tunnel[newX][newY];
		if (newDir == 1) return true;
		if(newX == x-1 && newY == y) { // ю╖
			if (newDir == 2 || newDir == 5 || newDir == 6) return true;
		}
		if(newX == x+1 && newY == y) { // ╬ф╥║
			if (newDir == 2 || newDir == 4 || newDir == 7) return true;
		}
		if(newX == x && newY == y-1) { // аб
			if (newDir >= 3 && newDir <= 5) return true;
		}
		if(newX == x && newY == y+1) { // ©Л
			if (newDir == 3 || newDir == 6 || newDir == 7) return true;
		}
		return false;
	}
	
	static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) return false;
		return true;
	}
	
	static boolean[][] copyArr(boolean[][] v, int x, int y){
		boolean[][] result = new boolean[v.length][v[0].length];
		for(int i=0;i<v.length;i++) {
			for(int j=0;j<v[0].length;j++) {
				result[i][j] = v[i][j];
			}
		}
		result[x][y] = true;
		return result;
	}
}
