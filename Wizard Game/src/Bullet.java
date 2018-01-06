import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	private Handler handler;
	
	public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		velocityX = (mx-x)/10; //Total travel time, speed bullet moves
		velocityY = (my-y)/10;
	}

	public void tick() {
		x += velocityX;
		y += velocityY;
		
		for(int i =0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ID.Block) {
				if(getBounds().intersects(temp.getBounds())) {
					handler.removeObject(this); //"this" pertains to the actual instance of bullet to make sure it doesnt go through walls
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,8,8);
	}

}
