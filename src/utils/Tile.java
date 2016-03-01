package utils;

import java.util.Random;
import java.util.TreeSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.RoundedRectangle;

public class Tile implements Comparable{
	
	SpriteSheet sprite;
	int tileSize, spriteSize;
	int currentX, currentY, x, y;
	TileEnum type;
	boolean pathable;
	Image image;
	int shift = 0;
	
	float movement, cover, protection, concealment, damage, flammability;
	
	public Tile(TileEnum type, int tileSize, int spriteSize){
		this.type = type;
		
		this.tileSize = tileSize;
		this.spriteSize = spriteSize;
		
		switch(type){
		case TEST:
			createTestTile();
			break;
		case BLANK:
			createBlankTile();
			break;
		case GRASS:
			createGrassTile();
			break;
		case DIRT:
			createDirtTile();
			break;
		case STONE_FLOOR:
			createStoneTile();
			break;
		case GRASSY_MUD:
			createGrassyMudTile();
			break;
//		case ACID:
//			break;
//		case ASH:
//			break;
//		case BRUSH:
//			break;
//		case CARPET:
//			break;
//		case CERAMIC_OBJECT_L:
//			break;
//		case CERAMIC_OBJECT_M:
//			break;
//		case CERAMIC_OBJECT_S:
//			break;
//		case DOOR:
//			break;
//		case FOG:
//			break;
//		case ICE:
//			break;
//		case LAVA:
//			break;
//		case METAL_OBJECT_L:
//			break;
//		case METAL_OBJECT_M:
//			break;
//		case METAL_OBJECT_S:
//			break;
//		case MIASMA:
//			break;
//		case MUD:
//			break;
//		case PAVED_FLOOR:
//			break;
//		case PERMAFROST:
//			break;
//		case ROOTS:
//			break;
		case SAND:
			createSandTile();
			break;
//		case SAPLING:
//			break;
//		case SMOKE:
//			break;
//		case SNOW:
//			break;
//		case STEAM:
//			break;
//		case STONE_OBJECT_L:
//			break;
//		case STONE_OBJECT_M:
//			break;
//		case STONE_OBJECT_S:
//			break;
//		case SWAMP:
//			break;
//		case TALL_GRASS:
//			break;
//		case TREE:
//			break;
//		case TUNDRA:
//			break;
//		case WATER:
//			break;
//		case WOOD_FLOOR:
//			break;
//		case WOOD_OBJECT_L:
//			break;
//		case WOOD_OBJECT_M:
//			break;
//		case WOOD_OBJECT_S:
//			break;
		default:
			break;
		}
	}
	
	public void setCoords(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	void createTestTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		currentX = 0;
		currentY = 0;
		pathable = true;
		
		try {
			sprite = new SpriteSheet("Resources/testTile.png" , 50, 50);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
	}
	
	void createBlankTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		currentX = 0;
		currentY = 0;
		pathable = false;
		
		try {
			sprite = new SpriteSheet("Resources/blankTile.png" , 50, 50);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
	}
	
	void createGrassTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		Random r = new Random();
		currentX = r.nextInt(3);
		currentY = 0;
		pathable = true;
		
		try {
			sprite = new SpriteSheet("Resources/grass.png" , spriteSize, spriteSize);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
		if(r.nextInt(2) == 1){
			image = image.getFlippedCopy(true, false);
		}
	}
	
	void createGrassyMudTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		Random r = new Random();
		currentX = r.nextInt(3);
		currentY = 0;
		pathable = true;
		
		try {
			sprite = new SpriteSheet("Resources/grassyMud.png" , spriteSize, spriteSize);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
		if(r.nextInt(2) == 1){
			image = image.getFlippedCopy(true, false);
		}
	}
	
	void createDirtTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		Random r = new Random();
		currentX = r.nextInt(3);
		currentY = 0;
		pathable = true;
		
		try {
			sprite = new SpriteSheet("Resources/dirt.png" , spriteSize, spriteSize);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
		if(r.nextInt(2) == 1){
			image = image.getFlippedCopy(true, false);
		}
	}
	
	void createSandTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		Random r = new Random();
		currentX = r.nextInt(3);
		currentY = 0;
		pathable = true;
		
		try {
			sprite = new SpriteSheet("Resources/sand.png" , spriteSize, spriteSize);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
		if(r.nextInt(2) == 1){
			image = image.getFlippedCopy(true, false);
		}
	}
	
	void createStoneTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		Random r = new Random();
		currentX = r.nextInt(3);
		currentY = 0;
		pathable = true;
		
		try {
			sprite = new SpriteSheet("Resources/stoneFloor.png" , spriteSize, spriteSize);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
		if(r.nextInt(2) == 1){
			image = image.getFlippedCopy(true, false);
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		image.draw(x * tileSize - (spriteSize - tileSize)/2, y * tileSize - (spriteSize - tileSize)/2);
	}
	
	public void highlight(Graphics g){
		Color c = Color.white;
		c.a = 0.5f;
		g.setColor(c);
		g.fill(new RoundedRectangle(x * tileSize, y * tileSize, tileSize, tileSize, 4));
		c.a = 1.0f;
		g.setColor(c);
	}
	
	public void setSprite(SpriteSheet newSprite){
		sprite = newSprite;
	}
	
	public void move(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean pathable(){
		return pathable;
	}
	
	public void clear(){
		shift = 0;
	}
	
	public TreeSet<Tile> getPossiblePath(TileMap map, int distance){
		TreeSet<Tile> output = new TreeSet<Tile>();
		
		if(pathable && (shift < distance)){
			shift = distance;
			output.add(this);
			output.addAll(map.get(x + 1, y).getPossiblePath(map, distance - 1));
			output.addAll(map.get(x - 1, y).getPossiblePath(map, distance - 1));
			output.addAll(map.get(x, y + 1).getPossiblePath(map, distance - 1));
			output.addAll(map.get(x, y - 1).getPossiblePath(map, distance - 1));
		}
		
		return output;
	}

	@Override
	public int compareTo(Object arg0) {
		Tile tile = (Tile) arg0;
		return x + y - tile.x + tile.y;
	}
	
	@Override
	public boolean equals(Object arg0){
		return compareTo(arg0) == 0;
	}
}
