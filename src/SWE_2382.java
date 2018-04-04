import java.util.ArrayList;
import java.util.Scanner;

public class SWE_2382 {
	static int n, m;
	static ArrayList<Virus> items = new ArrayList<Virus>();
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for(int i=0;i<t;i++){
			n = sc.nextInt();
			m = sc.nextInt();
			int k = sc.nextInt();
			
			items.clear();
			
			for(int j=0;j<k;j++){
				int y = sc.nextInt();
				int x = sc.nextInt();
				int count = sc.nextInt();
				int dir = sc.nextInt();
				items.add(new Virus(x, y, count, dir));
				}
			int sum = move();
			System.out.println("#" + (i+1) +" "+ sum);
		}
		sc.close();
	}
	static int move() {
		
		int sum = 0;
		
		for(int i=0;i<m;i++) { // 격리시간 m만큼 시도
			Virus[][] area = new Virus[n][n];
			ArrayList<Virus> tempItems = new ArrayList<>();
			
			for(final Virus v:items) {
				int newX = new Integer(v.x);
				int newY = new Integer(v.y);
				
				switch (v.direction) {
				case 1: //위
					newY--;
					break;
				case 2: //아래
					newY++;
					break;
				case 3:
					newX--;
					break;
				case 4: //우
					newX++;
					break;
				}
				
				Virus newVirus = new Virus(newX, newY, v.count, v.direction);
				
				if (area[newX][newY] == null) {
					// 새로 자리잡을 때는 삽
					if (newX == 0 || newX == n-1 || newY == 0 || newY == n-1) {
						newVirus.count /= 2;
						newVirus.changeDir();
					}
				} else {
					// 겹치는 자료가 있을 경우 파
					Virus current = area[newX][newY];
					if (current.maxCount != -1) {
						// 최대값이 존재하면 비교
						if (current.maxCount > newVirus.count) {
							// 기존이 더 크면 방향은 기존 방향 유지.
							newVirus.direction = current.direction;
							newVirus.maxCount = current.count;
						} else {
							newVirus.maxCount = newVirus.count;
						}
					} else {
						if (current.count > newVirus.count) {
							// 기존꺼보다 작으면
							newVirus.maxCount = current.count;
							newVirus.direction = current.direction;
						} else {
							// 기존꺼보다 크면
							newVirus.maxCount = newVirus.count;
						}
					}
					newVirus.count += current.count;
					tempItems.remove(current);
				}
				area[newX][newY] = newVirus;
				tempItems.add(newVirus);
			} // end Virus loop
			items = new ArrayList<>(tempItems);
		}
		for(final Virus item:items) {
			sum += item.count;
		}
		return sum;
	}
}

class Virus {
	int x, y, count, direction;
	int maxCount = -1;
	public Virus() { }
	public Virus(int x, int y, int c, int d) {
		this.x = x;
		this.y = y;
		this.count = c;
		this.direction = d;
		this.maxCount = -1;
	}
	void changeDir(){
		switch(direction) {
		case 1:
			this.direction = 2;
			break;
		case 2:
			this.direction = 1;
			break;
		case 3:
			this.direction = 4;
			break;
		case 4:
			this.direction = 3;
			break;
		}
	}
}