package entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import utils.Direction;
import utils.Inventory;
import utils.Item;
import utils.TileMap;
import entities.Entity;

/**
 * The player as it is represented on the screen
 */
public class PCCharacter extends Entity {

	Inventory inventory;
	boolean dead = false;
	boolean isWalking = false;
	Image image;
	Direction spriteDirection = Direction.NORTH;
	int speed = 1;
	boolean active = false;

	int xCoord, yCoord;

	/**
	 * Constructor
	 * 
	 * @param boundingBox - a Rectangle representing the borders of the Platform
	 * @param velocity - the initial velocity
	 */
	public PCCharacter(float size) {
		super(0, 0, size, size);
		inventory = new Inventory(new ArrayList<Item>());
	}

	@Override
	public void update(GameContainer gc, int delta, TileMap map) {
		if(active)
			handleInputs(gc, map);
		
		if(gc.getInput().isKeyDown(Input.KEY_LSHIFT)){
			x -= x % 2;
			y -= y % 2;
			speed = 2;
		}else{
			speed = 1;
		}

		if(isWalking){
			switch(direction){
			case EAST:
				if(x >= map.maxX() - map.tileSize()){
					x = map.maxX() - map.tileSize();
					isWalking = false;
				}
				else{
					x += speed;
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
					y -= speed;
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
					y += speed;
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
					x -= speed;
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

		if(!isWalking){
			if(input.isKeyPressed(Input.KEY_W)){
				spriteDirection = Direction.NORTH;
			}
			else if(input.isKeyPressed(Input.KEY_A)){
				spriteDirection = Direction.WEST;
			}
			else if(input.isKeyPressed(Input.KEY_S)){
				spriteDirection = Direction.SOUTH;
			}
			else if(input.isKeyPressed(Input.KEY_D)){
				spriteDirection = Direction.EAST;
			}
		}else{
			input.clearKeyPressedRecord();
		}

		if(input.isKeyDown(Input.KEY_W)){
			if(yCoord > 0){
				if(map.get(xCoord, yCoord-1).pathable()){
					if(!isWalking){
						spriteDirection = Direction.NORTH;
						direction = Direction.NORTH;
						isWalking = true;
					}
				}
			}
		}
		else if(input.isKeyDown(Input.KEY_A)){
			if(xCoord > 0){
				if(map.get(xCoord-1, yCoord).pathable()){
					if(!isWalking){
						spriteDirection = Direction.WEST;
						direction = Direction.WEST;
						isWalking = true;
					}
				}
			}
		}
		else if(input.isKeyDown(Input.KEY_S)){
			if(yCoord < map.getHeight()-1){
				if(map.get(xCoord, yCoord+1).pathable()){
					if(!isWalking){
						spriteDirection = Direction.SOUTH;
						direction = Direction.SOUTH;
						isWalking = true;
					}
				}
			}
		}
		else if(input.isKeyDown(Input.KEY_D)){
			if(xCoord < map.getWidth()-1){
				if(map.get(xCoord+1, yCoord).pathable()){
					if(!isWalking){
						spriteDirection = Direction.EAST;
						direction = Direction.EAST;
						isWalking = true;
					}
				}
			}
		}
	}

	public void draw(Graphics g){
		switch(spriteDirection){
		case NORTH:
			image = sprite.getSprite(0, 0);
			break;
		case EAST:
			image = sprite.getSprite(1, 0);
			break;
		case SOUTH:
			image = sprite.getSprite(2, 0);
			break;
		case WEST:
			image = sprite.getSprite(3, 0);
			break;
		}
		image.draw(x, y);
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
	
	public boolean isWalking(){
		return isWalking;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void setWalking(boolean value){
		isWalking = value;
	}
	
	public int getXCoord(){
		return xCoord;
	}
	
	public int getYCoord(){
		return yCoord;
	}
	
	public void setDirection(Direction d){
		direction = d;
		spriteDirection = d;
	}
	
	public void setXCoord(int x){
		xCoord = x;
		this.x = x * width;
	}
	
	public void setYCoord(int y){
		yCoord = y;
		this.y = y * width;
	}
	
	public boolean getActive(){
		return active;
	}
	
	public void setActive(boolean b){
		active = b;
	}
}
