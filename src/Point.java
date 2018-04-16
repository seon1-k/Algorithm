public class Point{
	int x, y, length = 0;
	
	public Point(int x, int y) {
		this.x = x; this.y = y;
	}
	public Point(int x, int y, int l) {
		this.x = x; this.y = y; this.length = l;
	}
	void print() {
		System.out.print("("+this.x + ", " + this.y+") ");
	}
	
	void setLength(int l) {
		this.length = l;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + x;
		result = prime * result + y;
		return result;
//		return Objects.hash(x, y, length);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (length != other.length)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
//		return Objects.equals(x, y, length);
	}
	
	boolean equal(Point p) {
		if(this.x == p.x && this.y == p.y) return true;
		return false;
	}
}