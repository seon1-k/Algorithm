import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_3190 {
	private static Scanner sc;
	static boolean showLog = false;
	
	static int n, k, l;
	
	static int map[][];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// 2:»ç°ú
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		map = new int[n+1][n+1];
		for(int i=0;i<k;i++) {
			int x = sc.nextInt(); int y = sc.nextInt();
			map[x][y] = 2;
		}
		
		l = sc.nextInt();
		
		
		Queue<Change3190> c = new LinkedList<>();
		Queue<Turn3190> cTurn3190 = new LinkedList<>();
		
		for(int i=0;i<l;i++) {
			int x = sc.nextInt(); char ch = sc.next().charAt(0);
			c.offer(new Change3190(x, ch));
		}
		
		int cX = 1; int cY = 1; //ÇöÀç ÁÂÇ¥
		int tX = 1; int tY = 1; //²¿¸® ÁÂÇ¥
		int cLength = 1; //ÇöÀç ¸ö ±æÀÌ
		int time = 1;
		int currentDir = 3; //0:»ó 1:ÇÏ 2:ÁÂ 3:¿ì
		
		int tailDir = currentDir;
		int tailValue = 0;
		
		map[cX][cY] = 1;
		while(true) {
			if(showLog) System.out.println("time: "+time);

			if(!c.isEmpty() && c.element().time+1 == time) {
				currentDir = changeDir(currentDir, c.poll().c);
				cTurn3190.offer(new Turn3190(cX, cY, currentDir));
			}
			
			int nX = cX+dx[currentDir], nY = cY+dy[currentDir];
			if(!check(nX, nY)) break;
			if(showLog) System.out.println("nX: "+nX+" nY: "+nY);
			if(map[nX][nY] == 1) break;
			if(map[nX][nY] == 2) { //»ç°ú°¡ ÀÕÀ¸¸é
				 k--; cLength++;
			} else {
				map[tX][tY] = 0;
				if(!cTurn3190.isEmpty() && cTurn3190.element().x == tX && cTurn3190.element().y == tY) {
					tailDir = cTurn3190.poll().dir;
				}
				tX += dx[tailDir]; tY += dy[tailDir];
			}
			map[nX][nY] = 1;
			cX = nX; cY = nY;
			

			if(showLog) System.out.println("t: "+tX+" "+tY);
			time++;
			
			if(showLog) {
				for(int i=1;i<=n;i++) {
					for(int j=1;j<=n;j++) {
						System.out.print(map[i][j]+ " ");
					}
					System.out.println();
				}
				System.out.println();
			}
		}
		
		System.out.println(time);
	}
	
	static boolean check(int x, int y) {
		if(x<1 || y<1 || x>n || y>n) return false;
		return true;
	}
	
	static int changeDir(int dir, char ch) {
		switch (dir) {
		case 0:
			if(ch == 'L') return 2;
			if(ch == 'D') return 3;
		case 1:
			if(ch == 'L') return 3;
			if(ch == 'D') return 2;
		case 2:
			if(ch == 'L') return 1;
			if(ch == 'D') return 0;
		case 3:
			if(ch == 'L') return 0;
			if(ch == 'D') return 1;
		}
		return dir;
	}
}

class Turn3190 {
	int x, y, dir;
	public Turn3190(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

class Change3190 {
	int time;
	char c;
	public Change3190(int x, char c) {
		this.time = x;
		this.c = c;
	}
}
