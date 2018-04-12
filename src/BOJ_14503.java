import java.util.Scanner;

public class BOJ_14503 {
	private static Scanner sc;
	
	static int[][] map;
	static int n, m;
	
	private static int dx[] = {-1, 0, 1, 0};
	private static int dy[] = {0, 1, 0, -1};
	
	static int count = 0;
	static boolean showLog = false;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		dfs(r, c, d, new boolean[n][m]);
		System.out.println(count);
	}
	
	static void dfs(int x, int y, int dir, boolean[][] visited) {
		if(!visited[x][y]) count++;
		if(showLog) System.out.println(x+" "+y+" "+dir);
		visited[x][y] = true;
		
		int newDir = -1;
		int wallCount = 0;
		for(int i=0;i<4;i++) {
			if(i==0) newDir = leftDir(dir);
			else newDir = leftDir(newDir);
			int nX = x+dx[newDir], nY = y+dy[newDir];
			if(!check(nX, nY)) continue;
			int newValue = map[nX][nY];
			if(showLog) System.out.println("new: "+nX+" "+nY+" "+newDir);
			if(newValue == 1 || visited[nX][nY]) {
				wallCount++; 
				continue;
			}
			
			if(!visited[nX][nY]) {
				//2-1
				dfs(nX, nY, newDir, copyArr(visited));
				return;
			}
		}
		
		if(wallCount == 4) {
			int r = reverseDir(dir);
			int rX = x+dx[r], rY = y+dy[r];
			if(!check(rX, rY)) return;
			int rValue = map[rX][rY];
			if(rValue == 1) return;
			dfs(rX, rY, dir, visited);
		} else {
			return;
		}
	}
	
	static int reverseDir(int dir) {
		int result = dir+2;
		if(result>3) result-=4;
		return result;
	}
	
	static int leftDir(int dir) {
		int result = dir - 1;
		if(result<0) return 3;
		return result;
	}
	
	static boolean[][] copyArr(boolean[][] arr){
		boolean[][] result = new boolean[arr.length][arr[0].length];
		for(int i=0;i<arr.length;i++) {
			result[i] = arr[i].clone();
		}
		return result;
	}
	
	static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) return false;
		return true;
	}
}