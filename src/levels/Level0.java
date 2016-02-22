package levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import driver.Driver;
import utils.Dialogue;
import utils.Level;
import utils.Region;
import utils.Tile;
import utils.TileEnum;
import entities.PCCharacter;
import events.DialogueEvent;
import events.EnterBattleEvent;
import events.StateSwitchEvent;

public class Level0 extends Level{

	String message = "Welcome to test level 0! Press Space to advance this message, and move to the blue area to return to test level 00";
	Dialogue dialogue;
	Region testDialogueRegion, testEventRegion, testBattleRegion;
	StateSwitchEvent testStateSwitchEvent;
	EnterBattleEvent testBattleEvent;
	DialogueEvent testDialogueEvent;

	public Level0(GameContainer gc, ArrayList<PCCharacter> characters, int tileSize, int spriteSize, int levelWidth, int levelHeight, ArrayList<ArrayList<Integer>> enterCoords) {
		super(gc, characters, tileSize, spriteSize, levelWidth, levelHeight, enterCoords);

		dialogue = new Dialogue(0, 0, message, Color.white, Color.black);
		
		testStateSwitchEvent = new StateSwitchEvent(Driver.MAIN_MENU);
		testBattleEvent = new EnterBattleEvent(map);
		testDialogueEvent = new DialogueEvent(dialogue);
		
		testDialogueRegion = new Region(1, 1, 2, 3, tileSize, Color.green, testDialogueEvent);
		testEventRegion = new Region(2, 7, 1, 1, tileSize, Color.blue, testStateSwitchEvent);
		testBattleRegion = new Region(2, 10, 3, 3, tileSize, Color.red, testBattleEvent);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		
		regions.add(testDialogueRegion);
		regions.add(testEventRegion);
		regions.add(testBattleRegion);

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
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		drawLevelEssentials(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		updateLevelEssentials(delta, gc);
	}

	@Override
	public void mouseReleased(int button, int x, int y){
		handlePauseMenuInputs(button, x, y);
	}

	@Override
	public void keyReleased(int key, char c){
		handleKeyRelease(key, c);
	}

	@Override
	public int getID() {
		return 6;
	}
}