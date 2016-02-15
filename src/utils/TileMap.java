package utils;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class TileMap {
	
	ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private int width, height;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		for(int i = 0; i < width; i++){
			tiles.add(new ArrayList<Tile>());
		}
	}
	
	public Tile get(int x, int y){
		return tiles.get(x).get(y);
	}
	
	public void set(Tile tile, int x, int y){
		tiles.get(x).add(y, tile);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < width; i++){
			ArrayList<Tile> list = tiles.get(i);
			for(int j = 0; j < list.size(); j++){
				list.get(j).draw(g, i, j);
			}
		}
	}
	
	public int tileSize(){
		return tiles.get(0).get(0).width;
	}
	
	public int minX(){
		return 0;
	}
	
	public int minY(){
		return 0;
	}
	
	public int maxX(){
		return width * tileSize();
	}
	
	public int maxY(){
		return height * tileSize();
	}
}