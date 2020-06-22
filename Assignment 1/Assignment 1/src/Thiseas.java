import java.io.*;
import java.util.*;


public class Thiseas {
	public static void main( String args[] ){
		
		StringStackImpl stackTrace = new StringStackImpl();
		String path=args[0];
		int row;
		int column;
		int i;
		int j;
		int counter =0;
		int c = 0;
		boolean m = true ;
		
		File f = null;
		BufferedReader reader = null;
		String line;
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
			while((line ==null)&&(counter <10)){
				counter++;
				line = reader.readLine();
			}
			if(line != null){
				String st= line.replaceAll("\\s+","");
				row = Character.getNumericValue(st.charAt(0));
				column = Character.getNumericValue(st.charAt(1));
				line = reader.readLine();
				if(row<0) throw  new NegativeArraySizeException("Array size cannot be negative.");
				if(column<0) throw  new NegativeArraySizeException("Array size cannot be negative.");
				char labyrinth[][] = new char[row][column];
				st= line.replaceAll("\\s+","");
				i = Character.getNumericValue(st.charAt(0));
				j = Character.getNumericValue(st.charAt(1));
				labyrinth[i][j]= 'E';
				line = reader.readLine();
				while((line != null)&&(m==true)){
					 if(c>=row){
						 m=false;
						 System.out.println("There is more rows that it should be.Wrong!!! End of program!!!");
					 }
					 else{
						 st=line.replaceAll("\\s+","");
						 int x=0;
						 int leng = st.length();
						 if(leng!=column){
							 System.out.println("There is more columns that it should be. Wrong on program.End of program!  "); 
							 m= false;
						 }
						 else{
							 m=true;
						 
							while((x<column)&&(st.charAt(x)!='\u0000')&&(leng>0)){
								labyrinth[c][x]=st.charAt(x);
								x++;
							
							 
						 
							}
						}
						 
						 
					 }
					 c++;
					 line = reader.readLine();
					 
				}
				
				if(c<row){
					m=false;
					 System.out.println("There is less rows that it should be.Wrong!!! End of program!!!");
				}
				int s=0;
				
				if(m==true){
					if(labyrinth[i][j]!='E'){
						for(int k=0;k<row;k++){
							for(int l=0;l<column;l++){
								if(labyrinth[k][l]=='E'){
									s++;
									i=k;
									j=l;							
								}
								else{
									m=false;
								}
							}
						}
						if(m==false){
							System.out.println("There is not entry in your maze. Wrong  coordinates of entry");
						
						}
						else if ((m==true)&&(s==1)){
							System.out.println("Wrong coordinates of entry but there is an entry in your maze");
						}
						else if (s>1){
							System.out.println("There is more than one entry in your maze.You cannot proceed to run your program");
							m=false;
						}
				
					}
					
				}
				
				if(m==true){
					for(int k=0;k<row;k++){
						for(int l=0;l<column;l++){
							if((labyrinth[k][l]=='1')||(labyrinth[k][l]=='0')||(labyrinth[k][l]=='E'));
								
								
							
							else{
								
								System.out.println("Your maze's elements must be one or zero or one E.Some elements are not one or zero or E. Wrong maze!");
								System.out.println("Wrong in row:"+ k + " and column:"+l);
								m=false;
							}
						}
					}
				}
				
				
				
				
				
				
				if(m==true){
					String d =  String.valueOf(i)+","+String.valueOf(j);
					stackTrace.push(d);
					boolean exit = false;
				
					while((exit==false)&&(m==true)){
						if((i-1>=0)&&(labyrinth[i-1][j]=='0')){
							i=i-1;
							d=String.valueOf(i)+","+String.valueOf(j);
							stackTrace.push(d);
							labyrinth[i][j]= 'X';
							if((i==0)||(i==row-1)||(j==0)||(j==column-1)){
								exit = true;
								System.out.println("You find the exit of maze! You are free");
								stackTrace.printStack(System.out );
								System.out.println();
							}
						
			
						}
					
						else if((j-1>=0)&&(labyrinth[i][j-1]=='0')){
							j=j-1;
							d=String.valueOf(i)+","+String.valueOf(j);
							stackTrace.push(d);
							labyrinth[i][j]= 'X';
							if((i==0)||(i==row-1)||(j==0)||(j==column-1)){
								exit = true;
								System.out.println("You find the exit of maze! You are free");
								stackTrace.printStack(System.out );
								System.out.println();
							}
						}
						else if((i+1>=0)&&(i+1<row)&&(labyrinth[i+1][j]=='0')){
							i=i+1;
							d=String.valueOf(i)+","+String.valueOf(j);
							stackTrace.push(d);
							labyrinth[i][j]= 'X';
							if((i==0)||(i==row-1)||(j==0)||(j==column-1)){
								exit = true;
								System.out.println("You find the exit of maze! You are free");
								stackTrace.printStack(System.out );
								System.out.println();
							}
						}
						else if((labyrinth[i][j+1]=='0')&&(j+1<=column)){
							j=j+1;
							d=String.valueOf(i)+","+String.valueOf(j);
							stackTrace.push(d);
							labyrinth[i][j]= 'X';
							if((i==0)||(i==row-1)||(j==0)||(j==column-1)){
								exit = true;
								System.out.println("You find the exit of maze! You are free");
								stackTrace.printStack(System.out );
								System.out.println();
							}
						}
						else if(stackTrace.isEmpty()==false){
		
								stackTrace.pop();
								if(stackTrace.isEmpty()==false){
									d=stackTrace.peek();
									i=Character.getNumericValue(d.charAt(0));
									j = Character.getNumericValue(d.charAt(2));
								}
								else{
									exit = true ;
									System.out.println("There is not exit from your maze ");
								
								}
								
							
						}
						else if(stackTrace.isEmpty()==true){
							exit = true ;
							System.out.println("There is not exit from your maze ");
						}			
								
							
							
							
					}
				}
				else{
					System.out.println("You have wrongs in text file. I cannot run!!!");
				}
			}
			else{
				System.out.println("Empty file");
			}
				
			
		}catch (IOException e) {
			System.out.println(" Sudden end ");
			
		}
		try {
			reader.close();
		} catch (IOException e) {
			System.err.println("Error closing file.");
		}
		
		
				
			
			
			

	}
}
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

