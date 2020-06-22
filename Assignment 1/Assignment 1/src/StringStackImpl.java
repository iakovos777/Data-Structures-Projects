import java.io.PrintStream;
import java.util.NoSuchElementException;


public class StringStackImpl implements StringStack {
	
	private Node head;
	private class Node {
		private String item;
		private Node next;
		private	Node(String item, Node next) {
			     this.item = item; 
			     this.next = next; 
			}
	}
	
	
	public StringStackImpl() { 
		head = null; 
	}
	
	public boolean isEmpty() { 
		return (head == null); 
	}
	public void push(String item) { 
		head = new Node(item, head);
	}
	public String pop() throws NoSuchElementException{ 
		if(!isEmpty()){
			String str = head.item; 
			Node t = head.next;
			head = t; 
			return str; 
		}
		else{
			throw new NoSuchElementException("your stack is empty you can't pop");
		}
	}
	 
	
	public String peek() throws NoSuchElementException{
		if(!isEmpty()){
			return head.item;
		}
		else{
			throw new NoSuchElementException("your stack is empty");
		}
	}
	
	public void printStack(PrintStream stream){
		Node current = head;
		if(!isEmpty()){
			while(current != null){
				stream.println(current.item);
				stream.flush();
				current = current.next;
			}
			
		}
		else{
			stream.println("Empty stack");
			stream.flush();
		}
		
		
	}

 	
	public int size(){
		int size = 0;
		Node currentNode = head;
		while(currentNode != null){
			currentNode = currentNode.next;
			size++ ;
		}
		return size;
		
	}

		
	
}

