import java.math.BigInteger;
import java.util.Scanner;

public class BOJ_10844 {
	
	private static Scanner sc;
	
	static BigInteger[] count = new BigInteger[10];

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
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
	
}