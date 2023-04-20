package appPixel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ColorPixel {

	private List<int[]> xyList; 
	
	public ColorPixel() {
	    xyList = new ArrayList<int[]>();
	}
	 
	public void addPoint(int x, int y) {
		int new_x = pixelX(x);
		int new_y = pixelY(y);

		int[] xy = {new_x,new_y};
		if (xyList.indexOf(xy) == -1)
			xyList.add(xy);
	}
	
	private static int pixelX(int x) {
		 return x = ((x+1)/20)*20;
	}
	 
	private static int pixelY(int y) {
		 return y = ((y+1)/20)*20;
	}

	 
	// This PolyLine paints itself given the Graphics context
	public void draw(Graphics gfx) { // draw itself
	   for(int[]i : xyList) {
		   Color c = gfx.getColor();
		   gfx.fillRect(i[0], i[1], 20, 20);
		   gfx.setColor(Color.black);
		   gfx.drawRect(i[0], i[1], 20, 20);
		   gfx.setColor(c);
		   
	   }
	   xyList.clear();
	}
}
