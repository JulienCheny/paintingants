package org.polytechtours.performance.tp.fourmispeintre2;

import java.awt.Color;
import java.awt.Graphics;

import org.polytechtours.performance.tp.fourmispeintre.CPainting;

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
	  
	  
    public static void convol(int x, int y, Color c, Graphics mGraphics, short [][][] field, final int pTaille) {
		switch(pTaille) {
			case 0: 	;break;
			case 1: convol(x, y, c, mGraphics, field, mMatriceConv9, mFactorConv9, 3);	break;
			case 2: convol(x, y, c, mGraphics, field, mMatriceConv25, mFactorConv25, 5);	break;
			case 3: convol(x, y, c, mGraphics, field, mMatriceConv49, mFactorConv49, 7);	break;
		}
	}
	  
	public static void convol(int x, int y, Color c, Graphics mGraphics, short [][][] field, final short [][] mConv, final short mFactorConv, final int pTaille) {
		int i, j, k, l, n, m, fieldHeight = field.length, fieldWidth = field[0].length;
		short red, green, blue;
		
		field[x][y][0] = (short)c.getRed();
		field[x][y][1] = (short)c.getGreen();
		field[x][y][2] = (short)c.getBlue();
		/*
		if(pTaille == 3)
			System.out.println("ptaille3");
		*/
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
	            red = (short) ( red / mFactorConv);
	            green = (short) (green / mFactorConv);
	            blue = (short) (blue / mFactorConv);
	            field[m][n][0] = red;
	            field[m][n][1] = green;
	            field[m][n][2] = blue;
	            
	            mGraphics.setColor(new Color((int)red, (int)green, (int)blue));
	            mGraphics.fillRect(m, n, 1, 1);
	        }
	    }
	}
	
	/*public static float[][] multiply(final int[][] matrix) {
		final int pTaille = matrix.length;
		float[][] result = {};
		switch(pTaille) {
		case 3: return multiply(matrix, mMatriceConv9, pTaille);
		case 5: return multiply(matrix, mMatriceConv25, pTaille);
		case 7: return multiply(matrix, mMatriceConv49, pTaille);
		}
		return result;
	}
	
	public static float[][] multiply(final int[][] matrix, final float[][] mConv, final int pTaille) {
		final int pTailleMid = (pTaille-1) / 2;
        final float[][] result = new float[pTaille][pTaille];
        float valUpLeft, valDownLeft,valUpRight,valDownRight;
        int i=0,j=0,k=0;
        for (i = 0; i <= pTaille; i ++) {
            for (j = 0; j <= pTaille; j ++) {
            	valUpLeft = 0F;
            	valDownLeft = 0F;
            	valUpRight = 0F;
            	valDownRight = 0F;

                for (k = 0; k < pTaille; k ++) {
                	valUpLeft += matrix[i][k] * mConv[k][j];
                    if(i!=pTailleMid)
                    {
                    	valDownLeft += matrix[pTaille-1-i][k] * mConv[k][j];
                    }
                    valUpRight += matrix[i][k] * mConv[k][pTaille-1-j];
                    if(j!=pTailleMid)
                    {
                    	valDownRight += matrix[pTaille-1-i][k] * mConv[k][pTaille-1-j];
                    }
                }
                result[i][j] = valUpLeft;
                if(i!=pTailleMid) {
                    result[pTaille - 1 - i][j] = valDownLeft;
                }
                result[i][pTaille - 1-j] = valUpRight;
                if(j!=pTailleMid) {
                    result[pTaille-1 - i][pTaille-1 - j] = valDownRight;
                }
            }
        }
        //for(int i=0; i< result.length;i++)
            //System.out.println(Arrays.toString(result[i]));
        return result;
    }*/
}
