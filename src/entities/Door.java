package entities;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import utils.TileMap;

public class Door extends Entity {

	boolean open = false;

	public Door(int x, int y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void update(GameContainer gc, int delta, TileMap map) {
		//Do nothing, door state is handled in collide method
	}

	@Override
	public void move(float x, float y) {
		//Do nothing, the door shouldn't move
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.lightGray);
		g.fill(new Rectangle(x, y, width, height));
	}

	@Override
	public void reset() {
		open = false;
	}
	
	public void open(){
		open = true;
	}

	public boolean isOpen(){
		return open;
	}

}
