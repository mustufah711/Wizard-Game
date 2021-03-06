import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x;
	protected int y;
	protected float velocityX = 0; //Speed at which objects are moving per second
	protected float velocityY = 0;
	protected ID id;
	protected SpriteSheet ss;
	
	public GameObject(int x, int y, ID id, SpriteSheet ss) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(); //Every object needs to be refreshed after moving
	public abstract void render(Graphics g); //Every object needs to be drawn on screen
	public abstract Rectangle getBounds(); //Collision detection

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public ID getID() {
		return id;
	}
	
	public void setID(ID id) {
		this.id = id;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}
	
	
}
