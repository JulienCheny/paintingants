package org.polytechtours.performance.tp.fourmispeintre2;

public class ColorTools {
	public static int getRGB(int red, int green, int blue) {
        return ((255 & 0xFF) << 24)|((red & 0xFF) << 16)|((green & 0xFF) << 8)|((blue & 0xFF) << 0);
    }
	
	public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }
	
	public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }
	
	public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }
}
