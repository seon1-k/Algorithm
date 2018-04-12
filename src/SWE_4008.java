import java.util.ArrayList;
import java.util.Scanner;

public class SWE_4008 {
	private static Scanner sc;

	static int maxValue;
	static int minValue;
	
	static int n;
	static int[] numbers;
//	static int[] datas; // 수식, + - * /
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0;t<T;t++) {
			n = sc.nextInt();
			
			numbers = new int[n];
			int[] datas = new int[4];
			maxValue = -100000000;
			minValue = 1000000000;
			
			for(int i=0;i<4;i++) {
				datas[i] = sc.nextInt();
			}
			for(int i=0;i<n;i++) {
				numbers[i] = sc.nextInt();
			}
			loop(datas, new ArrayList<Integer>());
			
			System.out.println("#"+(t+1)+" "+(maxValue - minValue));
		}
	}
	
	static void loop(int datas[], ArrayList<Integer> results) {
//		System.out.println(results);
		
		if(results.size() == n-1) {
			System.out.println(results);
			calculate(results);
			return;
		}
		for(int i=0;i<4;i++) { // i는 연산자 종류 
			if(datas[i] > 0) {
				int[] newDatas = datas.clone();
				newDatas[i]--;
				ArrayList<Integer> newResults = (ArrayList<Integer>) results.clone();
				newResults.add(i);
				loop(newDatas, newResults);
			}
		}
	}
	
	static void calculate(ArrayList<Integer> results) {
		if(results.size() != n-1) return;
		
		int sum = numbers[0];
		for(int i=0;i<n-1;i++) {
//			System.out.println(sum);
			switch(results.get(i)) {
			case 0:
				sum += numbers[i+1];
				break;
			case 1:
				sum -= numbers[i+1];
				break;
			case 2:
				sum *= numbers[i+1];
				break;
			case 3:
				sum /= numbers[i+1];
				break;
			}
		}
		
//		System.out.print("sum: "+sum+", ");
		if(sum > maxValue) maxValue = sum;
		if(sum < minValue) minValue = sum;
//		System.out.print("max: "+maxValue+", ");
//		System.out.println("min: "+minValue);
		return;
	}
}