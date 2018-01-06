import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private BufferedImage level = null;
	private Camera camera;
	int ammo = 100;
	private BufferedImage sprite_sheet = null;
	private SpriteSheet ss;
	private BufferedImage floor = null;
	public int hp = 100;
	
	public Game() {
		new Window(1000,563,"Wizard Game", this);
		start();
		handler = new Handler();
		camera = new Camera(0,0);
		this.addKeyListener(new KeyInput(handler));
	
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/random.png");
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		ss = new SpriteSheet(sprite_sheet);
		floor = ss.grabImage(4, 2, 32, 32);
		this.addMouseListener(new MouseInput(handler, camera, this,ss));
		loadLevel(level);
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *Allows multiple threads to run the game at 60fps and constantly update and refresh game
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis()-timer > 1000) {
				timer+=1000;
				frames=0;
			}
		}
		stop();
	}
	
	//Updates everything in the game
	public void tick() {
		for(int i =0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getID()== ID.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		handler.tick();
	}
	
	//Renders everything like images and game itself
	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); //Read upon this, learn it
		if(bs==null) {
			this.createBufferStrategy(3); //Creates 3 arguments, pre-loading frames
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D)g;
		
		for(int x = 0; x < 30*72; x+=32) {
			for(int y = 0; y < 30*72; y+=32) {
				g.drawImage(floor, x, y, null);
			}
		}
		
		g2.translate(-camera.getX(), -camera.getY());
		
		//This section is where we can draw anything we want now and it will show on screen
		/////////////////
		
		handler.render(g); //This has to go to the end since you want all objects created on top of background
		g2.translate(camera.getX(), camera.getY());
		/////////////////
		
		g.setColor(Color.gray);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.green);
		g.fillRect(5, 5, hp*2, 32);
		g.setColor(Color.black);
		g.drawRect(5, 5, 200, 32);
		
		g.setColor(Color.white);
		g.drawString("Ammo: " + ammo, 5, 50);
		
		Graphics g1 = bs.getDrawGraphics();
		Graphics2D g3 = (Graphics2D)g1;
		Font font = new Font("TimesRoman",1,37);
		if(hp==0) {
			g1.setFont(font);
			g1.setColor(Color.red);
			g1.drawString("Game Over", 200,200);
			g1.drawString("Try Again!", 200, 250);
		}
		
		g.dispose();
		bs.show();
	}
	
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for(int x = 0; x < w; x++) {
			for(int y = 0; y< h; y++) {
				int pixel = image.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==255) {
					handler.addObject(new Block(x*32, y*32, ID.Block,ss));
				}
				if(blue==255 && green==0) {
					handler.addObject(new Wizard(x*32, y*32, ID.Player, handler, this,ss));
				}
				if(green==255 && blue==0) {
					handler.addObject(new Enemy(x*32, y*32, ID.Enemy, handler,ss));
				}
				if(blue==255 && green==255) {
					handler.addObject(new Crate(x*32,y*32, ID.Crate,ss));
				}
			}
		}
	}
	
	public static void main(String [] args) {
		new Game();
	}
}
