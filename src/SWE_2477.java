import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class SWE_2477 {
	private static Scanner sc;
	
	static int n, m, k, a, b;
	
	static int[] aTime, bTime;
	static Person2477[] timeArr; 
	
	static Queue<Person2477> wait1, wait2; //진입전대기하는큐 
	static Person2477[] aCorner, bCorner;
	static boolean[] aCornerVisit, bCornerVisit;
	
	static Queue<Person2477> result;
	
	static int minTime = 1001;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=0;t<T;t++) {
			n = sc.nextInt(); m = sc.nextInt(); k = sc.nextInt(); a = sc.nextInt(); b = sc.nextInt();
			aTime = new int[n]; bTime = new int[m]; timeArr = new Person2477[k];
			for(int i=0;i<n;i++) aTime[i] = sc.nextInt();
			for(int i=0;i<m;i++) bTime[i] = sc.nextInt();
			for(int i=0;i<k;i++) {
				int temp = sc.nextInt();
				timeArr[i] = new Person2477(i+1, temp);
				if(temp < minTime) minTime = temp;
			}
			
			wait1 = new LinkedList<Person2477>();
			wait2 = new LinkedList<Person2477>();
			aCorner = new Person2477[aTime.length];
			bCorner = new Person2477[bTime.length];
			aCornerVisit = new boolean[aTime.length];
			bCornerVisit = new boolean[bTime.length];
			result = new LinkedList<Person2477>();
			

			dfs(0);
			
//			System.out.println(result);
			int sum = 0;
			for(final Person2477 p:result) {
//				System.out.println(p.oneNumber + " " + p.twoNumber);
				if(p.oneNumber == a && p.twoNumber == b) sum += p.num;
			}
			System.out.print("#"+(t+1)+" ");
			if(sum == 0) System.out.println(-1);
			else System.out.println(sum);
		}
	}
	
	static void dfs(int time) {
		for(int i=0;i<bCorner.length;i++) {
			if(bCornerVisit[i]) {
				//방문상태인사람 시간 체크해서 내보내기
				Person2477 p = bCorner[i];
				if(time - p.twoArriveTime == bTime[i]) {
					bCornerVisit[i] = false;
					result.add(p);
				}
			}
		}
		
		for(int i=0;i<bCorner.length;i++) {
			if(!bCornerVisit[i] && !wait2.isEmpty()) {
				bCornerVisit[i] = true;
				bCorner[i] = wait2.poll();
				bCorner[i].arriveCorner2(i, time);
			}
		}
		
		for(int i=0;i<aCorner.length;i++) {
			if(aCornerVisit[i]) {
				Person2477 p = aCorner[i];
				if(time - p.oneArriveTime == aTime[i]) {
					aCornerVisit[i] = false;
					
					boolean isInserted = false;
					for(int j=0;j<bCorner.length;j++) {
						if(!bCornerVisit[j]) {
							isInserted = true;
							bCorner[j] = p;
							bCornerVisit[j] = true;
							bCorner[j].arriveCorner2(j, time);
							break;
						}
					}
					if(!isInserted) wait2.add(p);
				}
			}
		}
		
		for(int i=0;i<aCorner.length;i++) {
			if(!aCornerVisit[i] && !wait1.isEmpty()) {
				aCornerVisit[i] = true;
				aCorner[i] = wait1.poll();
				aCorner[i].arriveCorner1(i, time);
			}
		}
		
		for(final Person2477 p:timeArr) {
			//도착하는 사람들 정리(마지막순위)
			if (p.time == time) {
				boolean isInserted = false;
				for(int i=0;i<aCorner.length;i++) {
					if(!aCornerVisit[i]) {
						aCorner[i] = p; 
						aCorner[i].arriveCorner1(i, time);
						aCornerVisit[i] = true;
						isInserted = true; 
						break;
					}
				}
				if(!isInserted) { //삽입안됐으면
					wait1.add(p);
				}
			}
		}
		
//		System.out.println("time : "+time);
//		System.out.println(wait1);
//		for(int i=0;i<bCorner.length;i++) {
//			if(bCornerVisit[i]) System.out.print(bCorner[i].num + " / ");
//			else System.out.print("n /");
////			System.out.print("/ ");
//		}
//		System.out.println();
		
		int aCount = getWaitCount(aCornerVisit);
		int bCount = getWaitCount(bCornerVisit);
		Queue<Person2477> w1 = wait1;
		Queue<Person2477> w2 = wait2;
		
		if(result.size() == k) return;
		
		dfs(time+1);
		
	}
	
	static int getWaitCount(boolean arr[]) {
		int sum = 0;
		for(final boolean a:arr) {
			if(a==true) sum++;
		}
		return sum;
	}
}

class Person2477 {
	int num, time;
	int oneNumber = -1;//1코너 방문인덱스
	int oneArriveTime = -1; //1코너 방문 시간
	int twoNumber = -1;//2코너 방문인덱스
	int twoArriveTime = -1; //2코너 방문 시간
	
	public Person2477(int num, int time) {
		this.num = num;
		this.time = time;
	}
	
	void print() {
		System.out.print(num+" "+time+" ");
	}
	
	void arriveCorner1(int num, int time) {
		this.oneNumber = num+1;
		this.oneArriveTime = time;
	}
	
	void arriveCorner2(int num, int time) {
		this.twoNumber = num+1;
		this.twoArriveTime = time;
	}
}