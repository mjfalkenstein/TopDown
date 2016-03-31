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

	public void draw(Graphics g, Tile playerTile, TileMap map){

		image = sprite.getSprite(0, 3);
		
		int i = 0;
		
		for(Tile t : path){
			boolean left = false;
			boolean right = false;
			boolean top = false;
			boolean bot = false;
			
			i++;
			
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
			
			if(left && right){
				image = sprite.getSprite(2, 0);
			}
			if(left && top){
				image = sprite.getSprite(1, 1);
			}
			if(left && bot){
				image = sprite.getSprite(1, 0);
			}
			if(left && !bot && ! top && !right){
				image = sprite.getSprite(3, 2);
			}
			
			if(right && top){
				image = sprite.getSprite(0, 1);
			}
			if(right && bot){
				image = sprite.getSprite(0, 0);
			}
			if(right && !bot && !top && !left){
				image = sprite.getSprite(3, 1);
			}
			
			if(top && bot){
				image = sprite.getSprite(3, 0);
			}
			if(top && !bot && !left && !right){
				image = sprite.getSprite(2, 2);
			}
			if(bot && !top && !left && !right){
				image = sprite.getSprite(2, 1);
			}
			
			if(t.getX() == playerTile.getX() && t.getY() == playerTile.getY()){
				if(left && !bot && ! top && !right){
					image = sprite.getSprite(1, 2);
				}
				if(right && !bot && ! top && !left){
					image = sprite.getSprite(0, 2);
				}
				if(top && !bot && !right && !left){
					image = sprite.getSprite(0, 3);
				}
				if(bot && !top && !right && !left){
					image = sprite.getSprite(1, 3);
				}
			}

			g.setColor(Color.white);
			image.draw(t.getX() * tileSize - (spriteSize - tileSize)/2, t.getY() * tileSize - (spriteSize - tileSize)/2);
			g.setColor(Color.black);
			g.drawString(i + "", tileSize * (t.getX() ), tileSize * (t.getY()));
		}
	}
}
