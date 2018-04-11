import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

class SWE_2115 {
	private static Scanner sc;

	static int maxValue;
	
	static int tempMaxValue; //합 구할떄 쓰는 최대값 value
	static ArrayList<Integer> tempArray;
//	static int minValue;
	
	static int n, m, c;
	
//	static int dx[];
	
	static int[][] honey;
	static boolean[][] isVisited;

	static Point[] first; //농부가 선택한 좌
	static Point[] second;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0;t<T;t++) {
			n = sc.nextInt();
			m = sc.nextInt();
			c = sc.nextInt();
			
			maxValue = -1;
			
			honey = new int[n][n];
			isVisited = new boolean[n][n];
			first = new Point[m];
			second = new Point[m];
			
//			dx = new int[m-1];
//			for(int i=0;i<m-1;i++) {
//				dx[i] = i+1;
//			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					honey[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(checked(i,j)) bfs(i, j);
				}
			}
			
			System.out.println("#"+(t+1)+" "+maxValue);
		}
	}
	
	static void bfs(int x, int y) {
//		System.out.println("start: "+x+", "+y+ " " +getSum(x,y));
		for(int i=y+m; i<=n-m ; i++) {
//			System.out.println(x+", "+i+" "+getSum(x, i));
			int value = getSum(x,y, true) + getSum(x, i, false);
			if(value > maxValue) maxValue = value;
		}
		for(int i =x+1 ; i<n ; i++) {
			for(int j=0; j<=n-m ; j++) {
//				System.out.println(i+", "+j+" "+getSum(i, j));
				int value = getSum(x,y, true) + getSum(i, j, false);
				if(value > maxValue) maxValue = value;
			}
		}
	}
	
	static void dfs(int x, int[] data, boolean[] visited, ArrayList<Integer> result) {
		visited[x] = true;
//		System.out.print(data[x]+" ");
		result.add(data[x]);
//		System.out.println(result);
		int originalSum = 0;
		int powSum = 0;
		for(int i=0;i<result.size();i++) {
			powSum += Math.pow(result.get(i), 2);
			originalSum += result.get(i);
		}
		if(powSum > tempMaxValue && originalSum <= c) {
			tempMaxValue = powSum;
			tempArray = result;
//			System.out.println("max: "+powSum);
		}
		for(int i=x+1;i<m;i++) {
			dfs(i, data, visited.clone(), (ArrayList<Integer>) result.clone());
		}
	}
	
	static void print(int[] arr, int length) { 
		for (int i = 0; i < length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println(""); 
	}
	
	static int getSum(int x, int y, boolean isOrigin) {
		if(!checked(x, y)) return 0;
		int[] array = new int[m];
		for(int i=0;i<m;i++) {
			array[i] = honey[x][y+i];
		}
//		if(isOrigin) System.out.print("origin ");
//		System.out.print("array: ");
//		print(array, array.length);
		
		tempMaxValue = -1;
		tempArray = null;
		for(int i=0;i<array.length;i++) {
			dfs(i, array, new boolean[array.length], new ArrayList<Integer>());
		}
		
		int sum = 0;
//		System.out.println("tempArray: " +tempArray);
		for(int i=0;i<tempArray.size();i++) {
			sum += Math.pow(tempArray.get(i), 2);
		}
		
		return sum;
	}
	
	static boolean checked(int x, int y) {
		if (x<0 || y<0 || x>=n || y>n-m) return false;
		return true;
	}
}
