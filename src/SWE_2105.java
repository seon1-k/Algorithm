import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//failed 
public class SWE_2105 {
	private static Scanner sc;
	
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	
	static int n;
	static int[][] cafe;
	
//	static boolean[] directions; // 0: 우하, 1: 좌하, 2:좌상, 3:우상
	
	static int startX, startY;
	static int maxValue = -1;
	
	static boolean[][] isVisited;
	static boolean[] numbers;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=0;t<T;t++) {
			n = sc.nextInt();
			cafe = new int[n][n];
			maxValue = -1;
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					cafe[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if((i==0 && j==0) || (i==0 && j==n-1) || (i==n-1 && j==0) || (i==n-1 && i==j)) continue;
//					isVisited = new boolean[n][n];
//					numbers = new boolean[101];
					startX = -1;
					startY = -1;
//					if(i==0 && j==1) {
						dfs(i, j, 0, -1,new boolean[n][n], new boolean[101], new int[4], new ArrayList<Point1>());
//						bfs(i, j);
//					}
					
//						dfs(i, j, new boolean[n][n], new boolean[101], 0, new boolean[4], -1, -1, -1); 	
				}
			}
			
			System.out.println(maxValue);
		}
	}
	
	static void dfs(int x, int y, int length, int beforeDir, boolean[][] visited, boolean[] numbers, int[] dir, ArrayList<Point1> history) {
		if(startX == -1 && startY == -1) { startX = x; startY = y; }
		else if(startX == x && startY == y) {
			System.out.println("end ("+x+", "+y+"), "+length); 
			if(length > maxValue) maxValue = length;
			return;
		}
		else { visited[x][y] = true; numbers[cafe[x][y]] = true; }
		System.out.println("("+x+", "+y+"), "+length); 
		for(int i=0;i<4;i++) {
			int nX = x + dx[i]; int nY = y + dy[i];
			if(!check(nX, nY) || beforeDir == reverse(i)) continue;
			if(!visited[nX][nY] && !numbers[cafe[nX][nY]]) {
//				ArrayList<Point1> arr1 = new ArrayList<Point1>();
//				arr1.addAll(arr);
//				arr1.add(new Point1(x, y, 0));
				
				switch (directionNum(dir)) {
				case 4:
					if(i != beforeDir) continue;
					break;
				case 3:
					if(dir[0] == 0) {
						if(dir[1] == dir[3] && i != 0) continue;
						else if(dir[1] > dir[3] && (i==1 || i==2)) continue;
						else if(dir[1] < dir[3]) continue;
//						else continue;
					}
					if(dir[1] == 0) {
						if(dir[2] == dir[0] && i != 1) continue;
						else if(dir[2] > dir[0] && (i==2 || i==3)) continue;
						else if(dir[2] < dir[0]) continue;
//						else continue;
					}
					if(dir[2] == 0) {
						if(dir[3] == dir[1] && i != 2) continue;
						else if(dir[3] > dir[1] && (i==0 || i==3)) continue;
						else if(dir[3] < dir[1]) continue;
//						else continue;
					}
					if(dir[3] == 0) {
						if(dir[2] == dir[0] && i != 3) continue;
						else if(dir[2] > dir[0] && (i==1 || i==2)) continue;
						else if(dir[2] < dir[0]) continue;
//						else continue;
					}
					break;
				case 2:
					int firstDir = -1;
					for(int k=0;k<4;k++) {
						if(k!= beforeDir && dir[k] != 0) firstDir = dir[k];
					}
					if(i == firstDir) continue;
//					if (i != beforeDir || i != reverse(firstDir)) continue;
					break;
				}
				
				int[] newDir = dir.clone();
				newDir[i]++;
				ArrayList<Point1> newArr = (ArrayList<Point1>) history.clone();
				newArr.add(new Point1(x, y, 0));
				
				dfs(nX, nY, length+1, i, copyArr(visited), copyArr(numbers, -1), newDir, newArr);
			}
		}
	}
	
	static int directionNum(int[] dir) {
		// 모든 방향이동을 다 헀는지
		// 이동한 방향 수
		int sum = 0;
		for(int i=0;i<4;i++) {
			if(dir[i] > 0) sum++;
		}
		return sum;
	}
	
	static boolean[][] copyArr(boolean[][] v){
		boolean[][] result = new boolean[v.length][v[0].length];
		for(int i=0;i<v.length;i++) {
			for(int j=0;j<v[0].length;j++) {
				result[i][j] = v[i][j];
			}
		}
		return result;
	}
	
	static boolean[] copyArr(boolean[] v, int n) {
		boolean[] result = new boolean[v.length];
		for(int i=0;i<v.length;i++) {
			result[i] = v[i];
		}
		if(n>=0) result[n] = true;
		return result;
	}
	
	static int reverse(int dir) {
		switch (dir) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 0;
		case 3:
			return 1;
		}
		return -1;
	}
	
//	static void bfs(int x, int y) {
//		Queue<Point1> q = new LinkedList();
//		q.add(new Point1(x, y, 1));
//		isVisited[x][y] = true;
//		while(!q.isEmpty()) {
////			System.out.println();
//			Point1 temp = q.poll();
////			if (startX == temp.x && startY == temp.y)
//			System.out.println("("+temp.x+", "+temp.y+"), "+temp.length); 
//			numbers[cafe[temp.x][temp.y]]= true;
//			isVisited[temp.x][temp.y]= true; 
//			
//			for(int i=0;i<4;i++) {
//				int newX = temp.x + dx[i];
//				int newY = temp.y + dy[i];
//				if(!check(newX, newY)) continue;
//				int newValue = cafe[newX][newY];
//				if(check(newX, newY) && !isVisited[newX][newY] && !numbers[newValue]) {
//					q.add(new Point1(newX, newY, temp.length+1));
//					isVisited[newX][newY] = true;
//				}
//			}
//		}
//	}
	static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=n) return false;
		return true;
	}
}

class Point1 {
	int x,y;
	int length = 0;
	public Point1(int x, int y, int length) {
		this.x = x; this.y = y;
		if(length > 0) this.length = length;
	}
}
//	static void dfs(int x, int y, boolean[][] visited, boolean[] numChecked, int length, boolean[] dir, int beforeDir, int bx, int by) {
//		
//		
////		System.out.println("("+bx+", "+by+")->"+"("+x+", "+y+") "+length);
//		
//		if(startX == -1 && startY == -1) {
//			// 시작점
//			startX = x;
//			startY = y;
//			visited[x][y] = false;
//		} else if(x == startX && y == startY && directionNum(dir) == 4) {
////			System.out.println("end");
//			if(maxValue < length)  {
//				maxValue = length;
////				System.out.println("maxValue: "+maxValue);
//			}
//			return;
//		} else {
//			visited[x][y] = true;	
//		}
//		int value = cafe[x][y];
//		
//		
//		for(int i=0;i<4;i++) {
//			int newX = x + dx[i];
//			int newY = y + dy[i];
////			System.out.println("move to : ("+newX+", "+newY+")");
//			if(!check(newX, newY)) continue; 
//			int newValue = cafe[newX][newY];
//			if(numChecked[newValue] && (newX != startX && newY != startY)) continue; 
//			if(!visited[newX][newY]) {
//				if(beforeDir == -1) {
//					dfs(newX, newY, copyArr(visited), copyArr(numChecked, value), length+1, copyArr(dir, i), i, x, y);
//				} else {
//					if(directionNum(dir) == 4) {
//						if(beforeDir != i) continue;
//					} else if(directionNum(dir) == 3) {
//						if(dir[0] && dir[1] && dir[2]) {
//							if( i != 3 && i != beforeDir) continue;
//						} else if(dir[1] && dir[2] && dir[3]) {
//							if( i != 0 && i != beforeDir) continue;
//						} else if(dir[0] && dir[2] && dir[3]) {
//							if( i != 1 && i != beforeDir) continue;
//						} else if(dir[0] && dir[1] && dir[3]) {
//							if( i != 2 && i != beforeDir) continue;
//						}
//					} else if(directionNum(dir) == 2) {
//						if(dir[0] && dir[1]) {
//							if(beforeDir == 0 && i == 1) continue; // 0, 2, 3 가능
//							if(beforeDir == 1 && i == 0) continue; // 1, 2, 3 가능
//						} else if(dir[1] && dir[2]) {
//							if(beforeDir == 1 && i == 2) continue;
//							if(beforeDir == 2 && i == 1) continue;
//						} else if(dir[2] && dir[3]) {
//							if(beforeDir == 2 && i == 3) continue;
//							if(beforeDir == 3 && i == 2) continue;// 0, 2, 3 가능
//						} else if(dir[0] && dir[2]) {
//							if(beforeDir == 0 && i == 2) continue;
//							if(beforeDir == 2 && i == 0) continue;// 0, 2, 3 가능
//						} else if(dir[1] && dir[3]) {
//							if(beforeDir == 1 && i == 3) continue;
//							if(beforeDir == 3 && i == 1) continue;// 0, 2, 3 가능
//						} else if(dir[0] && dir[3]) {
//							if(beforeDir == 0 && i == 3) continue;
//							if(beforeDir == 3 && i == 0) continue;// 0, 2, 3 가능
//						}
//					} else if(directionNum(dir) == 1) {
//						if(dir[0] && i == 2) continue;
//						if(dir[1] && i == 3) continue;
//						if(dir[2] && i == 0) continue;
//						if(dir[3] && i == 1) continue;
//					} else continue;
//					dir[i] = true;
//					dfs(newX, newY, copyArr(visited), copyArr(numChecked, value), length+1, copyArr(dir, -1), i, x, y);
//				}
//			}
//				
//		}
//	}
//	

//	
//	static int directionNum(boolean[] dir) {
//		// 모든 방향이동을 다 헀는지
//		// 이동한 방향 수
//		int sum = 0;
//		for(int i=0;i<4;i++) {
//			if(dir[i]) sum++;
//		}
//		return sum;
//	}
//	
////	static int[] alreadyDirection(boolean[] dir) {
////		//이미 방문한
////		ArrayList<Integer> array = new ArrayList<>();
////		for(int i=0;i<4;i++) {
////			if(dir[i]) array.add(i);
////		}
////		int[] result = new int[array.size()];
////		for(int i=0;i<result.length;i++) {
////			result[i] = array.get(i);
////		}
////		return result;
////	}
//	
	
//}
