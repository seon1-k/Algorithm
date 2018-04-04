import java.util.Scanner;

public class Main {
	// BOJ_11726
	
	static int[] dp = new int[1001];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2;i<=n;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		int result = dp[n] % 10007;
		System.out.println(result);
		sc.close();
	}
	
}