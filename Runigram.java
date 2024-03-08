// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		// //// Hide / change / add to the testing code below, as needed.
		
		// // Tests the reading and printing of an image:	
		Color[][]tinypic  = read("tinypic.ppm");
		// print(tinypic);

		// // Creates an image which will be the result of various 
		// // image processing operations:
		// Color[][] imageOut;

		// // Tests the horizontal flipping of an image:
		// imageOut = flippedHorizontally(tinypic);
		// System.out.println();
		// print(imageOut);
		
		// //// Write here whatever code you need in order to test your work.
		// imageOut = grayScaled(tinypic);
		// System.out.println();
		// print(scaled(imageOut, 8, 8));


		display(scaled(tinypic, 8, 8));
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				image[i][j] = new Color(in.readInt(), in.readInt(), in.readInt());
			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				print(image[i][j]);
			}
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		int rows = image.length;
		int cols = image[0].length;
		Color[][] flippedhorzColors = new Color[rows][cols]; 
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				flippedhorzColors[i][j] = image[i][cols - j - 1];// rewrites a columns reversed
			}
		}
		return flippedhorzColors;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		int rows = image.length;
		int cols = image[0].length;
		Color[][] flippedVertColors = new Color[rows][cols];
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				flippedVertColors[i][j] = image[rows - i - 1][j];  // rewrites a row flipped
			}
		}
		return flippedVertColors;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();
		double luminanceValue = ((0.299 * r) + (0.587 * g) + (0.114 * b));
		int luminanceInt = (int)luminanceValue; // gets the int needed
		Color luminanceColor = new Color(luminanceInt, luminanceInt, luminanceInt);
		return luminanceColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		int rows = image.length;
		int cols = image[0].length;
		Color[][] greyColors = new Color[rows][cols];
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				greyColors[i][j] = luminance(image[i][j]);  // rewrites the photo but in gray
			}
		}
		return greyColors;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code // 
		long rows = image.length; // the previous photos rows
		long cols = image[0].length; // the preהious photos columns
		Color[][] scaledImage = new Color[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int newWidth = (int)(i * ((double)cols / width));
				int newHeight = (int)(j * ((double)rows / height));

				scaledImage[i][j] = image[newHeight][newWidth];
			}
		}
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int r1 = c1.getRed();
		int g1 = c1.getGreen();
		int b1 = c1.getBlue();
		int r2 = c2.getRed();
		int g2 = c2.getGreen();
		int b2 = c2.getBlue();

		double newRedD = Math.round(alpha * r1 + (1 - alpha) * r2);// new red combination as a double
		double newGreenD = Math.round(alpha * g1 + (1 - alpha) * g2);// new green combination as a double
		double newBlueD = Math.round(alpha * b1 + (1 - alpha) * b2);// new blue combination as a double

		int newRed = (int)newRedD;
		int newGreen = (int)newGreenD;
		int newBlue = (int)newBlueD;


		Color blendedColor = new Color(newRed, newGreen, newBlue);// the new combined color
		return blendedColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		int rows = image1.length;
		int cols = image1[0].length;

		Color[][] blendedColors = new Color[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) { 
				blendedColors[i][j] = blend(image1[i][j], image2[i][j], alpha);// retrieves pixel from image 1 and 2 - ask daniel question about this
			}
		}	
		return blendedColors;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		int sourceRows = source.length;
		int sourceCols = source[0].length;
		double alpha = 0;

		if (sourceRows != target.length || sourceCols != target[0].length){ // what is it not ok to check arrays directly
			target = scaled(target, sourceRows, sourceCols); // scales the picture to 
		}

		for( int i = 0; i <= n; i++){
			alpha = (double)(n - i)/n; // calculates the new alpha
			Color [][] blendedImage = blend(source, target, alpha); // blends the new image - how does it work if it returns void?
			print(blendedImage);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

