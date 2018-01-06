import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		/*
		 * Lets say we want column 1 row 1, then if you do 1*32-32 you get 0 which in memory is the first spot
		 * That is why we use the 32. Each block size is 32*32
		 */
		return image.getSubimage((col*32)-32, (row*32)-32, width, height);
	}
}
