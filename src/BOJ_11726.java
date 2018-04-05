import java.util.Scanner;

public class BOJ_11726 {
	// BOJ_11726

	static int[] dp = new int[1001];
	private static Scanner sc;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);

		int n = sc.nextInt();
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
			dp[i] %= 10007;
		}
		System.out.println(dp[n]);
		sc.close();
	}

}