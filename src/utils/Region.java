package utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;

import entities.Entity;
import events.Event;
import tiles.Tile;
import tiles.TileMap;

public class Region extends Entity {
	
	int tileSize;
	Color color;
	RoundedRectangle highlight;
	Event event;
	
	public Region(int x, int y, float width, float height, int tileSize, Color color) {
		super(x, y, width, height);
		
		this.color = color;
		this.tileSize = tileSize;
		
		highlight = new RoundedRectangle(x * tileSize, y * tileSize, width * tileSize, height * tileSize, 5);
	}
	
	public Region(int x, int y, float width, float height, int tileSize, Color color, Event event) {
		super(x, y, width, height);
		
		this.color = color;
		this.tileSize = tileSize;
		this.event = event;
		
		highlight = new RoundedRectangle(x * tileSize, y * tileSize, width * tileSize, height * tileSize, 5);
	}

	@Override
	public void update(GameContainer gc, int delta, TileMap map) {
		//shouldn't need to do anything?
	}

	@Override
	public void move(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		g.setLineWidth(3);
		g.setColor(color);
		g.draw(highlight);
		Color c = new Color(color.r, color.g, color.b, 0.2f);
		g.setColor(c);
		g.fill(highlight);
	}
	
	public void doEvent(StateBasedGame sbg, Camera camera){
		if(event != null)
			event.act(sbg, camera);
	}
	
	public boolean contains(Entity e){
		return highlight.contains(e.getX() + tileSize/2, e.getY() + tileSize/2);
	}
	
	public boolean contains(float x, float y){
		return highlight.contains(x, y);
	}

	@Override
	public void reset() {
		//also shouldn't need to do anything
	}
	
	public Event getEvent(){
		if(event != null){
			return event;
		}
		return null;
	}

}
