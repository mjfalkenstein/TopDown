package utils;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Tile {
	
	SpriteSheet sprite;
	int width, height;
	int currentX, currentY, x, y;
	TileEnum type;
	boolean pathable;
	
	float movement, cover, protection, concealment, damage, flammability;
	
	public Tile(TileEnum type, int width, int height){
		this.type = type;
		
		this.width = width;
		this.height = height;
		
		switch(type){
		case TEST:
			createTestTile();
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
//		case SAND:
//			break;
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
	}
	
	void createGrassTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		currentX = 0;
		currentY = 0;
		pathable = true;
	}
	
	void createDirtTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		currentX = 0;
		currentY = 0;
		pathable = true;
	}
	
	void createStoneTile(){
		movement = 1.0f;
		cover = 0.0f;
		protection = 0.0f;
		concealment = 0.0f;
		damage = 0.0f;
		flammability = 0.0f;
		currentX = 0;
		currentY = 0;
		pathable = true;
	}
	
	public void draw(Graphics g, int i, int j){
		Image image = sprite.getSprite(currentX, currentY);
		image.draw(x + i * width, y + j * height);
	}
	
	public void setSprite(SpriteSheet newSprite){
		sprite = newSprite;
	}
	
	public void move(int x, int y){
		this.x = x;
		this.y = y;
	}
}
