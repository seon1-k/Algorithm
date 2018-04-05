import java.util.Scanner;

public class BOJ_11057 {
	
	private static Scanner sc;
	
	private static final int MOD = 10007;
	
	static long[][] d;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		d = new long[n+1][10];
		
		for(int i=0;i<10;i++) {
			d[1][i] = 1;
		}
		for(int i=2;i<=n;i++) {
			d[i][0] = 1;
			for(int j=1;j<10;j++) {
				d[i][j] = 0;
				for(int k=0;k<=j;k++) {
					d[i][j] += d[i-1][k];
					d[i][j] %= 10007;
				}
			}
		}
		
		long sum = 0;
		for(int i=0;i<10;i++) {
			sum += d[n][i];
		}
		sum %= 10007;
		System.out.println(sum);
		
		sc.close();
	}
}