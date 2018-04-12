import java.util.ArrayList;
import java.util.Scanner;

public class SWE_4012 {
	private static Scanner sc;
	static int[][] map;
	static int n;
	
	static int minValue = 10000000;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=0;t<T;t++) {
			n = sc.nextInt();
			map = new int[n+1][n+1];
			minValue = 10000000;
			
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i=1;i<=n;i++) {
//				System.out.println("index: "+i);
				dfs(i, new ArrayList<Integer>());
//				System.out.println();
			}
			
			System.out.println("#"+(t+1) + " "+ minValue);
		}
	}
	
	static void dfs(int index, ArrayList<Integer> q) {
		ArrayList<Integer> newQ = (ArrayList<Integer>) q.clone();
		newQ.add(index);
		if(newQ.size() == n/2) calculate(newQ); 
//			System.out.println(newQ);
			
		for(int i=index+1;i<=n;i++) {
			if(i != index) {
				dfs(i, newQ);
			}
		}
	}
	
	static void calculate(ArrayList<Integer> menuA) {
		
		ArrayList<Integer> menuB = new ArrayList<>();
		for(int i=1;i<=n;i++) {
			if(!menuA.contains(i)) menuB.add(i);
		}
		
		int aSum = 0;
		int bSum = 0;
		for(final int i:menuA) {
			for(final int j:menuA) {
				if(i!=j) aSum += map[i][j];
			}
		}
		for(final int i:menuB) {
			for(final int j:menuB) {
				if(i!=j) bSum += map[i][j];
			}
		}
		
		int result = aSum - bSum;
		if(result<0) result*=-1;
		if(result < minValue) minValue = result;
	}
}