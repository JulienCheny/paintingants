package org.polytechtours.performance.tp.fourmispeintre2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CConvolution {
	static private final short[][] mMatriceConv9 = {
								{1, 2, 1},
								{2, 4, 2},
								{1, 2, 1}
								};
	  static private final short[][] mMatriceConv25 = {
								{1, 1, 2, 1, 1},
								{1, 2, 3, 2, 1},
								{2, 3, 4, 3, 2},
								{1, 2, 3, 2, 1},
								{1, 1, 2, 1, 1}
								};
	  static private final short[][] mMatriceConv49 = {
								{1, 1, 2, 2, 2, 1, 1},
								{1, 2, 3, 4, 3, 2, 1},
								{2, 3, 4, 5, 4, 3, 2},
								{2, 4, 5, 8, 5, 4, 2},
								{2, 3, 4, 5, 4, 3, 2},
								{1, 2, 3, 4, 3, 2, 1},
								{1, 1, 2, 2, 2, 1, 1},
								};
	  static private final short mFactorConv9  = 16;
	  static private final short mFactorConv25 = 44;
	  static private final short mFactorConv49 = 128;
	  
    public static void convol(int x, int y, Color c, BufferedImage mBaseImage, short [][][] field, final int pTaille) {
    	field[x][y][0] = (short)c.getRed();
		field[x][y][1] = (short)c.getGreen();
		field[x][y][2] = (short)c.getBlue();
		mBaseImage.setRGB(x, y, c.getRGB());
        
		switch(pTaille) {
			case 0: 	;break;
			case 1: convol(x, y, c, mBaseImage, field, mMatriceConv9, mFactorConv9, 3);	break;
			case 2: convol(x, y, c, mBaseImage, field, mMatriceConv25, mFactorConv25, 5);	break;
			case 3: convol(x, y, c, mBaseImage, field, mMatriceConv49, mFactorConv49, 7);	break;
		}
	}
	  
	private static void convol(int x, int y, Color c, BufferedImage mBaseImage, short [][][] field, final short [][] mConv, final short mFactorConv, final int pTaille) {
		int i, j, k, l, n, m, fieldHeight = field.length, fieldWidth = field[0].length;
		int red, green, blue;
		
		for (i = 0; i < pTaille; i++) {
	    	for (j = 0; j < pTaille; j++) {
	    		red = green = blue = 0;
	            for (k = 0; k < pTaille; k++) {
	            	for (l = 0; l < pTaille; l++) {
	            		m = (x + i + k - 2 + fieldWidth) % fieldWidth;
	            		n = (y + j + l - 2 + fieldHeight) % fieldHeight;
	            		red += mConv[k][l] * field[m][n][0];
	            		green += mConv[k][l] * field[m][n][1];
	            		blue += mConv[k][l] * field[m][n][2];
	            		//System.out.println("cred = " + c.getRed() + ", cgreen = " + c.getGreen() + ", cblue = " + c.getBlue() + ", fred = " + field[m][n][0]);
	            		//System.out.println("r = " + red + ", g = " + green + ", b = " + blue);
	                }
	            }
	            m = (x + i - 1 + fieldWidth) % fieldWidth;
	            n = (y + j - 1 + fieldHeight) % fieldHeight;
	            red /= mFactorConv;
	            green /= mFactorConv;
	            blue /= mFactorConv;
	            //System.out.println(field[m][n][0] + " " + field[m][n][1] + " " + field[m][n][2]);
	            field[m][n][0] = (short) red;
	            field[m][n][1] = (short) green;
	            field[m][n][2] = (short) blue;
	            //System.out.println(red + " " + green + " " + blue);
	            mBaseImage.setRGB(m, n, ColorTools.getRGB(red, green, blue));
	        }
	    }
	}
}
