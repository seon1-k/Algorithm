import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Solution {
	private static Scanner sc;
	
	static int n, m;
	static int[][] town;
	
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	static boolean[][] isVisited;
	
//	static int maxValue = -1;
	static int maxCount = -1;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=0;t<T;t++) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			town = new int[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					town[i][j] = sc.nextInt();
				}
			}
			
//			maxValue = -1;
			maxCount = -1;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
//					System.out.println("Point "+i+", "+j);
					isVisited = new boolean[n][n];
					getHouse(i, j, n*2);
				}
			}
			System.out.println("#"+(t+1)+" "+maxCount);
		}
	}
	
//	static void calculate(int x, int y, int k) {
//		if(k==1)
//	}
	
	static void print(Set<Point> arr) {
		for(final Point item:arr) {
			System.out.print("("+item.x + ", " + item.y+")");
		}
		System.out.println();
	}
	
	static void getHouse(int x, int y, int maxK) {
		isVisited[x][y] = true;
		Set<Point> arr = new HashSet<>();
		Set<Point> before = new HashSet<>();
		for(int k=1;k<=maxK;k++) {
			
			if(k==1) {
				arr.add(new Point(x,y));
				int count = getHouseCount(arr);
				int value = count * m - operationMoney(k);
				if(count > maxCount && value >= 0) {
//					System.out.print("k: "+k + " ");
//					System.out.println(" count: "+count + " v:" + value + " op:" +operationMoney(k));
					maxCount = count;
					if(maxCount == Math.pow(n, 2)) return;
				}
				continue;
			}
			if(k==2) before = arr;
			Set<Point> currentData = new HashSet<>();
			for(final Point item:before) {
				for(int i=0;i<4;i++) {
					int nX = item.x+dx[i]; int nY = item.y+dy[i];
					if(check(nX, nY)) {
						if(!isVisited[nX][nY]) {
							isVisited[nX][nY] = true;
							currentData.add(new Point(nX, nY));
						}
					}
				}
			}
			before = currentData;
			arr.addAll(currentData);
			
			int count = getHouseCount(arr);
			int value = count * m - operationMoney(k);
			if(count > maxCount && value >= 0) {
//				System.out.print("k: "+k + " ");
//				System.out.println(" count: "+count + " v:" + value + " op:" +operationMoney(k));
				maxCount = count;
				if(maxCount == Math.pow(n, 2)) return;
			}
			
//			print(arr);
//			System.out.println(arr.size()+ " "+ getHouseCount(arr));
//			int houseMoney = getHouseCount(arr) * m;
//			int op = operationMoney(k);
//			if(houseMoney - op > maxValue) {
//				System.out.println("count: "+ (houseMoney/m) + " k: "+k);
//				maxValue = houseMoney - op;
//				maxCount = houseMoney / m;
//			}
		}
	}
	
	static int getHouseCount(Set<Point> arr) {
		int sum = 0;
		for(final Point p:arr) {
			if(town[p.x][p.y] == 1) sum ++;
		}
		return sum;
	}
	
//	static Set<Point> getHouse2(int x, int y, int k) {
//		isVisited[x][y] = true;
////		isSelected[x][y] = true;
//		
//		Set<Point> arr = new HashSet<>();
//		
//		if(k==1) {
//			arr.add(new Point(x,y));
//		}
//		else {
//			Set<Point> items = getHouse(x, y, k-1);
//			for(final Point item: items) {
//				for(int i=0;i<4;i++) {
//					int nX = item.x+dx[i]; int nY = item.y+dy[i];
//					if(check(nX, nY)) {
//						if(isVisited[nX][nY] == false) {
//							isVisited[nX][nY] = true;
//							arr.add(new Point(nX, nY));
//						}
//					}
//				}
//			}
//			arr.addAll(items);
//		}
//		
//		return arr;
//	}
	
	static int operationMoney(int k) {
		return (int) (Math.pow(k, 2) + (k-1) * (k-1));
	}
	
	static boolean check(int x, int y) {
		if (x<0 || y<0 || x>=n || y>=n) return false;
		return true;
	}
}
