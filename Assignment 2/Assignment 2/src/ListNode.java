/**
 * ListNode represents a signe-link list node
 * Each node contains an Object reference to data and a reference to the nextNode in the list. 
 */
class ListNode
{
	PrintJob data;
	ListNode nextNode;

	/**
	 * Constructor. It initializes data and sets next node to null 
	 * @param object a reference to node's data
	 */
	ListNode( PrintJob object )
	{
		this( object, null );
	} 
	
	/**
	 * constructor creates ListNode with passed data and next node
	 * @param object the reference to node's data
	 * @param node the next node in the list
	 */
	ListNode( PrintJob object, ListNode node )
	{
		data = object;
		nextNode = node;
	}

	/**
	 * Returns this node's data
	 * @return the reference to node's data
	 */
	PrintJob getPrintJob()
	
	{
		return data; 
	}

	/**
	 * Get reference to next node
	 * @return the next node
	 */
	ListNode getNext()
	{
		return nextNode; 
	} 
} 