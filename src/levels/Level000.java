package levels;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import driver.Driver;
import utils.Dialogue;
import utils.Level;
import utils.Region;
import utils.Tile;
import utils.TileEnum;
import entities.Entity;
import entities.Player;
import events.StateSwitchEvent;

/**
 * A simple test level designed to integrate all entities in one environment
 */
public class Level000 extends Level{

	Rectangle background;
	String message = "Welcome to test level 000! Press Space to advance this message, and move to the yellow area to return to test level 0";
	Dialogue dialogue;
	Region testDialogueRegion, testEventRegion;
	StateSwitchEvent testEvent;

	public Level000(GameContainer gc, Player player, int tileSize, int spriteSize, int levelWidth, int levelHeight, int enterX, int enterY) {
		super(gc, player, tileSize, spriteSize, levelWidth, levelHeight, enterX, enterY);

		background = new Rectangle(0, 0, levelWidth, levelHeight);
		testEvent = new StateSwitchEvent(Driver.LEVEL_0);
		testDialogueRegion = new Region(1, 1, 2, 3, tileSize, Color.green);
		testEventRegion = new Region(2, 2, 1, 1, tileSize, Color.yellow, testEvent);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.gc = gc;
		this.sbg = sbg;

		player.setSprite("/Resources/testPlayerSprite.png", tileSize, tileSize);

		world.add(player);
		world.add(testDialogueRegion);
		world.add(testEventRegion);

		Tile testTile = new Tile(TileEnum.TEST, tileSize, tileSize);
		Tile blankTile = new Tile(TileEnum.BLANK, tileSize, tileSize);

		for(int i = 0; i < levelWidth / tileSize; i++){
			for(int j = 0; j < levelHeight / tileSize; j++){
				map.set(testTile, i, j);
			}
		}

		for(int i = 0; i < map.getWidth(); i++){
			for(int j = 0; j < map.getHeight(); j++){
				if(i == 0 || j == 2){
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

		drawLevelEssentials(g);

		dialogue.draw(g);

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
		
		if(!testDialogueRegion.contains(player)){
			dialogue.hide();
		}
		if(testEventRegion.contains(player))testEventRegion.doEvent(sbg);

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
		if(testDialogueRegion.contains(player)){
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
		return Driver.LEVEL_00;
	}
}
