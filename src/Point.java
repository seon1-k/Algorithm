public class Point {
	int x, y, length = 0;
	
	public Point(int x, int y) {
		this.x = x; this.y = y;
	}
	public Point(int x, int y, int l) {
		this.x = x; this.y = y; this.length = l;
	}
	void print() {
		System.out.println(this.x + ", " + this.y);
	}
}