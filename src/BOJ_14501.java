import java.util.Scanner;

public class BOJ_14501 {
	private static Scanner sc;
	
	static int n;
	static int[] tArr, pArr;
	
	static int maxValue = -1;
	static boolean showLog = false;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		n = sc.nextInt();
		tArr = new int[n+1]; pArr = new int[n+1];
		for(int i=1;i<=n;i++) {
			tArr[i] = sc.nextInt();
			pArr[i] = sc.nextInt();
		}
		
		for(int i=1;i<=n;i++) {
			dfs(i, 0);
			if(showLog) System.out.println();
		}
		
		System.out.println(maxValue);
	}
	
	static void dfs(int i, int sum) {
		if(showLog) System.out.println("i: "+i+", sum: "+sum);
		int nextDay = i + tArr[i];
		int newSum = sum;
		
		if(nextDay <= n+1) {
			newSum += pArr[i];
		}
		
		if(nextDay <= n) {
			for(int j=nextDay;j<=n;j++) {
				dfs(j, newSum);	
			}
		} else {
			if(maxValue < newSum) {
				if(showLog) System.out.println("setMaxValue: "+newSum+", i: "+i);
				maxValue = newSum;
			}
		}
	}
}