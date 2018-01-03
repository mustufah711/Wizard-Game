import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	/*
	 * Always updates the objects that we created for the game and we can iterate through linkedlist
	 */
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.render(g);
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
