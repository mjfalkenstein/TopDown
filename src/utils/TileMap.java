package utils;

import java.util.ArrayList;

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
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void draw(){
		for(int i = 0; i < width; i++){
			ArrayList<Tile> list = tiles.get(i);
			for(int j = 0; j < height; j++){
				Tile tile = list.get(j);
				tile.draw(i, j);
			}
		}
	}
}