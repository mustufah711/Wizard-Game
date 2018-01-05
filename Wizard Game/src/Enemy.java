import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject {
	
	private Handler handler;
	Random random = new Random();
	int choose = 0;
	int hp = 100;
	
	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		x += velocityX;
		y += velocityY;
		choose = random.nextInt(10);
		
		for(int i =0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(temp.getID()==ID.Block) {
				if(getBoundsBig().intersects(temp.getBounds())) {
					x += (velocityX*5)*-1; //Bounces wall at double the speed
					y += (velocityY*5)*-1; //-1 inverts velocity
					velocityX *= -1;
					velocityY *= -1;
				}
				else if(choose == 0) {
					velocityX = (random.nextInt(4- -4)+ -4); //Move in random directions
					velocityY = (random.nextInt(4- -4)+ -4);
				}
			}
			if(temp.getID() == ID.Bullet) {
				if(getBounds().intersects(temp.getBounds())) {
					hp -= 50;
				}
			}
		}
		if(hp <= 1) {
			handler.removeObject(this);
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	
	//So that if enemy collides with wall, then there will be more space rather then hitting wall directly
	public Rectangle getBoundsBig() {
		return new Rectangle(x-16, y-16, 64, 64);
	}
}