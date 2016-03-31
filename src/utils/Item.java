package utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class Item{
	
	protected Image image;
	protected int currentDurability;
	protected int maxDurability;	
	protected SpriteSheet sprite;
	protected int currentX, currentY;
	protected int x, y;
	
	public void move(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		image.draw(x, y);
	}
	
}