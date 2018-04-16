import java.util.Scanner;

public class BOJ_13458 {
	private static Scanner sc;
	static boolean showLog = false;
	
	static int[] numArr;
	
	static long sum = 0; 
	static int b, c;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
	
//		int n = 1000000;
		int n = sc.nextInt();
		numArr = new int[n];
		for(int i=0;i<n;i++) {
			numArr[i] = sc.nextInt();
//			numArr[i] = 1000000;
		}
		b = sc.nextInt();
		c = sc.nextInt();
//		b = 1; c= 1;
		sum = n;
		for(int i=0;i<n;i++) {
			int value = numArr[i] - b;
			if(value <= c && value > 0) {
				sum += 1;
			} else if (value > c) {
				if(value%c == 0) sum += value/c;
				else sum += (value/c + 1);
			}
		}
		
		System.out.println(sum);
	}
}
