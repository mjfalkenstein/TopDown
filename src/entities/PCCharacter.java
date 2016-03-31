package entities;

import java.util.ArrayList;
import java.util.TreeSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utils.Direction;
import utils.Inventory;
import utils.Item;
import utils.Stats;
import entities.Entity;
import tiles.Tile;
import tiles.TileEnum;
import tiles.TileMap;

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
	Image portrait;
	boolean hasMoved = false;
	public int maxTiles = 5;
	public static final int MAX_MOVES = 2;
	private int moves = 0;
	ArrayList<Direction> path;
	Stats stats;
	int currentHealth;

	int xCoord, yCoord;

	/**
	 * Constructor
	 * 
	 * @param boundingBox - a Rectangle representing the borders of the Platform
	 * @param velocity - the initial velocity
	 */
	public PCCharacter(float size, String portraitPath) {
		super(0, 0, size, size);
		inventory = new Inventory(new ArrayList<Item>());
		try {
			portrait = new Image(portraitPath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		path = new ArrayList<Direction>();
		
		stats = new Stats(1, 0, 0, 0, 0, 0, 0, 0);
		currentHealth = stats.maxHealth;
	}
	
	/**
	 * Constructor
	 * 
	 * @param boundingBox - a Rectangle representing the borders of the Platform
	 * @param velocity - the initial velocity
	 */
	public PCCharacter(float size, String portraitPath, Stats stats) {
		super(0, 0, size, size);
		inventory = new Inventory(new ArrayList<Item>());
		try {
			portrait = new Image(portraitPath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		path = new ArrayList<Direction>();
		
		this.stats = stats;
		currentHealth = stats.maxHealth;
	}

	@Override
	public void update(GameContainer gc, int delta, TileMap map) {

		if(gc.getInput().isKeyDown(Input.KEY_LSHIFT)){
			x -= x % 2;
			y -= y % 2;
			speed = 2;
		}else{
			speed = 1;
		}

		if(path.size() != 0 && !isWalking){
			direction = path.get(0);
			spriteDirection = direction;
			path.remove(0);
			isWalking = true;
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

	public void setPath(TreeSet<Tile> tiles){
		path.clear();
		Tile tile = new Tile(TileEnum.BLANK, 0, 0);
		tile.setCoords(xCoord, yCoord);
		
		if(!isWalking){

			for(Tile t : tiles){
				boolean left = false;
				boolean right = false;
				boolean top = false;
				boolean bot = false;
				boolean search = true;

				Tile next = t;

				for(Tile t2 : tiles){
					if(t.getDistance(tile) < t2.getDistance(tile) && t.getDistance(t2) == 1){
						next = t2;
						if(search && next.getX() < t.getX()){
							left = true;
							search = false;
						}
						if(search && next.getX() > t.getX()){
							right = true;
							search = false;
						}
						if(search && next.getY() < t.getY()){
							top = true;
							search = false;
						}
						if(search && next.getY() > t.getY()){
							bot = true;
							search = false;
						}
					}
				}

				if(left || right || top || bot){
					if(left){
						path.add(Direction.WEST);
					}
					if(right){
						path.add(Direction.EAST);
					}
					if(top){
						path.add(Direction.NORTH);
					}
					if(bot){
						path.add(Direction.SOUTH);
					}
				}
			}
		}
	}

	/**
	 * Called from update(), used to handle all inputs
	 * 
	 * @param gc - GameConatiner
	 */
	public void handleInputs(GameContainer gc, TileMap map){
		Input input = gc.getInput();
		
		path.clear();

		if(!isWalking && path.isEmpty()){
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

	public Image getPortrait(){
		return portrait;
	}
	public boolean hasMoved(){
		return hasMoved;
	}
	
	public int getMoves(){
		return moves;
	}
}