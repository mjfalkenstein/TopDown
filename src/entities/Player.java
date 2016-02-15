package entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import utils.Direction;
import utils.Inventory;
import utils.Item;
import utils.TileMap;
import entities.Entity;

/**
 * The player as it is represented on the screen
 */
public class Player extends Entity {

	Inventory inventory;
	boolean dead = false;
	boolean isWalking = false;
	
	int xCoord, yCoord;

	/**
	 * Constructor
	 * 
	 * @param boundingBox - a Rectangle representing the borders of the Platform
	 * @param velocity - the initial velocity
	 */
	public Player(int x, int y, float width, float height) {
		super(x, y, width, height);
		inventory = new Inventory(new ArrayList<Item>());
	}

	@Override
	public void update(GameContainer gc, int delta, TileMap map) {
		handleInputs(gc, map);

		if(isWalking){
			switch(direction){
			case EAST:
				if(x >= map.maxX() - map.tileSize()){
					x = map.maxX() - map.tileSize();
					isWalking = false;
				}
				else{
					x += 1;
					if(x % map.tileSize() == 0){
						isWalking = false;
					}
				}
				break;
			case NORTH:
				if(y <= 0){
					y = 0;
					isWalking = false;
				}
				else{
					y -= 1;
					if(y % map.tileSize() == 0){
						isWalking = false;
					}
				}
				break;
			case SOUTH:
				if(y >= map.maxY() - map.tileSize()){
					y = map.maxY() - map.tileSize();
					isWalking = false;
				}
				else{
					y += 1;
					if(y % map.tileSize() == 0){
						isWalking = false;
					}
				}
				break;
			case WEST:
				if(x <= 0){
					x = 0;
					isWalking = false;
				}
				else{
					x -= 1;
					if(x % map.tileSize() == 0){
						isWalking = false;
					}
				}
				break;
			}
		}
		
		xCoord = (int) (x / map.tileSize());
		yCoord = (int) (y / map.tileSize());
	}

	@Override
	public void move(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fill(new Rectangle(x, y, width, height));
	}

	@Override
	public void reset(){
		move(startingX, startingY);
		if(inventory != null){
			inventory.reset();
		}
	}

	/**
	 * Called from update(), used to handle all inputs
	 * 
	 * @param gc - GameConatiner
	 */
	public void handleInputs(GameContainer gc, TileMap map){
		Input input = gc.getInput();

		if(input.isKeyDown(Input.KEY_W)){
			if(!isWalking){
				direction = Direction.NORTH;
				isWalking = true;
			}
		}
		else if(input.isKeyDown(Input.KEY_A)){
			if(!isWalking){
				direction = Direction.WEST;
				isWalking = true;
			}
		}
		else if(input.isKeyDown(Input.KEY_S)){
			if(!isWalking){
				direction = Direction.SOUTH;
				isWalking = true;
			}
		}
		else if(input.isKeyDown(Input.KEY_D)){
			if(!isWalking){
				direction = Direction.EAST;
				isWalking = true;
			}
		}
	}

	/**
	 * Used to signify that the player has been killed
	 */
	public void kill(){
		dead = true;
	}

	public void revive(){
		dead = false;
	}

	public boolean isDead(){
		return dead;
	}

	public void addItem(Item item) {
		if(inventory == null){
			inventory = new Inventory(new ArrayList<Item>());
		}
		inventory.addItem(item);
	}

	public void removeItem(Item item){
		inventory.removeItem(item);
	}

	public Inventory getInventory(){
		return inventory;
	}

	public boolean has(Item item){
		return inventory.contains(item);
	}
}
