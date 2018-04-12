import java.util.Scanner;

//failed
public class BOJ_13460 {
	private static Scanner sc;
//	static char maze[][];
	
//	static Point red;
//	static Point blue;
	static Point whole;
	
	static int minValue = 10000;
	static int n, m;
	
	static boolean showLog = false;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		char[][] maze = new char[n][m];
		for(int i=0;i<n;i++) {
			String temp = sc.next();
			for(int j=0;j<m;j++) {
				maze[i][j] = temp.charAt(j);
//				if(maze[i][j] == 'B') blue = new Point(i, j);
//				else if(maze[i][j] == 'R') red = new Point(i, j);
				if(maze[i][j] == 'O') whole = new Point(i, j);
			}
		}
		
		loop(maze, 0, 1);
		loop(maze, 1, 1);
		loop(maze, 2, 1);
		loop(maze, 3, 1);
		
		
		if(minValue >= 10000) {
			System.out.println(-1);
		} else {
			System.out.println(minValue);
		}
	}
	
	static void loop(char[][] maze, int direction, int length) {
//		0위 1아래 2좌 3우
//		System.out.println(direction + " " +length);
		char[][] newMaze = copyArr(maze);
		
		Point blue = find(newMaze, 'B');
		Point red = find(newMaze, 'R');
		
		switch (direction) {
		case 0://위 
			if(blue.y == red.y) { // 같은 y좌표면 누가 먼저 움직일지정해야함!
				if(blue.x < red.x) { //블루 먼저 
					newMaze = move(newMaze, direction, 'B');
					newMaze = move(newMaze, direction, 'R');
				} else { // 레드 먼
					newMaze = move(newMaze,direction, 'R');
					newMaze = move(newMaze,direction, 'B');
				}
			} else {
				newMaze = move(newMaze,direction, 'R');
				newMaze = move(newMaze,direction, 'B');
			}
			break;
		case 1: //아래
			if(blue.y == red.y) { // 같은 y좌표면 누가 먼저 움직일지정해야함!
				if(blue.x < red.x) {
					newMaze = move(newMaze,direction, 'R');
					newMaze = move(newMaze,direction, 'B');
				} else { // 레드 먼
					newMaze = move(newMaze,direction, 'B');
					newMaze = move(newMaze,direction, 'R');
				}
			} else {
				newMaze = move(newMaze,direction, 'R');
				newMaze = move(newMaze,direction, 'B');
			}
			break;
		case 2://좌 
			if(blue.x == red.x) { // 같은 y좌표면 누가 먼저 움직일지정해야함!
				if(blue.x < red.x) {
					newMaze = move(newMaze, direction, 'B');
					newMaze = move(newMaze, direction, 'R');
				} else { // 레드 먼
					newMaze = move(newMaze, direction, 'R');
					newMaze = move(newMaze, direction, 'B');
				}
			} else {
				newMaze = move(newMaze, direction, 'R');
				newMaze = move(newMaze, direction, 'B');
			}
			break;
		case 3:
			if(blue.x == red.x) { // 같은 y좌표면 누가 먼저 움직일지정해야함!
				if(blue.x < red.x) {
					newMaze = move(newMaze, direction, 'R');
					newMaze = move(newMaze, direction, 'B');
				} else { // 레드 먼
					newMaze = move(newMaze, direction, 'B');
					newMaze = move(newMaze, direction, 'R');
				}
			} else {
				newMaze = move(newMaze, direction, 'R');
				newMaze = move(newMaze, direction, 'B');
			}
			break;
		}
		
		Point doubleOut = find(newMaze, 'X');
		Point failed = new Point(-1, -1);
		if(!doubleOut.equal(failed)) return;
		
		Point newBlue = find(newMaze, 'B');
		Point newRed = find(newMaze, 'R');
//		if(red.equal(newRed) || blue.equal(newBlue)) return;
		
//		if(newBlue.equal(failed) || newRed.equal(failed)) {
//			return;
//		}
		
		
		if(newBlue.equal(whole)) return;
		if(newRed.equal(whole)) {
			//성공
			if(length < minValue) {
				minValue = length;
//				System.out.println(length);
			}
			return;
		}
		
		if(length > 10) return;
		if(direction == 0 || direction == 1) {
			loop(newMaze, 2, length+1);
			loop(newMaze, 3, length+1);
		} else {
			loop(newMaze, 0, length+1);
			loop(newMaze, 1, length+1);
		}
	}
	
	static char[][] move (char[][] maze, int direction, char color) {
		if(showLog) System.out.println(maze+" " +direction+ " "+color);
		char[][] newMaze = copyArr(maze);
		Point temp = find(newMaze, color);
		if(temp.equal(new Point(-1, -1))) {
			// 두개가 동시에 빠진 경우
			return maze;
		}
		newMaze[temp.x][temp.y] = '.'; 
		
		switch (direction) {
		case 0:
			//
			while(temp.x-1 >= 0) {
				Point upper = new Point(temp.x-1, temp.y);
				int nextValue = maze[upper.x][upper.y];
				if(nextValue == '#') {
					break;
				} 
				else if(nextValue == 'B' || nextValue == 'R') {
					if(upper.equal(whole)) {
						temp.x--; 
					}
					break;
				}
				else if(nextValue == '.') { temp.x--; }
				else if(nextValue == 'O') { temp.x--; break; }
			}
			break;
		case 1:
			//아래 
			while(temp.x+1 < n) {
				Point lower = new Point(temp.x+1, temp.y);
				int nextValue = maze[lower.x][lower.y];
				if(nextValue == '#') {
					break;
				} 
				else if(nextValue == 'B' || nextValue == 'R') {
					if(lower.equal(whole)) {
						temp.x++; 	
					}
					break;
				}
				else if(nextValue == '.') temp.x++;
				else if(nextValue == 'O') { temp.x++; break; }
			}
			break;
		case 2:
			//좌 
			while(temp.y-1 >= 0) {
				Point left = new Point(temp.x, temp.y-1);
				int nextValue = maze[left.x][left.y];
				if(nextValue == '#') {
					break;
				} 
				else if(nextValue == 'B' || nextValue == 'R') {
					if(left.equal(whole)) {
						temp.y--; 
					}
					break;
				}
				else if(nextValue == '.') temp.y--;
				else if(nextValue == 'O') { temp.y--; break; }
			}
			break;
		case 3:
			//우 
			while(temp.y+1 < m) {
				Point right = new Point(temp.x, temp.y+1);
				int nextValue = maze[right.x][right.y];
				if(nextValue == '#') {
					break;
				} 
				else if(nextValue == 'B' || nextValue == 'R') {
					if(right.equal(whole)) {
						temp.y++; 
					}
					break;
				}
				else if(nextValue == '.') temp.y++;
				else if(nextValue == 'O') { temp.y++; break; }
			}
			break;
		}
		
		if((newMaze[temp.x][temp.y] == 'R' && color == 'B') || (newMaze[temp.x][temp.y] == 'B' && color == 'R')) newMaze[temp.x][temp.y] = 'X';
		else newMaze[temp.x][temp.y] = color;

		if(showLog) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					System.out.print(newMaze[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		
		return newMaze;
	}
	
	static Point find(char[][] maze, char color) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(maze[i][j] == color) return new Point(i, j);
			}
		}
		return new Point(-1, -1);
	}
	
	static char[][] copyArr(char[][] maze){
		char[][] result = new char[maze.length][maze[0].length];
		for(int i=0;i<maze.length;i++) {
			result[i] = maze[i].clone();
		}
		return result;
	}
}
