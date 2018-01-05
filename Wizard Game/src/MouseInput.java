import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	private Camera camera;
	
	public MouseInput(Handler handler, Camera camera) {
		this.handler = handler;
		this.camera = camera;
	}
	//Mouse Listener 
	public void mousePressed(MouseEvent e) {
		int mx = (int)(e.getX()+camera.getX()); //Add camera coordinates to keep tracking of where bullets are being shot
		int my = (int)(e.getY()+camera.getY());
		
		for(int i =0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			if(tempObj.getID() == ID.Player) {
				//The 16 and 24 are there to make sure the bullet is pointing away from player
				handler.addObject(new Bullet(tempObj.getX()+16, tempObj.getY()+24, ID.Bullet, handler, mx, my));
			}
		}
	}
}
