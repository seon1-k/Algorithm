import java.math.BigInteger;
import java.util.Scanner;

public class BOJ_10844 {
	
	private static Scanner sc;
	private static long mod = 1000000000L;
	
	static BigInteger[] count = new BigInteger[10];
	
	static long[][] d;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		d = new long[n+1][10];
		////
//		18404112
		for(int i=1;i<=9;i++) d[1][i] = 1;
		for(int i=2;i<=n;i++) {
			for(int j=0;j<10;j++) {
				d[i][j] = 0;
				if(j-1 >= 0) d[i][j] += d[i-1][j-1];
				if(j+1 <= 9) d[i][j] += d[i-1][j+1];
				d[i][j] %= mod;
			}
		}
		
		long sum1 = 0;
		for(int i=0;i<10;i++) {
			sum1 += d[n][i];
		}
		sum1 %= mod;
		System.out.println(sum1);
		
		////
		
		for(int i=1;i<=9;i++) {
			count[i] = new BigInteger("1");
		}
		count[0] = new BigInteger("0");
		
		
		go(n);
		
		
		BigInteger sum = new BigInteger("0");
		for(int i=0;i<=9;i++) {
			sum = sum.add(count[i]);
		}
		sum = sum.remainder(new BigInteger("1000000000"));
		System.out.println(sum);
		sc.close();
	}
	
	static void go(int n) {
		for(int i=2;i<=n;i++) {
			BigInteger[] newArr = new BigInteger[10];
			newArr[0] = count[1];
			newArr[9] = count[8];
			
			for (int j=1;j<=8;j++) {
				newArr[j] = count[j-1].add(count[j+1]);
			}
			
			count = newArr;
		}
	}
	
	static void go2(int n) {
		
	}
	
}