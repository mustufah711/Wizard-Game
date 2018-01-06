import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crate extends GameObject{
	
	private BufferedImage crate;
	
	public Crate(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		crate = ss.grabImage(6, 2, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(crate, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
