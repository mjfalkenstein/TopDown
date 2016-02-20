package levels;

import java.util.ArrayList;

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
import entities.PCCharacter;
import events.StateSwitchEvent;

/**
 * A simple test level designed to integrate all entities in one environment
 */
public class Level0 extends Level{

	Rectangle background;
	String message = "Welcome to test level 0! Press Space to advance this message, and move to the blue area to return to test level 00";
	Dialogue dialogue;
	Region testDialogueRegion, testEventRegion;
	StateSwitchEvent testEvent;

	public Level0(GameContainer gc, ArrayList<PCCharacter> characters, int tileSize, int spriteSize, int levelWidth, int levelHeight, ArrayList<ArrayList<Integer>> enterCoords) {
		super(gc, characters, tileSize, spriteSize, levelWidth, levelHeight, enterCoords);

		background = new Rectangle(0, 0, levelWidth, levelHeight);
		testEvent = new StateSwitchEvent(Driver.LEVEL_00);
		testDialogueRegion = new Region(1, 1, 2, 3, tileSize, Color.green);
		testEventRegion = new Region(2, 7, 1, 1, tileSize, Color.blue, testEvent);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		
		world.add(testDialogueRegion);
		world.add(testEventRegion);

		for(int i = 0; i < levelWidth / tileSize; i++){
			for(int j = 0; j < levelHeight / tileSize; j++){
				if(i > 10 && i < 14 && j > 7 && j < 11){
					map.set(new Tile(TileEnum.GRASSY_MUD, tileSize, spriteSize), i, j);
				}else if(i > 10 && i < 14 && j > 10 && j < 14){
					map.set(new Tile(TileEnum.DIRT, tileSize, spriteSize), i, j);
				}else if(i > 10 && i < 14 && j > 4 && j < 8){
					map.set(new Tile(TileEnum.SAND, tileSize, spriteSize), i, j);
				}else{
					map.set(new Tile(TileEnum.GRASS, tileSize, spriteSize), i, j);
				}
			}
		}

		for(int i = 3; i <= 7; i++){
			for(int j = 3; j <= 7; j++){
				if(j == 3 || j == 7 || i == 3){
					map.set(new Tile(TileEnum.BLANK, tileSize, tileSize), i, j);
				}
			}
		}

		dialogue = new Dialogue(0, 0, message, Color.white, Color.black);

		background = new Rectangle(-levelWidth * tileSize / 2, -levelHeight * tileSize / 2, levelWidth * tileSize, levelHeight * tileSize);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.translate(-(currentCharacter.getX() - gc.getWidth()/2), -(currentCharacter.getY() - gc.getHeight()/2));

		g.setColor(Color.gray);
		g.fill(background);

		map.draw(g);

		drawLevelEssentials(g);

		dialogue.draw(g);

		g.translate((currentCharacter.getX() - gc.getWidth()/2), (currentCharacter.getY() - gc.getHeight()/2));
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

		dialogue.move(currentCharacter.getX() - dialogue.getWidth(), currentCharacter.getY() - dialogue.getHeight());
		
		if(!testDialogueRegion.contains(currentCharacter)){
			dialogue.hide();
		}
		if(testEventRegion.contains(currentCharacter))testEventRegion.doEvent(sbg);

		updateLevelEssentials(mouseX, mouseY, delta, gc);
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
		if(testDialogueRegion.contains(currentCharacter)){
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
