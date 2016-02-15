package entities;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

import utils.Direction;
import utils.TileMap;

/**
 * This class represents anything that can act as a solid object on the screen
 */
public abstract class Entity {
	
	protected float x, y, width, height, startingX, startingY, startingMaxX, startingMaxY;
	protected SpriteSheet sprites;
	Direction direction;
	boolean visible = true;
	
	/**
	 * Constructor
	 * 
	 * @param boundingBox - a Rectangle representing the borders of the Entity
	 * @param velocity - the initial velocity
	 */
	public Entity(int x, int y, float width, float height) {
		super();
		this.x = x * width;
		this.y = y * width;
		startingX = x * width;
		startingY = y * height;
		this.width = width;
		this.height = height;
		direction = Direction.NORTH;
	}
	
	/**
	 * Called every frame, used to update position, state, etc
	 * 
	 * @param gc - GameContainer
	 * @param delta - time difference since the last frame
	 */
	public abstract void update(GameContainer gc, int delta, TileMap map);
	
	/**
	 * Instantly moves the entity to new coordinates (x, y)
	 * 
	 * @param x - new x coordinate
	 * @param y - new y coordinate
	 */
	public abstract void move(float x, float y);
	
	/**
	 * Rotates the entity by a given amount in degrees
	 * 
	 * @param degrees - rotation
	 */
	public void rotate(Direction d){
		this.direction = d;
	}
	
	/**
	 * Draws the entity to the given Graphics context
	 * 
	 * @param g - Graphics
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Resets the Entity to its original position, velocity, and states
	 */
	public abstract void reset();
	
	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void remove(){
		visible = false;
	}
	
	public void show(){
		visible = true;
	}

	public float getStartingX() {
		return startingX;
	}
	
	public float getStartingY(){
		return startingY;
	}
	
}
