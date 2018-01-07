import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Wizard extends GameObject {
	
	Handler handler;
	Game game;
	private BufferedImage wizard;
	int n;
	Random rand = new Random();
	
	public Wizard(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		wizard = ss.grabImage(1, 1, 32, 48);
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
			
			if(tempObj.getID()==ID.Crate) {
				if(getBounds().intersects(tempObj.getBounds())) {
					n=rand.nextInt(2)+1;
					/*
					 * Cannot get crate if ammo already 100 and if ammo greater then 100, will set ammo to 100 auto
					 */
					if(n==1) {
						if(game.ammo==100) {
							x+=velocityX*-1;
							y+=velocityY*-1;
							return;
						}
						game.ammo+=10;
						//Make sure ammo does not exceed 100
						int n = game.ammo;
						int x = 0;
						if(game.ammo>100) {
							n-=100;
							x=game.ammo-n;
							game.ammo = x;
							handler.removeObject(tempObj);
						}
						else {
							handler.removeObject(tempObj);
						}
					}
					if(n==2){
						if(game.hp==100) {
							x+=velocityX*-1;
							y+=velocityY*-1;
							return;
						}
						game.hp+=10;
						int n = game.hp;
						int x = 0;
						if(game.hp >100) {
							n-=100;
							x=game.hp-n;
							game.hp=x;
							handler.removeObject(tempObj);
						}
						else {
							handler.removeObject(tempObj);
						}
						
					}
				}
			}
			
			if(tempObj.getID()==ID.Enemy) {
				if(getBounds().intersects(tempObj.getBounds())) {
					game.hp--;
					if(game.hp==0) {
						handler.removeObject(this);
					}
				}
			}
		}
	}
	
	//Generates the color and size of the box neeed
	public void render(Graphics g) {
		g.drawImage(wizard, x, y, null);
	}
	
	//Creates dimension of the wizard
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,48);
	}

}
