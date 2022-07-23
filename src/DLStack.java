


/**
 * 
 * This class represents an extended Stack ADT using a doubly linked list
 * @author Jensen Medeiros
 * @param <T> implements parent class DLStack
 */
public class DLStack<T> implements DLStackADT<T> {

    private DoubleLinkedNode<T> top;
    private int numItems;

    /**
     * Constructor for DLStack
     * initializes variables
     * 
     */
    public DLStack() {
        top = null;
        numItems = 0;
    }

    /**
     * This method adds an item to the top of the stack
     * @param dataItem is the item to be added to top of stack
     */ 
    @Override
    public void push(T dataItem) {
    	
    	
    	
        if (top == null) {
        	
        	// makes new linked list and adds param as top
            top = new DoubleLinkedNode<T>(dataItem);
            
            
        } else {
        	
            DoubleLinkedNode<T> testNode = new DoubleLinkedNode<T>(dataItem);
            
            
            testNode.setPrevious(top);// top <--testNode 
            
            top.setNext(testNode); // top <--> testNode
            
            top = testNode; // sets top to test node
            
            testNode.setNext(null); // top <--> testNode --|
        }
        
        numItems++;
    }

    /**
     * Removes and returns the data item at the top of the stack 
     * @return the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() throws EmptyStackException {
    	
    	
        if (top == null) {
        	
        	//if top is null than the stack is empty therefore throws exception 
            throw new EmptyStackException("Stack is empty");
            
        } else {
        	
            T poppedData = top.getElement(); // sets top to poppedData
            
            if (top.getPrevious() == null) {
            	
            	
                top = null; // Sets the top of the stack to null if the top of the stack is the only node in the stack
            } else {
                top = top.getPrevious(); // Sets the top of the stack to the previous node of the top of the stack
                top.setNext(null); // Sets the pointer of the new top of the stack to null
            }
            numItems--;
            return poppedData;
        }
    }

    
    
    /**
     * @return the top of the stack without removing it
     */
    @Override
    public T peek() throws EmptyStackException {
    	
        if (numItems == 0) {
        	
        	//if the count is == 0 than the stack must be empty
            throw new EmptyStackException("Stack is empty");
        }
        //else return the top element data
        return top.getElement();
    }

    /**
     * : Removes and returns the k-th data item from the top of the stack
     * @param index of element to be returned
     * @return the element at the specified index
     * @throws InvalidItemException if k <= 0 or k is greater than the numItems(size of stack)
     */
    @Override
    public T pop(int k) throws InvalidItemException {
    	
        T itemPopped;
        
        if (k > numItems || k <= 0) {
        	
            throw new InvalidItemException("Invalid value");
        } else if (k == 1) {
        	
        	// if the param k is == 1 than its the first item. Than pop the stack using pop method
            itemPopped = pop();
            
            
        } else {
            DoubleLinkedNode<T> currentNode = top;
            
            for (int i = 1; i < k - 1; i++) { 
            	
            	
                currentNode = currentNode.getPrevious(); 
            }
            //we now need to fix the pointers
            itemPopped = currentNode.getPrevious().getElement(); 
            if (currentNode.getPrevious().getPrevious() != null) { 
                currentNode.getPrevious().getPrevious().setNext(currentNode); // previous --> currNode
                
                
                
                currentNode.setPrevious(currentNode.getPrevious().getPrevious()); // currentNode --> Node@index.getNext()
            } else {
            	
                currentNode.setPrevious(null);//currentNode --> null
            }
            
            
        }
        numItems--;
        return itemPopped;
    }
    
    

    /**
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }
    
    

    /**
     * @returns the number of elements in the stack
     */
    @Override
    public int size() {
    	return numItems;
    }
    
    

    /**
     * @return top of the stack
     */
    @Override
    public DoubleLinkedNode<T> getTop() {
        return top;
    }

    /**
     * @return a string represnetation of stack
     */
    public String toString() {
    	
        String finalRes = "[";
        
     
        DoubleLinkedNode<T> currentNode = top;
        while (currentNode != null) {
        	
        	
        	//iterate through linked until null
        	finalRes += currentNode.getElement();
        	currentNode = currentNode.getPrevious();
        	
            if (currentNode != null) {
            	
            	
            	finalRes += " ";
            }
        }
        
        //completes in form of “[data1 data2 … datan]”
        finalRes += "]";
        return finalRes;
    }
}