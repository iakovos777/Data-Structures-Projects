import java.io.*;
import java.util.*;

public class TwoDTree {
	private int N, size ;
	private TreeNode head;
	public class TreeNode {
		private Point p;
		private TreeNode l, r;
		public TreeNode(Point p){
			this.p=p;
			l=null;
			r=null;
		}
	}
	public TwoDTree(){
		head = null;
		N=0;
		size = 0;
		
	} 
	public TwoDTree(int size){
		head = null;
		N=0;
		this.size = size;
		
	} 
	public boolean isEmpty(){
		if(N==0){
			return true;
		}
		else {
			return false;
		}
	} 
	
	public void setSize(int size){
		this.size=size;
	}
	
	public int size(){
		return size;
	} 
	public int HeightOfTree(){
		return (int) (Math.log(size()) / Math.log(2)+1);
	}
	public void insert(Point p){
		 int level = 1;
		 head = insertNode(head, p,level);
		 N++;
	}
	private TreeNode insertNode(TreeNode h, Point p,int level){
		if (h == null) return new TreeNode(p);
		
		if ((level%2==1)||(level==1)){
			if(p.x()>h.p.x()){
				level++;
				h.r = insertNode(h.r, p,level);
			}
			else if(p.x()<h.p.x()){
				level++;
				h.l = insertNode(h.l, p,level);
			}
		}
		else{
			if(p.y()>h.p.y()){
				level++;
				h.r = insertNode(h.r, p,level);
			}
			else{
				level++;
				h.l = insertNode(h.l, p,level);
			}
			
		}
		return h; 
		 
	}
	
	public boolean search(Point p){
		int level = 1;
		return searchNode(head,p,level);
	} 
	
	private boolean searchNode(TreeNode h, Point p,int level){
		if(h==null){ 
			return false;
		}
		if ((p.x()== h.p.x())&&(p.y()==h.p.y())){
			return true;
		}
		 
	   
		if ((level%2==1)||(level==1)){
			if(p.x()>h.p.x()){
				level++;
				return searchNode(h.r, p,level);
			}
			else{
				level++;
				return searchNode(h.l, p,level);
			}
		}
		else{
			if(p.y()>h.p.y()){
				level++;
				return searchNode(h.r, p,level);
			}
			else{
				level++;
				return searchNode(h.l, p, level);
			}
			
		}
		
	}
	
	public Point nearestNeighbor(Point p){
		if(isEmpty()){
			return null;
		}
		else{
			if(head!=null){
				int mindist=head.p.squareDistanceTo(p);
				int level = 1;
				TreeNode x =nearest(head,p,level,mindist,null,head);
				return x.p;
			}
			else{
				return null;
			}
		}
	} // point in the tree that is
	//closest to p
	//(null if tree is empty)
	public TreeNode nearest(TreeNode h, Point p, int level, int mindist, Rectangle previous, TreeNode minnode){
		Rectangle rectl = null , rectr =null;
		
		if(h==null){
			return minnode;
		}
		
		 
	    if(level==1&&level<HeightOfTree()){
	    	 if(h.l!=null){
				rectl= new Rectangle(0,0,h.p.x(),100);
				
			 }
			 if(h.l!=null){
				rectr = new Rectangle(h.p.x(),0,100,100);
				
			 }
			 level++;
			if(rectl!=null&&mindist>=rectl.squareDistanceTo(p)){
				if(mindist > h.l.p.squareDistanceTo(p)){
					mindist = h.l.p.squareDistanceTo(p);
					minnode = h.l;
					
				}
			    previous = rectl;
				minnode=nearest(h.l,p,level,mindist,previous,minnode);
			
				if(rectr!=null&&mindist>=rectr.squareDistanceTo(p)){
					if(mindist > h.r.p.squareDistanceTo(p)){
						mindist = h.r.p.squareDistanceTo(p);
						minnode = h.r;
						
					}
					
					previous = rectr;
					minnode=nearest(h.r,p,level,mindist,previous,minnode);
				}
				
			}
			else if(rectr!=null&&mindist>=rectr.squareDistanceTo(p)){
					if(mindist > h.r.p.squareDistanceTo(p)){
						mindist = h.r.p.squareDistanceTo(p);
						minnode = h.r;
						
					}
					
					previous = rectr;
					minnode=nearest(h.r,p,level,mindist,previous,minnode);
					if(rectl!=null&&mindist>=rectl.squareDistanceTo(p)){
						if(mindist > h.l.p.squareDistanceTo(p)){
							mindist = h.l.p.squareDistanceTo(p);
							minnode = h.l;
							
						}
						
					previous = rectl;
					minnode=nearest(h.l,p,level,mindist,previous,minnode);
				} 
			}
			else{
				return minnode;
			}
	    }
		else if (level%2==1&&level<HeightOfTree()){
			level++;
			 if(h.l!=null){
				rectl = new Rectangle(previous.xmin(),previous.ymin(),h.p.x(),previous.ymax());
				
			}
			 if(h.r!=null){	
				rectr = new Rectangle(h.p.x(),previous.ymin(),previous.xmax(),previous.ymax());
				
			}
			if(rectl!=null&&mindist>=rectl.squareDistanceTo(p)){
				if(mindist > h.l.p.squareDistanceTo(p)){
					mindist = h.l.p.squareDistanceTo(p);
					minnode = h.l;
					
				}
				
				previous = rectl;
				minnode=nearest(h.l,p,level,mindist,previous,minnode);
				if(rectr!=null&&mindist>=rectr.squareDistanceTo(p)){
					if(mindist > h.r.p.squareDistanceTo(p)){
						mindist = h.r.p.squareDistanceTo(p);
						minnode = h.r;
						
					}
					
				
					previous = rectr;
					minnode=nearest(h.r,p,level,mindist,previous,minnode);
				}
			
			}
		    else if(rectr!=null&&mindist>=rectr.squareDistanceTo(p)){
				if(mindist > h.r.p.squareDistanceTo(p)){
					mindist = h.r.p.squareDistanceTo(p);
					minnode = h.r;
					
				}
				
				previous = rectr;
				minnode=nearest(h.r,p,level,mindist,previous,minnode);
				if(rectl!=null&&mindist>=rectl.squareDistanceTo(p)){
					if(mindist > h.l.p.squareDistanceTo(p)){
						mindist = h.l.p.squareDistanceTo(p);
						minnode = h.l;
						
					}
					
					previous = rectl;
					minnode=nearest(h.l,p,level,mindist,previous,minnode);
			
			   }
			   
			
		    }
			else{
				return minnode;
			}
		}
		else if(level%2==0&&level<HeightOfTree()){
			level++;
			  if(h.l!=null){
				rectl = new Rectangle(previous.xmin(),previous.ymin(),previous.xmax(),h.p.y());
				
			}
			  if(h.r!=null){
				rectr = new Rectangle(previous.xmin(),h.p.y(),previous.xmax(),previous.ymax());
				
			}
			if(rectl!=null&&mindist>=rectl.squareDistanceTo(p)){
				if(mindist > h.l.p.squareDistanceTo(p)){
					mindist = h.l.p.squareDistanceTo(p);
					minnode = h.l;
					
				}
				
				previous = rectl;
				minnode=nearest(h.l,p,level,mindist,previous,minnode);
				if(rectr!=null&&mindist>=rectr.squareDistanceTo(p)){
					if(mindist > h.r.p.squareDistanceTo(p)){
						mindist = h.r.p.squareDistanceTo(p);
						minnode = h.r;
						
					}
				
					previous = rectr;
					minnode=nearest(h.r,p,level,mindist,previous,minnode);
				}
			
			}
		    else if(rectr!=null&&mindist>=rectr.squareDistanceTo(p)){
				if(mindist > h.r.p.squareDistanceTo(p)){
					mindist = h.r.p.squareDistanceTo(p);
					minnode = h.r;
					
				}
				
				previous = rectr;
				minnode=nearest(h.r,p,level,mindist,previous,minnode);
				if(rectl!=null&&mindist>=rectl.squareDistanceTo(p)){
					if(mindist > h.l.p.squareDistanceTo(p)){
						mindist = h.l.p.squareDistanceTo(p);
						minnode = h.l;
						
					}
					
					previous = rectl;
					minnode=nearest(h.l,p,level,mindist,previous,minnode);
				}
				else{
				return minnode;
			}
			
			}
		}
			else{
				return minnode;
			}
		
	    return minnode;
	
	}
	
	
	public List<Point> rangeSearch(Rectangle rect){
		List<Point> list = new List<>();
		if(isEmpty()){
			return null;
		}
		else{
			Rectangle root = new Rectangle(0,0,100,100);
			if(root.intersects(rect)){
				int level = 1;
				list.insertAtFront(head.p);
				return rangeS(head,rect,level,list,null);
			}
			else{
				return null;
			}
			 
		}
	} // Returns a list
	// with the Points that are contained in the rectangle
	
	public List<Point> rangeS(TreeNode h, Rectangle rect, int level, List<Point> list, Rectangle previous){
		if(h==null){
			return list;
		}
		
		Rectangle rectl=null, rectr = null; 
	    if(level==1&&level<HeightOfTree()){
	    	 if(h.l!=null){
				rectl = new Rectangle(0,0,h.p.x(),100);
			 }
			 if(h.r!=null){
				rectr = new Rectangle(h.p.x(),0,100,100);
			 }
			level++;
			if(rectl!=null&&rectl.intersects(rect)==true){
				previous = rectl;
				list.insertAtFront(h.l.p);
				rangeS(h.l,rect,level,list,previous);
				
				
				if(rectr!=null&&rectr.intersects(rect)==true){
					previous = rectr;
					list.insertAtFront(h.r.p);
					rangeS(h.r,rect,level,list,previous);
				}
			}
		    else if(rectr!=null&&rectr.intersects(rect)==true){
		    	previous = rectr;
		    	list.insertAtFront(h.r.p);
				rangeS(h.r,rect,level,list,previous);
				if(rectl!=null&&rectl.intersects(rect)==true){
					previous = rectl;
					list.insertAtFront(h.l.p);
					rangeS(h.l,rect,level,list,previous);
				}
			}
			
			
	    }
		else if (level%2==1&&level<HeightOfTree()){
			if(h.l!=null){	 
				rectl = new Rectangle(previous.xmin(),previous.ymin(),h.p.x(),previous.ymax());}
			if(h.r!=null){
				rectr = new Rectangle(h.p.x(),previous.ymin(),previous.xmax(),previous.ymax());
			}
			level++;
			if(rectl!=null&&rectl.intersects(rect)==true){
				previous = rectl;
				list.insertAtFront(h.l.p);
				rangeS(h.l,rect,level,list,previous);
				if(rectr!=null&&rectr.intersects(rect)==true){
					previous = rectr;
					list.insertAtFront(h.r.p);
					rangeS(h.r,rect,level,list,previous);
				}
			}
		    else if(rectr!=null&&rectr.intersects(rect)==true){
		    	previous = rectr;
		    	list.insertAtFront(h.r.p);
				rangeS(h.r,rect,level,list,previous);
				if(rectl!=null&&rectl.intersects(rect)==true){
					previous = rectl;
					list.insertAtFront(h.l.p);
					rangeS(h.l,rect,level,list,previous);
				}
			}
			
		}
		else if(level%2==0&&level<HeightOfTree()){
			
			if(h.l!=null){
				 rectl = new Rectangle(previous.xmin(),previous.ymin(),previous.xmax(),h.p.y());	
			}
			
			
			if(h.r!=null){
			rectr = new Rectangle(previous.xmin(),h.p.y(),previous.xmax(),previous.ymax());
			}
			level++;
			
			
			if(rectl!=null&&rectl.intersects(rect)==true){
				previous = rectl;
				list.insertAtFront(h.l.p);
				rangeS(h.l,rect,level,list,previous);
				if(rectr!=null&&rectr.intersects(rect)==true){
					previous = rectr;
					list.insertAtFront(h.r.p);
					rangeS(h.r,rect,level,list,previous);
				}
			
			}
		    else if(rectr!=null&&rectr.intersects(rect)==true){
		    	previous = rectr;
		    	list.insertAtFront(h.r.p);
				rangeS(h.r,rect,level,list,previous);
				if(rectl!=null&&rectl.intersects(rect)==true){
					previous = rectl;
					list.insertAtFront(h.l.p);
					rangeS(h.l,rect,level,list,previous);
				}
			
				
			
			}
		
		}
		
	    return list;
	}
	
	public void print(){
		
		if(head!=null){
			int level=1;
			System.out.println(head.p.toString()+level);
			printf(head,level);
		}
	}
	
	public void printf(TreeNode h, int level)
	{
		if ( isEmpty() )
		{
			System.out.printf( "Empty tree" );
			return;
		} // end if
		if(h!=null){
		System.out.printf( "The tree is: " );
		level++;
		if(h.r!=null){
			
			System.out.println(h.r.p.toString()+ level+"right");
		}
		if(h.l!=null){
			System.out.println(h.l.p.toString()+ level+"left");
		}
		if(h.r!=null){
			printf(h.r,level);
		}
		if(h.l!=null){
			printf(h.l,level);
		}
		System.out.println( "\n" );
		}
	} 
	
	
	
	
	
	public static void main( String args[] ){
			String path=args[0];
			File f = null;
			String answer;
	        BufferedReader reader = null;
	        String line = null;
	        boolean check=true ;
	        int counter = 0;
	        TwoDTree tree = new TwoDTree();
	        Scanner scan = new Scanner(System.in);
	        try {
	            f = new File(path);
	        } catch (NullPointerException e) {
	            System.err.println("File not found.");
	        }
	        try {
	        	reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

	            
	        } catch (FileNotFoundException e) {
	            System.err.println("Error opening file!");
	        }
	        try {
	        	line = reader.readLine();
	        	counter++;
	        	if(line!=null){
	        		String [] tokens = line.split("\\s+");
	        		int N = Integer.parseInt(tokens[0]);
	        		 tree.setSize(N);
	        		line = reader.readLine();
	        		counter++;
	        		
	        	}
				while( (line !=  null)&&(check==true) ){
	        
					String [] tokens = line.split("\\s+");
					int t = Integer.parseInt(tokens[0]);
					int m = Integer.parseInt(tokens[1]);
					if((t%1==0)&&(m%1==0)&&(t>=0)&&(t<=100)&&(m>=0)&&(m<=100)){
						Point p = new Point(t,m);
						
						if(tree.search(p)==false){
							tree.insert(p);
						}
						else{
							System.out.println("Error!!! Already there is this point! Invalid parametres in line:"+counter);
							check=false;
						}
					}
					else{
						System.out.println("Error!!! Invalid parametres. In line:"+counter);
						check=false;
					}
					
					counter++;
					line = reader.readLine();
				}
	        } catch (IOException e) {
	            System.out.println("Line  Sudden end.");
	        }
	        try {
	            reader.close();
	        } catch (IOException e) {
	            System.err.println("Error closing file.");
	        }
	      
	        while(check!=false){
	        	System.out.println("Press:");
	        	System.out.println("\n1. If you want to give a query rectangle  ");
				System.out.println("2. If you want to give a query point ");
				System.out.println("0. If you want to end program");
				System.out.print("> ");
				answer = scan.nextLine();
				if(answer.equals("1")){
					System.out.println("Give the dimensions of rectangle in form xmin,xmax,ymin,ymax");
					answer = scan.nextLine();
					String [] tokens = answer.split("\\s+");
					int xmin, ymin, ymax, xmax;
					if(tokens.length>2){
						xmin = Integer.parseInt(tokens[0]);
						xmax = Integer.parseInt(tokens[1]);
						ymin =Integer.parseInt(tokens[2]);
						ymax = Integer.parseInt(tokens[3]);
					}
					else{
						xmin = Integer.parseInt(tokens[0]);
						xmax = Integer.parseInt(tokens[1]);
						answer= scan.nextLine();
						String[] tokens2 = answer.split("\\s+");
						ymin =Integer.parseInt(tokens2[0]);
						ymax = Integer.parseInt(tokens2[1]);
					}
					Rectangle rect = new Rectangle(xmin,ymin,xmax,ymax);
					if(tree.rangeSearch(rect)==null){
						System.out.println("Null list");
					}
					else{
						tree.rangeSearch(rect).print();
					}
				}
				else if(answer.equals("2")){
					System.out.println("Give the dimensions of a point in form x,y");
					answer = scan.nextLine();
					String [] tokens = answer.split("\\s+");
					int x,y;
					x = Integer.parseInt(tokens[0]);
					y= Integer.parseInt(tokens[1]);
					Point p = new Point(x,y);
					if(tree.nearestNeighbor(p)==null){
						System.out.println("None result");
					}
					else{
					System.out.println(tree.nearestNeighbor(p).toString());}
				}
				
				
				else if(answer.equals("0")){
					check = false;
				}
	        }
	    
	}
}
