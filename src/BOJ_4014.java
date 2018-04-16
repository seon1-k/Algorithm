import java.util.Scanner;

public class BOJ_4014 {
	private static Scanner sc;
	
	static boolean showLog = false;
	
	static int n, x, sum;
	static int map[][];
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=0;t<T;t++) {
			n = sc.nextInt(); x = sc.nextInt();
			map = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			sum = 0;
			for(int i=0;i<n;i++) {
				if(check(map[i])) { 
					sum++;
					if(showLog) System.out.println("true");
				}
			}
			for(int j=0;j<n;j++) {
				int arr[] = new int[n];
				for(int i=0;i<n;i++) {
					arr[i] = map[i][j];
				}
				if(check(arr)) {
					sum++;
					if(showLog) System.out.println("true");
				}
			}
			
			System.out.println("#"+(t+1)+" "+sum);
		}
	}

	static boolean check(int[] a) {
		if(showLog) {
			for(int i=0;i<a.length;i++) {
				System.out.print(a[i]+" ");
			}
			System.out.println();
		}
		
		boolean[] upCliff = new boolean[a.length];
		boolean[] downCliff = new boolean[a.length];
		
		for(int i=0;i<n-1;i++) {
			if(a[i] - a[i+1] == 1) { //ÇÏÇâ
				for(int j=i+2;j<=i+x;j++) {
					if(j >= n) return false;
					if(a[i+1] != a[j]) return false;
					downCliff[j] = true;
				}
				downCliff[i+1] = true;
			}
			else if(a[i+1] - a[i] == 1) {
				for(int j=i-1;j>=i-x+1;j--) {
					if(j<0) return false;
					if(a[i] != a[j]) return false;
					upCliff[j] = true;
				}
				upCliff[i] = true;
			} 
			else if(a[i] != a[i+1]) return false;
		}
		for(int i=0;i<a.length;i++) {
			if(upCliff[i] && downCliff[i]) return false;
		}
		
		return true;
	}
}
