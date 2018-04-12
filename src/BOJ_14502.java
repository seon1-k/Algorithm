import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14502 {
	private static Scanner sc;
	
	static int n, m;
	static int[][] map;
	
	static ArrayList<Point> virus = new ArrayList<>();
//	static ArrayList<Point> walls = new ArrayList<>();
	static ArrayList<Point> emptys = new ArrayList<>();
	
	static boolean showLog = false;
	static int count = 0;
	static int maxCount = -1;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static boolean[][] virusVisited;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2) virus.add(new Point(i, j));
//				if(map[i][j] == 1) walls.add(new Point(i, j));
				if(map[i][j] == 0) emptys.add(new Point(i, j));
			}
		}
		
		for(int i=0;i<emptys.size();i++) {
			ArrayList<Point> items = new ArrayList<>();
			items.add(emptys.get(i));
			
			dfs(i, items);
		}
		System.out.println(maxCount);
	}
	
	static void makeWalls(ArrayList<Point> emptys) {
		int[][] newMap = copyArr(map);
		for(final Point empty:emptys) {
			newMap[empty.x][empty.y] = 1;
		}
		virusVisited = new boolean[n][m];
		bfsVirus(newMap);
	}
	
	static void bfsVirus(int[][] map) {
		Queue<Point> vArr = new LinkedList();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j] == 2) {
					vArr.add(new Point(i, j));
					virusVisited[i][j] = true;
				}
			}
		}
		int[][] newMap = copyArr(map);
		
		while(!vArr.isEmpty()) {
			Point v = vArr.poll();
			for(int i=0;i<4;i++) {
				int nX = v.x+dx[i], nY = v.y+dy[i];
				if(!check(nX, nY)) continue;
				if(!virusVisited[nX][nY] && newMap[nX][nY] == 0) {
					virusVisited[nX][nY] = true;
					newMap[nX][nY] = 2;
					vArr.add(new Point(nX, nY));
				}
			}
			
		}
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(newMap[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		int count = safeCount(newMap);
		if(count > maxCount) maxCount = count;
	}
	
	static void dfs(int index, ArrayList<Point> items) {
		for(int i=index+1;i<emptys.size();i++) {
			ArrayList<Point> newItems = new ArrayList<>();
			newItems.addAll(items);
			newItems.add(emptys.get(i));
			if(newItems.size() == 3) {
				if(showLog) print(newItems);
				makeWalls(newItems);
//				count++;
				continue;
			}
			dfs(i, newItems);
		}
	}
	
	static int[][] copyArr(int[][] map) {
		int[][] result = new int[map.length][map[0].length];
		for(int i=0;i<map.length;i++) {
			result[i] = map[i].clone();
		}
		return result;
	}
	
	static boolean[][] copyArr(boolean[][] map) {
		boolean[][] result = new boolean[map.length][map[0].length];
		for(int i=0;i<map.length;i++) {
			result[i] = map[i].clone();
		}
		return result;
	}
	
	static int virusCount(int[][] map) {
		int sum = 0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j] == 2) sum++;
			}
		}
		return sum;
	}
	
	static int safeCount(int[][] map) {
		int sum = 0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j] == 0) sum++;
			}
		}
		return sum;
	}
	
	static void print(ArrayList<Point> items) {
		for(final Point p:items) {
			p.print();
		}
		System.out.println();
	}
	
	static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) return false;
		return true;
	}
}