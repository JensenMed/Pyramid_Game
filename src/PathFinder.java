import java.io.IOException;


/**
 * 
 * This class contains the code needed to compute a path from the entrance of the pyramid to all the treasure chambers
 * @author Jensen Medeiros
 *
 */

public class PathFinder {
	
	private Map pyramidMap;
	
	
	public PathFinder(String fileName) {
		
		try {
			this.pyramidMap = new Map(fileName);
		} catch (InvalidMapCharacterException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * @return best path for stack
	 */
	
	public DLStack path() {
		
		DLStack<Chamber> path = new DLStack<Chamber>();
		
		int N = pyramidMap.getNumTreasures();
		Chamber start = pyramidMap.getEntrance();
		
		path.push(start);
		
		start.markPushed();
		
		Chamber currChamber;
		Chamber neighbourChamber;
		
		int tChamber = 0;
		
		boolean test = true;
		
		while(test){
			if(path.isEmpty()) {
				//break loop if empty
				test = false;
				break;
			}else {
				
				// check the top of stack to compare to treasure
				currChamber = path.peek();
				if(currChamber.isTreasure()) {
					
					// adds to treasures counter
					tChamber ++;
				}
				if(currChamber.isTreasure() && tChamber == N) {
					
					// if treasure number is reached then break loop
					test = false;
					break;
				}
				
				// pass this into new method
				neighbourChamber = this.bestChamber(currChamber);
				
				if(neighbourChamber != null) {
					
					//push the neighbourChamber into stack
					path.push(neighbourChamber);
					
					
					// now mark it as pushed
					neighbourChamber.markPushed();
				}else {
					
					// pops top off stack 
					path.pop().markPushed();
				}
				
			}
			
		}return path;
		
		
		
		
	}
	
	
	
	/**
	 * 
	 * @returns value of pyramidMap
	 */
	
	public Map getMap() {
		return this.pyramidMap;
	}
	
	
	
	/**
	 * 
	 * @param currentChamber passed in to compare if dimmed
	 * @return true if dimmed else false
	 */
	public boolean isDim(Chamber currentChamber) {
		
		
			boolean isDimmed =false;
			
			//Checks if neighbours are lighted
			for(int i=0;i <= 5 ; i++) {
				if(currentChamber.getNeighbour(i).isLighted()) {
					isDimmed=true;
				}
			}
			
			// if all conditions for dim are met return true. Else return false
			if(currentChamber!=null && currentChamber.isSealed()==false && currentChamber.isLighted()==false && isDimmed) {
				return true;
			}else {
				return false;
			}
			
			
	}
	
	
	
	
	/**
	 * 
	 * @param currentChamber passed in to compare to best chamber to be reached 
	 * @return null if none else will return best chamber to visit next in accordance with rules
	 */
	
	public Chamber bestChamber(Chamber currentChamber) {
		
		Chamber neighbour = null;
		
		
		
		
		for(int i=0;i <= 5; i++){
			neighbour=currentChamber.getNeighbour(i);
			
			
			//checks if unmarked treasure
			if(neighbour.isMarked()==false && neighbour.isTreasure()) {
				return neighbour;
			}
		}
		
		for(int n = 0; n <=5; n++) {
			
			//checks if unmarked lighted chamber
			neighbour=currentChamber.getNeighbour(n);
			if(neighbour.isMarked()==false && neighbour.isLighted()) {
				return neighbour;
			}
		}
		
		
			// checks if unmarked dim chamber
		for(int a = 0; a <=5; a++) {
			neighbour=currentChamber.getNeighbour(a);
			if(neighbour.isMarked()==false && this.isDim(neighbour)) {
				return neighbour;
			}
		
		}
		
		//if none than return null
		return null;

		
			


	}	


}
