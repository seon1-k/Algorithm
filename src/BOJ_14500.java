import java.util.Scanner;

public class BOJ_14500 {
	private static Scanner sc;
	
	static int n, m;
	static int[][] map;
	
	static int maxValue = -1;
	
	static int dx2[] = { 0, 0, 1, 1};
	static int dy2[] = { 0, 1, 0, 1};
	
	static int dx31[] = {0, 0, 1, 2};
	static int dy31[] = {0, 1, 0, 0};
	static int dx32[] = {0,0,1,2};
	static int dy32[] = {0,1,1,1};
	static int dx33[] = {0,0,0,1};
	static int dy33[] = {0,1,2,2};
	static int dx34[] = {0,0,0,-1};
	static int dy34[] = {0,1,2,2};
	static int dx35[] = {0,1,2,2};
	static int dy35[] = {0,0,0,-1};
	static int dx36[] = {0,1,2,2};
	static int dy36[] = {0,0,0,1};
	static int dx37[] = {0,1,1,1};
	static int dy37[] = {0,0,1,2};
	static int dx38[] = {0,0,0,1};
	static int dy38[] = {0,1,2,0};
	
	static int dx41[] = {0,1,1,2};
	static int dy41[] = {0,0,1,1};
	static int dx42[] = {0,1,1,2};
	static int dy42[] = {0,0,-1,-1};
	static int dx43[] = {0,0,-1,-1};
	static int dy43[] = {0,1,1,2};
	static int dx44[] = {0,0,1,1};
	static int dy44[] = {0,1,1,2};
	
	static int dx51[] = {0,0,-1,0};
	static int dy51[] = {0,-1,0,1};
	static int dx52[] = {0,0,1,0};
	static int dy52[] = {0,-1,0,1};
	static int dx53[] = {0,-1,0,1};
	static int dy53[] = {0,0,1,0};
	static int dx54[] = {0,-1,0,1};
	static int dy54[] = {0,0,-1,0};
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		n = sc.nextInt(); m = sc.nextInt();
		map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				select(i, j);
			}
		}
		
		System.out.println(maxValue);
	}
	
	static void select(int x, int y) {
		// 1
		int sum = 0;
		int count = 0;
		if(y < m-4+1) {
			for(int i=0;i<4;i++) {
				int nY = y+i;
				if(!check(x, nY)) break;
				sum += map[x][nY];
				count++;
			}
			if(sum > maxValue && count == 4) maxValue = sum;
		}
		
		//1-2
		sum = 0; count = 0;
		if(x < n-4+1) {
			for(int i=0;i<4;i++) {
				int nX = x+i;
				if(!check(nX, y)) break;
				sum += map[nX][y];
				count++;
			}
			if(sum > maxValue && count == 4) maxValue = sum;
		}
		
		//2
		sum = 0; count = 0;
		if(x < n-2+1 && y < m-2+1) {
			setMax(x, y, dx2, dy2);
		}
		
		//3-1, 3-2, 3-6
		if(x < n-3+1 && y < m-2+1) {
			int[][] dxx = new int[3][4];
			int[][] dyy = new int[3][4];
			dxx[0] = dx31.clone();
			dyy[0] = dy31.clone();
			dxx[1] = dx32.clone();
			dyy[1] = dy32.clone();
			dxx[2] = dx36.clone();
			dyy[2] = dy36.clone();
			
			for(int t=0;t<dxx.length;t++) {
				int[] dx = dxx[t].clone();
				int[] dy = dyy[t].clone();
				
				setMax(x, y, dx, dy);
			}
		}
		
		// 3-3, 3-7, 3-8
		
		if(x < n-2+1 && y < m-3+1) {
			int[][] dxx = new int[3][4];
			int[][] dyy = new int[3][4];
			dxx[0] = dx33.clone();
			dyy[0] = dy33.clone();
			dxx[1] = dx37.clone();
			dyy[1] = dy37.clone();
			dxx[2] = dx38.clone();
			dyy[2] = dy38.clone();
			
			for(int t=0;t<dxx.length;t++) {
				int[] dx = dxx[t].clone();
				int[] dy = dyy[t].clone();
				
				setMax(x, y, dx, dy);
			}
		}
		
		//3-4
		if(x < n && y < m-3+1) {
			setMax(x, y, dx34, dy34);
		}
		
		//3-5
		if(x < n-3+1 && y < m) {
			setMax(x, y, dx35, dy35);
		}
		
		setMax(x, y, dx41, dy41);
		setMax(x, y, dx42, dy42);
		setMax(x, y, dx43, dy43);
		setMax(x, y, dx44, dy44);
		setMax(x, y, dx51, dy51);
		setMax(x, y, dx52, dy52);
		setMax(x, y, dx53, dy53);
		setMax(x, y, dx54, dy54);
	}
	
	static void setMax(int x, int y, int[] dx, int[] dy) {
		int sum = 0, count = 0;
		for(int i=0;i<4;i++) {
			int nX = x+dx[i], nY = y+dy[i];
			if(!check(nX, nY)) break;
			sum += map[nX][nY];
			count++;
		}
		if(sum > maxValue && count == 4) maxValue = sum;
	}
	
	static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) return false;
		return true;
	}
}