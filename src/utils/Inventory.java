package utils;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Inventory {

	private ArrayList<Item> items;
	
	public Inventory(){
		items = new ArrayList<Item>();
	}
	
	public Inventory(ArrayList<Item> items){
		this.items = items;
	}
	
	public void draw(Graphics g){
		Rectangle r = new Rectangle(0, 0, 100, 100);
		g.setColor(Color.red);
		g.fill(r);
		for(Item i : items){
			i.draw(g);
		}
	}
	
	public void move(int x, int y){
		for(int i = 0; i < items.size(); i++){
			Item item = items.get(i);
			item.move(x, y);
		}
	}
	
	public void addItem(Item i){
		items.remove(i);
	}
	
	public void removeItem(Item i){
		items.remove(i);
	}
	
	public boolean contains(Item i){
		return items.contains(i);
	}
}