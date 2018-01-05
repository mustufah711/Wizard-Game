import java.awt.Graphics;
import java.util.LinkedList;

/*
 * Deals with updating the whole game without needing to instantiate multiple GameObjects
 * Only create one handler instance and whole game can be updated
 */
public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>(); //List of all objects created
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	/*
	 * Always updates the objects that we created for the game and we can iterate through linkedlist
	 */
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.tick(); //tick every object in the list
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.render(g); //render every object in the list
		}
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	//Create new object on screen
	public void addObject(GameObject tempObj) {
		object.add(tempObj);
	}
	
	//Remove any object on screen
	public void removeObject(GameObject tempObj) {
		object.remove(tempObj);
	}
}
