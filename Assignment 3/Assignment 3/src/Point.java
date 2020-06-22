
public class Point {
	private int x, y ;
	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	public int x(){
		return x;
	}
	public int y(){
		return y;
	} 
	public double distanceTo(Point z){
		double dx = x- z.x, dy = y-z.y;
		return Math.sqrt(dx*dx+dy*dy);
	} 
	public int squareDistanceTo(Point z){
		int dx = x- z.x, dy = y-z.y;
		return dx*dx+dy*dy ;
	} // square of the Euclidean
	//distance between two points
	public String toString(){
		return "(" + x + "," + y + ")";
	} // string representation: (x, y)

}
