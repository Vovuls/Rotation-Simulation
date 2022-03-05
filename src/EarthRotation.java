import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EarthRotation {
	public static BufferedImage rotate(BufferedImage img, int degreeOfEarth) {

		// Getting Dimensions of image
		int width = img.getWidth();
		int height = img.getHeight();

		// Creating a new buffered image
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

		// creating Graphics in buffered image
		Graphics2D g2 = newImage.createGraphics();

		// Rotating image by degrees using toradians() method and setting new dimension
		g2.rotate(Math.toRadians(degreeOfEarth), width / 2, height / 2);
		g2.drawImage(img, null, 0, 0);

		// Return rotated buffer image
		return newImage;
	}
}
