package utils;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class TileMap {
	
	ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	private int width, height, tileSize;
	
	private ArrayList<TileEnum> groundTiles = new ArrayList<TileEnum>();
	
	public TileMap(int width, int height, int tileSize){
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		
		for(int i = 0; i < width; i++){
			tiles.add(new ArrayList<Tile>());
		}
		
		Tile blankTile = new Tile(TileEnum.BLANK, tileSize, tileSize);
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				tiles.get(i).add(j, blankTile);
			}
		}
		
		groundTiles.add(TileEnum.GRASS);
		groundTiles.add(TileEnum.DIRT);
		groundTiles.add(TileEnum.GRASSY_MUD);
		groundTiles.add(TileEnum.SAND);
	}
	
	public Tile get(int x, int y){
		return tiles.get(x).get(y);
	}
	
	public void set(Tile tile, int x, int y){
		tiles.get(x).set(y, tile);
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
				TileEnum type = list.get(j).type;
				if(groundTiles.contains(type)){
					list.get(j).draw(g, i, j);
				}
			}
		}
		
		for(int i = 0; i < width; i++){
			ArrayList<Tile> list = tiles.get(i);
			for(int j = 0; j < list.size(); j++){
				TileEnum type = list.get(j).type;
				if(!groundTiles.contains(type)){
					list.get(j).draw(g, i, j);
				}
			}
		}
	}
	
	public void move(int x, int y){
		for(int i = 0; i < width; i++){
			ArrayList<Tile> list = tiles.get(i);
			for(int j = 0; j < list.size(); j++){
				list.get(j).move(i * tileSize, j * tileSize);
			}
		}
	}
	
	public int tileSize(){
		return tiles.get(0).get(0).tileSize;
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