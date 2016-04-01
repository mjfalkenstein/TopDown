package entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import tiles.TileMap;
import utils.Direction;
import utils.Inventory;
import utils.Stats;
import weapons.Weapon;

public class Character extends Entity{
	
	protected Inventory inventory;
	protected boolean dead = false;
	protected boolean isWalking = false;
	protected Image image;
	protected Direction spriteDirection = Direction.NORTH;
	protected int speed = 1;
	protected boolean active = false;
	protected Image portrait;
	protected boolean hasMoved = false;
	private int maxTiles = 5;
	protected static final int MAX_MOVES = 2;
	protected int moves = 0;
	protected ArrayList<Direction> path;
	protected Stats stats;
	protected int currentHealth;
	protected int turnStatus = 0;
	protected Weapon equipped;

	public Character(int x, int y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(GameContainer gc, int delta, TileMap map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public Weapon getEquipped(){
		return equipped;
	}
	
	public Stats getStats(){
		return stats;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getMaxTiles() {
		return maxTiles;
	}

	public void setMaxTiles(int maxTiles) {
		this.maxTiles = maxTiles;
	}

	public void damage(int i) {
		currentHealth -= i;
		
	}

}
