import java.util.Scanner;

public class BOJ_14499 {
	private static Scanner sc;
	
	static int[] box;
	static int[][] map;
	static int n,m, fX, fY, k;
	
	static int cX, cY; //current
	
	static int dx[] = {-1, 0, 0, -1, 1};
	static int dy[] = {-1, 1, -1, 0, 0};
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		box = new int[7];
		n = sc.nextInt(); m = sc.nextInt(); fX = sc.nextInt(); fY = sc.nextInt(); k = sc.nextInt();
		map = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		cX = fX; cY = fY;
		for(int i=0;i<k;i++) {
			int dir = sc.nextInt();
			dfs(cX, cY, dir);
		}
		sc.close();
	}
	
	static void dfs(int x, int y, int d) {
//		System.out.println("x: "+x+", y: "+y+", d: "+d);
		int nX = x + dx[d]; int nY = y+dy[d];
		if(!check(nX, nY)) return;
//		System.out.println("moveTo x: "+nX+" y:"+nY);
		
		moveBox(d);
		
		if(map[nX][nY] == 0) {
			map[nX][nY] = box[6];
		} else {
			box[6] = map[nX][nY];
			map[nX][nY] = 0;
		}
		
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) System.out.print(map[i][j] + " ");
//			System.out.println();
//		}
//		printBox();
//		System.out.println();
		
		System.out.println(box[1]);
		cX = nX; cY = nY;
	}
	
	static void moveBox(int direction) {
		int[] newBox = new int[7];
		switch(direction) {
		case 1://동(우) 
			newBox[5] = box[5];
			newBox[2] = box[2];
			newBox[1] = box[4];
			newBox[3] = box[1];
			newBox[4] = box[6];
			newBox[6] = box[3];
			break;
		case 2:
			newBox[5] = box[5];
			newBox[2] = box[2];
			newBox[4] = box[1];
			newBox[1] = box[3];
			newBox[3] = box[6];
			newBox[6] = box[4];
			break;
		case 3: //북(위) 
			newBox[4] = box[4];
			newBox[3] = box[3];
			newBox[2] = box[1];
			newBox[1] = box[5];
			newBox[5] = box[6];
			newBox[6] = box[2];
			break;
		case 4:
			newBox[4] = box[4];
			newBox[3] = box[3];
			newBox[6] = box[5];
			newBox[5] = box[1];
			newBox[1] = box[2];
			newBox[2] = box[6];
			break;
		}
		box = newBox.clone();
	}
	
	static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) return false;
		return true;
	}
	
	static void printBox() {
		System.out.println(box[2]);
		System.out.println(box[4] + " " + box[1] + " " + box[3]);
		System.out.println(box[5]);
		System.out.println(box[6]);
	}
}