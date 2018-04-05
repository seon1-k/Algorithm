import java.util.Scanner;

public class BOJ_14891 {
	
	static int[][] arr = new int[4][8];
	static boolean chain1, chain2, chain3;
	private static Scanner sc;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		for(int i=0;i<4;i++) {
			String input = sc.nextLine();
			for(int j=0;j<8;j++) {
				arr[i][j] = input.charAt(j) - '0';
			}
		}
		int k = sc.nextInt();
		for(int i=0;i<k;i++) {
			int num = sc.nextInt();
			int rotate = sc.nextInt();
			rotate(num, rotate, 0);
		}
		System.out.println(getResult());
		sc.close();
	}
	
	static void rotate(int num, int rot, int rotateType) {
		int aRight = arr[0][2];
		int bLeft = arr[1][6];
		int bRight = arr[1][2];
		
		int cLeft = arr[2][6];
		int cRight = arr[2][2];
		
		int dLeft = arr[3][6];
		
		chain1 = (aRight != bLeft) ? true : false;
		chain2 = (bRight != cLeft) ? true : false;
		chain3 = (cRight != dLeft) ? true : false;
		
		
		justRotate(num, rot);
		
		int rRot = rot * -1;
		switch (num) {
		case 1:
			subRotate(2, rRot, num);
			break;
		case 2:
			subRotate(1, rRot, num);
			subRotate(3, rRot, num);
			break;
		case 3:
			subRotate(2, rRot, num);
			subRotate(4, rRot, num);
			break;
		case 4:
			subRotate(3, rRot, num);
			break;
		}
	}
	
	static void subRotate(int num, int rot, int parents) {
//		System.out.println(chain1 + " " +chain2+ " "+chain3);
		switch (num) {
		case 1:
			if (chain1) justRotate(num, rot);
			break;
		case 2:
			if ((parents == 1 && chain1) || (parents == 3 && chain2)) { 
				justRotate(num, rot);
				if (parents == 1) subRotate(3, rot * -1, num);
				if (parents == 3) subRotate(1, rot * -1, num);
			}
			break;
		case 3:
			if ((parents == 2 && chain2) || (parents == 4 && chain3)) {
				justRotate(num, rot);
				if (parents == 2) subRotate(4, rot * -1, num);
				if (parents == 4) subRotate(2, rot * -1, num);
			}
			break;
		case 4:
			if (chain3) justRotate(num, rot);
			break;
		}
	}
	

	static void justRotate(int num, int rot) {
//		System.out.println("num: "+num+" rot: "+rot);
		int[] result = new int[8];
		int[] array = arr[num-1];
		if (rot == -1) {
			result[7] = array[0];
			for(int i=0;i<7;i++) {
				result[i] = array[i+1];
			}
		} else {
			result[0] = array[7];
			for(int i=1;i<8;i++) {
				result[i] = array[i-1];
			}
		}
		arr[num-1] = result;
	}
	
	static int getResult() {
		return arr[0][0] * 1 + arr[1][0] * 2 + arr[2][0] * 4 + arr[3][0] * 8;
	}
}