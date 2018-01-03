import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Handler handler; //Do not initialize new instance since we need to access same linkedlist
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i =0; i<handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			if(tempObj.getID() == ID.Player) {
				if(key == KeyEvent.VK_W) { 
					handler.setUp(true);
					System.out.println("up pressed");
				}
				if(key == KeyEvent.VK_S) {
					handler.setDown(true);
					System.out.println("down pressed");
				}
				if(key == KeyEvent.VK_A) {
					handler.setLeft(true);
					System.out.println("left pressed");
				}
				if(key == KeyEvent.VK_D) {
					handler.setRight(true);
					System.out.println("right pressed");
				}
			}
		}
	}
	
	public void keyRelased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i =0; i<handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			if(tempObj.getID() == ID.Player) {
				System.out.println("hello");
				if(key == KeyEvent.VK_UP) handler.setUp(false);
				if(key == KeyEvent.VK_DOWN) handler.setDown(false);
				if(key == KeyEvent.VK_LEFT) handler.setLeft(false);
				if(key == KeyEvent.VK_RIGHT) handler.setRight(false);
			}
		}
	}
}
