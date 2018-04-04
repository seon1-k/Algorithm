import java.util.Scanner;

public class BOJ_9095 {
	
	static int[] dp = new int[12];
	static int maxInt = 3;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i=0;i<t;i++) {
			int n = sc.nextInt();
			System.out.println(go(n));
		}
	}
	
	static int go(int n) {
		if (n<=2) return dp[n];
		
		for(int i=maxInt;i<=n;i++) {
//			System.out.println("for loop : " + i);
			dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
		}
		if(maxInt < n) maxInt = n;
		return dp[n];
	}
}