package levels;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import utils.Dialogue;
import utils.Level;
import utils.Region;
import utils.Tile;
import utils.TileEnum;
import entities.Entity;
import entities.Player;

/**
 * A simple test level designed to integrate all entities in one environment
 */
public class Level0 extends Level{

	Rectangle background;
	String message = "This is a test message, it should take a few different windows to display";
	Dialogue dialogue;
	Region testRegion;

	public Level0(GameContainer gc, int tileSize, int levelWidth, int levelHeight) {
		super(gc, tileSize, levelWidth, levelHeight);

		background = new Rectangle(0, 0, levelWidth, levelHeight);
		testRegion = new Region(1, 1, 2, 3, tileSize, Color.green);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.gc = gc;
		this.sbg = sbg;

		player = new Player(5, 5, tileSize, tileSize);
		player.setSprite("/Resources/testPlayerSprite.png", tileSize, tileSize);

		world.add(player);

		Tile testTile = new Tile(TileEnum.TEST, tileSize, tileSize);
		Tile blankTile = new Tile(TileEnum.BLANK, tileSize, tileSize);

		for(int i = 0; i < levelWidth / tileSize; i++){
			for(int j = 0; j < levelHeight / tileSize; j++){
				map.set(testTile, i, j);
			}
		}

		for(int i = 3; i <= 7; i++){
			for(int j = 3; j <= 7; j++){
				if(j == 3 || j == 7 || i == 3){
					map.set(blankTile, i, j);
				}
			}
		}

		dialogue = new Dialogue(0, 0, message, Color.white, Color.black);

		background = new Rectangle(-levelWidth * tileSize / 2, -levelHeight * tileSize / 2, levelWidth * tileSize, levelHeight * tileSize);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.translate(-(player.getX() - gc.getWidth()/2), -(player.getY() - gc.getHeight()/2));

		g.setColor(Color.gray);
		g.fill(background);

		map.draw(g);

		for(Entity e : world){
			e.draw(g);
		}

		dialogue.draw(g);

		testRegion.draw(g);

		drawLevelEssentials(g);

		g.translate((player.getX() - gc.getWidth()/2), (player.getY() - gc.getHeight()/2));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//make sure to add cameraX/cameraY to account for moving camera
		int mouseX = gc.getInput().getMouseX() + camera.getX();
		int mouseY = gc.getInput().getMouseY() + camera.getY();

		//make sure to call collide before update
		if(!paused){

			for(Entity e : world){

				e.update(gc, delta, map);
			}
		}

		dialogue.move(player.getX() - dialogue.getWidth(), player.getY() - dialogue.getHeight());
		
		if(!testRegion.contains(player)){
			dialogue.hide();
		}

		updateLevelEssentials(mouseX, mouseY, delta);
	}

	@Override
	public void mouseReleased(int button, int x, int y){
		//make sure to add cameraX/cameraY to account for moving camera
		x += camera.getX();
		y += camera.getY();

		handlePauseMenuInputs(button, x, y);
	}

	@Override
	public void keyReleased(int key, char c){
		if(key == Input.KEY_ESCAPE){
			if(!paused){
				pause();
				pauseMenu.show();
			}else{
				unpause();
				pauseMenu.hide();
			}
		}
		if(testRegion.contains(player)){
			if(key == Input.KEY_SPACE){
				if(!dialogue.showing()){
					dialogue.show();
				}else{
					dialogue.advance();
				}
			}
		}
	}

	@Override
	public int getID() {
		return 6;
	}
}
