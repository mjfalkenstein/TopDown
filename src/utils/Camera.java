package utils;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import entities.Entity;

public class Camera {

	int dX, dY;
	int levelWidth, levelHeight;
	Rectangle view;

	/**
	 * Constructor
	 * 
	 * @param gc - GameContainer
	 * @param levelWidth - the maximum width of the level in pixels
	 * @param levelHeight - the maximum height of the level in pixels
	 */
	public Camera(GameContainer gc, int levelWidth, int levelHeight) {
		dX = 0;
		dY = 0;
		view = new Rectangle(0, 0, gc.getWidth(), gc.getHeight());
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
	}

	/**
	 * Instructs the camera to follow the given entity
	 * 
	 * @param gc - GameContainer
	 * @param g - the Graphics context
	 * @param e - the Entity to follow
	 */
	public void update(GameContainer gc, Graphics g, Entity e) {
		view.setX((e.getX() - gc.getWidth()/2));
		view.setY((e.getY() - gc.getHeight()/2));
	}
	
	public int getX(){
		return (int) view.getX();
	}
	
	public int getY(){
		return (int) view.getY();
	}
	
}