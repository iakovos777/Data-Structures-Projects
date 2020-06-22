
public class Rectangle {
	private int xmin, ymin, xmax, ymax;
	public Rectangle(int xmin, int ymin, int xmax, int ymax){
		if(xmin>=0&&xmin<=100){
			this.xmin=xmin;
		}
		else{
			throw new IllegalArgumentException("xmin wrong argument:"+Integer.toString(xmin));
		}
		if(ymin>=0&&ymin<=100){
			this.ymin= ymin;
		}
		else{
			throw new IllegalArgumentException("ymin wrong argument:"+Integer.toString(ymin));
		}
		if(xmax>=0&&xmax<=100&&xmax>xmin){
			this.xmax = xmax;
		}
		else{
			throw new IllegalArgumentException("xmax wrong argument:"+Integer.toString(xmin));
		}
		if(ymax>=0&&ymax<=100&&ymax>ymin){
			this.ymax = ymax ;
		}
		else{
			throw new IllegalArgumentException("ymax wrong argument:"+Integer.toString(ymax));
		}
		
		
	}
	
	public int xmin(){
		return xmin;
	} 
	public int ymin(){
		return ymin;
	} 
	public int xmax(){
		return xmax;
	} 
	public int ymax(){
		return ymax;
	}
	public boolean contains(Point p){
		if((p.x()>=xmin())&&(p.x()<=xmax())&&(p.y()>=ymin())&&(p.y()<=ymax())){
			return true;
		}
		else{
			return false;
		}
		
	} //does p belong to the rectangle?
	public boolean intersects(Rectangle that){
		if((this.xmin < that.xmax()) && (this.xmax > that.xmin()) && (this.ymax > that.ymin()) && (this.ymin < that.ymax())){
			return true;
		}
		else{
			return false;
		}
	} // do the two rectangles
	// intersect?
	public double distanceTo(Point p){
		double b=0.0;
		if(contains(p)==true){
			return b;
		}
		else{
			if(p.y()>ymax){
				if(p.x()<=xmin){
					b=(double)Math.sqrt(Math.pow(xmin-p.x(),2)+Math.pow(ymax-p.y(),2));
				}
				
				else if((p.x()>xmin)&&(p.x()<xmax)){
					b=(double)Math.abs(p.y()-ymax);
				}
				else if(p.x()>=xmax){
					b=(double)Math.sqrt(Math.pow(p.x()-xmax,2)+Math.pow(ymax-p.y(),2));
				}
				
			}
			else if(p.x()>xmax){
				if(p.y()>=ymax){
					b=(double)Math.sqrt(Math.pow(xmax-p.x(),2)+Math.pow(p.y()-ymax,2));
				}
				else if(p.y()<=ymin){
					b=(double)Math.sqrt(Math.pow(xmax-p.x(),2)+Math.pow(ymin-p.y(),2));
				}
				else if((p.y()>ymin)&&(p.y()<ymax)){
					b=(double)Math.abs(p.x()-xmax);
					
				}
			}
			else if(p.y()<ymin){
				if(p.x()<=xmin){
					b=(double)Math.sqrt(Math.pow(xmin-p.x(),2)+Math.pow(ymin-p.y(),2));
				}
				
				else if((p.x()>xmin)&&(p.x()<xmax)){
					b=(double)Math.abs(p.y()-ymin);
				}
				else if(p.x()>=xmax){
					b=(double)Math.sqrt(Math.pow(p.x()-xmax,2)+Math.pow(ymin-p.y(),2));
				}
				
			}
			else if(p.x()<xmin){
				if(p.y()>=ymax){
					b=(double) Math.sqrt(Math.pow(xmin-p.x(),2)+Math.pow(p.y()-ymax,2));
				}
				else if(p.y()<=ymin){
					b=(double)Math.sqrt(Math.pow(xmin-p.x(),2)+Math.pow(ymin-p.y(),2));
				}
				else if((p.y()>ymin)&&(p.y()<ymax)){
					b=(double)Math.abs(p.x()-xmin);
					
				}
			}
			return b;
		}
		
	} // Euclidean distance from p
	//to closest point in rectangle
	public int squareDistanceTo(Point p){
		return (int)Math.pow(distanceTo(p), 2);
	} 
	public String toString(){
		return "[" + xmin + "," + xmax + "] x [" + ymin + "," + ymax + "]";
	}
	// string representation: [xmin, xmax] x [ymin, ymax]
}
