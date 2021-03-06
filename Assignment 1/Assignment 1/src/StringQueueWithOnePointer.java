import java.io.PrintStream;
import java.util.NoSuchElementException;




public class StringQueueWithOnePointer implements StringQueue {
	private Node tail;
	private class Node {
			private	String item;
			private	Node next;
			Node(String item) {
				 this.item = item;
			   	 next = null; 
									}
	}
	
	
	public StringQueueWithOnePointer() { 
		tail = null; 
	}

	public boolean isEmpty() {
				return (tail == null); 
	}
	
	public void put(String item) {
		Node newNode = tail;
		tail = new Node(item);
		if (isEmpty()){
			tail.next =tail;
			
		}
		else{ 
			tail.next = newNode.next;
			newNode.next = tail; 
		}
	}
	
	public String get()throws NoSuchElementException {
		if(!isEmpty()){
		String str = tail.next.item;
		Node node = tail.next.next;
		tail.next = node;
		return str; 	
		}
		else{
			throw new NoSuchElementException("your Queue is empty you can't get items");
		}
	}	
		
		
	public String peek() throws NoSuchElementException{
		if(!isEmpty()){
			return tail.next.item;
		}
		else{
			throw new NoSuchElementException("your queue is empty");
		}
		
	} 
	public void printQueue(PrintStream stream){
		Node current = tail.next;
		if(!isEmpty()){
			while(current != tail){
				stream.println(current.item);
				stream.flush();
				current = current.next;
			}
			stream.println(tail.item);
			stream.flush();
			
		}
		else{
			stream.println("Empty queue");
			stream.flush();
		}
		
	}
    
	public int size(){
		int size = 0;
		Node currentNode = tail.next;
		while(currentNode != tail){
			currentNode = currentNode.next;
			size++ ;
		}
		return size+1;
		
	}
}
	


