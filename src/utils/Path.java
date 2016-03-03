package utils;

import java.util.TreeSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import tiles.Tile;
import tiles.TileMap;

public class Path {

	TreeSet<Tile> path = new TreeSet<Tile>();
	SpriteSheet sprite;
	Image image;
	int spriteSize;
	int tileSize;

	public Path(TreeSet<Tile> path, int spriteSize, int tileSize){
		this.path = path;
		this.spriteSize = spriteSize;
		this.tileSize = tileSize;

		try {
			sprite = new SpriteSheet("Resources/PathArrow.png" , spriteSize, spriteSize);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g, TileMap map){

		image = sprite.getSprite(0, 3);

		for(Tile t : path){
			boolean left = false;
			boolean right = false;
			boolean top = false;
			boolean bot = false;
			
			for(Tile t2 : path){
				if((t2.getX() - t.getX() == -1) && (t2.getY() - t.getY() == 0)){
					left = true;
				}
				if((t2.getX() - t.getX() == 1) && (t2.getY() - t.getY() == 0)){
					right = true;
				}
				if((t2.getX() - t.getX() == 0) && (t2.getY() - t.getY() == -1)){
					top = true;
				}
				if((t2.getX() - t.getX() == 0) && (t2.getY() - t.getY() == 1)){
					bot = true;
				}
			}
			
			if(left){
				if(right){
					image = sprite.getSprite(2, 0);
				}
			}

			g.setColor(Color.white);
			image.draw(t.getX() * tileSize - (spriteSize - tileSize)/2, t.getY() * tileSize - (spriteSize - tileSize)/2);
		}
		System.out.println("========================");
	}
}
