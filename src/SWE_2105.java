import java.util.Scanner;

public class SWE_2105 {
	private static Scanner sc;
	
	static int dx[] = {1,1,-1,-1};
	static int dy[] = {1,-1,-1,1};
	
	static int[][] cafe;
	static int startX, startY;
	static int n;
	static int maxValue;
	
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
					if(j==0 || j==n-1) continue;
//					if((i==0 && j==0) || (i==n-1 && j==0) || (i==n-1 && j==0) && (i==n-1 && j==n-1)) continue;
					startX = -1; startY = -1;
					
//					if(i==1 && j== 2) 
						dfs(i, j, new boolean[n][n], 1, new boolean[101], -1);
				}
			}
			
			System.out.println("#"+(t+1)+" "+maxValue);
			
		}
	}
	
	static void dfs(int x, int y, boolean[][] visited, int length, boolean[] numbers, int beforeDir) {
//		System.out.println(x+", "+y);
		if(startX == -1 && startY == -1) {
			// 시작점 
			startX = x;
			startY = y;
//			System.out.println("start "+x+", "+y);
			int nX = x + dx[0]; int nY = y+dy[0];
			if(check(nX, nY) && cafe[x][y] != cafe[nX][nY]) {
				numbers[cafe[x][y]] = true;
				dfs(nX, nY, copyArr(visited), length+1, numbers.clone(), 0);
			}
		} else {
			visited[x][y] = true;
			numbers[cafe[x][y]] = true;
			
			int end = beforeDir+2;
			if(end > 4) end = 4;
			for(int i=beforeDir;i<end;i++) {
				int nX = x + dx[i]; int nY = y+dy[i];
				if(nX == startX && nY == startY) {
//					System.out.println("end "+(length));
					if(maxValue < length) maxValue = length;
					return;
				}
				if(!check(nX, nY)) continue;
				int newValue = cafe[nX][nY];
				if(!visited[nX][nY] && !numbers[newValue]) {
					dfs(nX, nY, copyArr(visited), length+1, numbers.clone(), i);
				}
			}
		}
	}
	
	static boolean[][] copyArr(boolean[][] arr){
		boolean[][] result = new boolean[arr.length][arr[0].length];
		for(int i=0;i<arr.length;i++) {
//			for(int j=0;j<arr[0].length;j++) {
//				result[i][j] = arr[i]
//			}
			result[i] = arr[i].clone();
		}
		
		return result;
	}
	
	static boolean check(int x, int y) {
		if(x<0 || y<0 || y>n-1 || x>n-1) return false;
		return true;
	}
}