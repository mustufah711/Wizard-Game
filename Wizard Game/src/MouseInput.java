import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	private Camera camera;
	Game game;
	private SpriteSheet ss;
	
	public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss) {
		this.handler = handler;
		this.camera = camera;
		this.game = game;
		this.ss = ss;
	}
	//Mouse Listener 
	public void mousePressed(MouseEvent e) {
		int mx = (int)(e.getX()+camera.getX()); //Add camera coordinates to keep tracking of where bullets are being shot
		int my = (int)(e.getY()+camera.getY());
		
		for(int i =0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			if(tempObj.getID() == ID.Player && game.ammo >=1) {
				//The 16 and 24 are there to make sure the bullet is pointing away from player
				handler.addObject(new Bullet(tempObj.getX()+16, tempObj.getY()+24, ID.Bullet, handler, mx, my,ss));
				game.ammo--;
			}
		}
	}
}
