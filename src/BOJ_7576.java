import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_7576 {
	
	private static Scanner sc;
//	static int n;
//	static boolean[][] arr;
//	static boolean[] isVisited;
	
	static int[][] farm;
	static int m, n;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static boolean[][] isVisited;
	
	static Queue<XY> q;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		m = sc.nextInt();
		n = sc.nextInt();
		
		isVisited = new boolean[n][m];
		q = new LinkedList();
		farm = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				farm[i][j] = sc.nextInt();
				if(farm[i][j] == -1) isVisited[i][j] = true;
				if(farm[i][j] == 1) q.add(new XY(i, j));
			}
		}
		
		
//		int count = 0;
//		while(!isComplete(farm)) {
//			
//			
//			int[][] tempFarm = copyArr(farm);
			bfs();
//			if(equal(farm, tempFarm)) {
//				System.out.println(-1);
//				return;
//			}
//			count++;
//		}
//		System.out.println(count);
	}
	
	static void bfs() {
		int length = 0;
		while(!q.isEmpty()) {
			XY item = q.poll();
			length = item.length;
			for(int i=0;i<4;i++){
				int nX = item.x+dx[i]; int nY = item.y + dy[i];
				if(!check(nX, nY)) continue;
				int newValue = farm[nX][nY];
				if(newValue == 0) {
					farm[nX][nY] = 1;
					q.add(new XY(nX, nY, item.length+1));
				}
			}
		}
		if(isComplete(farm)) {
			System.out.println(length);
		} else {
			System.out.println(-1);
		}
	}
	
	static boolean equal(int[][] a, int[][] b) {
		for(int i=0;i<a.length;i++) {
//			if(a[i] != b[i]) return false;
			for(int j=0;j<a[0].length;j++) {
				if(a[i][j] != b[i][j]) return false;
			}
		}
		return true;
	}
	
	static int[][] copyArr(int[][] farm){
		int[][] result = new int[farm.length][farm[0].length];
		for(int i=0;i<farm.length;i++) {
			result[i] = farm[i].clone();
		}
		return result;
	}
	
	static boolean isComplete(int[][] farm) {
		for(int i=0;i<farm.length;i++) {
			for(int j=0;j<farm[0].length;j++) {
				if(farm[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) return false;
		return true;
	}
}

class XY {
	int x, y;
	int length = 0;
	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public XY(int x, int y, int l) {
		this.x = x;
		this.y = y;
		this.length = l;
	}
}