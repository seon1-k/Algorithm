import java.util.Scanner;

public class BOJ_12100 {
	private static Scanner sc;
	
	static int n;
	static int[][] map;
	
	static boolean showLog = false;
	
	static int maxValue = -1;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
			}
		}
//		move(move(map, 1, new boolean[n][n]), 2, new boolean[n][n]);
//		move(2, new boolean[n][n]);
		for(int i=0;i<4;i++) {
			dfs(map, i, 0);
		}
		
		System.out.println(maxValue);
	}
	
	static void dfs(int[][] map, int dir, int length) {
		if(length == 5) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] > maxValue) maxValue = map[i][j];
				}
			}
			return;
		}
		
		int[][] newMap = move(copyArr(map), dir, new boolean[n][n]);
		
		for(int i=0;i<4;i++) {
			if(i != dir) dfs(newMap, i, length+1);
		}
	}
	
	static int[][] move(int[][] map, int direction, boolean[][] isMerged) {
		int[][] newMap = copyArr(map);
		
		switch(direction) {
		case 0: //╩С
			for(int j=0;j<n;j++) {
				for(int i=1;i<n;i++) {
					int item = newMap[i][j];
					if(item == 0) continue;
					int k = i;
					while(k>0 && newMap[k-1][j] == 0) {
						newMap[k-1][j] = item;
						newMap[k][j] = 0;
						k--;
					}
					if(showLog) showMap(newMap);
					if(k>0 && newMap[k-1][j] == newMap[k][j] && !isMerged[k-1][j]) {
						newMap[k-1][j] *= 2;
						newMap[k][j] = 0;
						isMerged[k-1][j] = true;
					}
					if(showLog) showMap(newMap);
				}
			}
			break;
		case 1: //го
			for(int j=0;j<n;j++) {
				for(int i=n-2;i>=0;i--) {
					int item = newMap[i][j];
					if(item == 0) continue;
					int k = i;
					while(k<n-1 && newMap[k+1][j] == 0) {
						newMap[k+1][j] = item;
						newMap[k][j] = 0;
						k++;
					}
					if(showLog) showMap(newMap);
					if(k<n-1 && newMap[k+1][j] == newMap[k][j] && !isMerged[k+1][j]) {
						newMap[k+1][j] *= 2;
						newMap[k][j] = 0;
						isMerged[k+1][j] = true;
					}
					if(showLog) showMap(newMap);
				}
			}
			break;
		case 2: //аб
			for(int i=0;i<n;i++) {
				for(int j=1;j<n;j++) {
					int item = newMap[i][j];
					if(item == 0) continue;
					int k = j;
					while(k>0 && newMap[i][k-1] == 0) {
						newMap[i][k-1] = item;
						newMap[i][k] = 0;
						k--;
					}
					if(showLog) showMap(newMap);
					if(k>0 && newMap[i][k-1] == newMap[i][k] && !isMerged[i][k-1]) {
						newMap[i][k-1] *= 2;
						newMap[i][k] = 0;
						isMerged[i][k-1] = true;
					}
					if(showLog) showMap(newMap);
				}
			}
			break;
		case 3: //©Л
			for(int i=0;i<n;i++) {
				for(int j=n-2;j>=0;j--) {
					int item = newMap[i][j];
					if(item == 0) continue;
					int k = j;
					while(k<n-1 && newMap[i][k+1] == 0) {
						newMap[i][k+1] = item;
						newMap[i][k] = 0;
						k++;
					}
					if(showLog) showMap(newMap);
					if(k<n-1 && newMap[i][k+1] == newMap[i][k] && !isMerged[i][k+1]) {
						newMap[i][k+1] *= 2;
						newMap[i][k] = 0;
						isMerged[i][k+1] = true;
					}
					if(showLog) showMap(newMap);
				}
			}
			break;
		}
//		showMap(newMap);
		return newMap;
	}
	
	static int[][] copyArr(int[][] arr){
		int[][] result = new int[arr.length][arr[0].length];
		for(int i=0;i<arr.length;i++) {
			result[i] = arr[i].clone();
		}
		return result;
	}
	
	static void showMap(int[][] map) {
		for(int a=0;a<n;a++) {
			for(int b=0;b<n;b++) {
				System.out.print(map[a][b]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
