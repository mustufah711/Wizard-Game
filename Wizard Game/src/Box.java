import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Box extends GameObject {

	public Box(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
	}

	@Override
	public void tick() {
		x += velocityX;
		y += velocityY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
}
