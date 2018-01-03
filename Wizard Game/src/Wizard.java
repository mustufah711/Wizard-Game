import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wizard extends GameObject {
	
	Handler handler;
	
	public Wizard(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		x += velocityX;
		y += velocityY;
		
		collision();
		
		//Movement of player
		if(handler.isUp())
			velocityY=-5;
		else if(!handler.isDown())
			velocityY=0;
		if(handler.isDown())
			velocityY=5;
		else if(!handler.isUp())
			velocityY=0;
		if(handler.isRight())
			velocityX=5;
		else if(!handler.isLeft())
			velocityX=0;
		if(handler.isLeft())
			velocityX=-5;
		else if(!handler.isRight())
			velocityX=0;
	}
	
	/*
	 * This takes care of any collisions for the player so they can't go through walls
	 */
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			if(tempObj.getID()==ID.Block) {
				/*
				 * Checks to see if block object. If it is then we can use Rectangle to see if object
				 * intersects with the wizard and if it does then we resist collision
				 */
				if(getBounds().intersects(tempObj.getBounds())) {
					x+=velocityX * -1;
					y+= velocityY * -1;
				}
			}
		}
	}
	
	//Generates the color and size of the box neeed
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 32, 48);
	}
	
	//Creates dimension of the wizard
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,48);
	}

}
