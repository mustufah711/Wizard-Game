import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
	
	private BufferedImage block;
	
	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		block = ss.grabImage(5, 2, 32, 32);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(block, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,32,32);
	}

}
