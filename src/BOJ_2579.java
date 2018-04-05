import java.util.Scanner;

public class BOJ_2579 {
	
	private static Scanner sc;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		long[][] d = new long[n][3];
		d[0][1] = arr[0];
		d[0][2] = 0;
		d[1][1] = d[0][1] + arr[1];
		d[1][2] = arr[1];
		for(int i=2;i<n;i++) {
			d[i][1] = d[i-1][2] + arr[i];
			d[i][2] = Math.max(d[i-2][1], d[i-2][2]) + arr[i];
		}
		System.out.println(Math.max(d[n-1][1], d[n-1][2]));
	}
}
