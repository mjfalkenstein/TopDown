package utils;

import inGameMenus.InGameLoadMenu;
import inGameMenus.InGameOptionsMenu;

import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import driver.Driver;
import entities.Entity;
import entities.PCCharacter;

/**
 * A class that handles all level essentials
 * All other 'Levels' must inherit from this in order to work properly
 */
public abstract class Level extends BasicGameState{

	protected GameContainer gc;
	protected StateBasedGame sbg;
	protected boolean paused = false;
	protected PauseMenu pauseMenu;

	protected ArrayList<PCCharacter> characters;

	protected PCCharacter currentCharacter;

	protected Camera camera;
	protected int mouseX, mouseY;
	protected int levelWidth, levelHeight;
	int buttonWidth, buttonHeight, buttonXOffset, buttonYOffset, buttonXGap, buttonYGap;
	protected Notification warning;
	protected SimpleButton b1, b2;

	protected InGameLoadMenu loadMenu; 
	protected InGameOptionsMenu optionsMenu;

	boolean goToMainMenu = false;
	boolean newGame = false;
	boolean quit = false;

	protected ArrayList<Entity> world = new ArrayList<Entity>();
	protected ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();

	TrueTypeFont font;
	float fontSize = 12f;
	private Cursor emptyCursor, visibleCursor;

	protected TileMap map;
	int mapWidth, mapHeight;
	protected int tileSize, spriteSize;

	protected int currentCharacterIndex = 0;

	int counter = 50;

	ArrayList<ArrayList<Integer>> enterCoords = new ArrayList<ArrayList<Integer>>();

	/**
	 * Constructor
	 * 
	 * @param gc - the GameContainer
	 * @param p - the Player object for this level
	 * @param levelWidth - the width of the level
	 * @param levelHeight - the height of the level
	 */
	public Level(GameContainer gc, ArrayList<PCCharacter> characters, int tileSize, int spriteSize, int levelWidth, int levelHeight, ArrayList<ArrayList<Integer>> enterCoords){
		visibleCursor = Mouse.getNativeCursor();

		this.characters = characters;
		
		for(PCCharacter c : characters){
			c.setActive(false);
		}

		currentCharacter = characters.get(currentCharacterIndex);
		currentCharacter.setActive(true);

		//loading the font
		try{
			InputStream is = ResourceLoader.getResourceAsStream("Resources/HappyKiller.ttf");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, is);
			awtFont = awtFont.deriveFont(fontSize);
			font = new TrueTypeFont(awtFont, false);
		}catch(Exception e){
			e.printStackTrace();
		}

		this.enterCoords = enterCoords;

		this.tileSize = tileSize;
		this.spriteSize = spriteSize;

		this.levelWidth = levelWidth * tileSize;
		this.levelHeight = levelHeight * tileSize;
		Color background = Color.black;
		Color textColor = Color.lightGray;

		buttonWidth = 220;
		buttonHeight = 30;
		buttonXOffset = (int)(gc.getWidth() * 0.9f - 200);
		buttonYOffset = (int)(gc.getHeight() * 0.5f);
		buttonYGap = (int)(gc.getHeight() * 0.075f);
		buttonXGap = (int)(gc.getWidth() * 0.2);

		b1 = new SimpleButton(0, 0, buttonWidth, buttonHeight, "Confirm");
		b2 = new SimpleButton(0, 0, buttonWidth, buttonHeight, "Cancel");
		warning = new Notification(0, 0, gc.getWidth()/3, gc.getHeight()/3, background, textColor, b1, b2, buttonYGap, "Start New Game","Are you sure you want to start a new game? Unsaved progress will be lost.");

		camera = new Camera(gc, levelWidth * tileSize, levelHeight * tileSize);

		pauseMenu = new PauseMenu(gc, gc.getGraphics(), Color.black, Color.lightGray);

		loadMenu = new InGameLoadMenu(gc);

		optionsMenu = new InGameOptionsMenu(gc);

		map = new TileMap(levelWidth, levelHeight, tileSize);

		for(PCCharacter c : characters){
			world.add(c);
			c.setSprite("/Resources/testPlayerSprite.png", tileSize, tileSize);
		}
	}

	@Override
	public abstract void init(GameContainer gc, StateBasedGame sbg) throws SlickException;

	@Override
	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;

	@Override
	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;

	@Override
	public abstract void mouseReleased(int button, int x, int y);

	@Override
	public abstract void keyReleased(int key, char c);

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg){
		gc.resume();
		pauseMenu.hide();
		loadMenu.hide();
		try {
			emptyCursor = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
			Mouse.setNativeCursor(emptyCursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		paused = false;
		goToMainMenu = false;
		quit = false;
		newGame = false;

		currentCharacter = characters.get(currentCharacterIndex);
		
		for(PCCharacter c : characters){
			if(c.isWalking()){
				c.setWalking(false);
				switch(c.getDirection()){
				case EAST:
					//player.move((player.getXCoord() - 0) * tileSize, player.getYCoord() * tileSize);
					c.setDirection(Direction.WEST);
					break;
				case NORTH:
					//player.move(player.getXCoord() * tileSize, (player.getYCoord() + 1) * tileSize);
					c.setDirection(Direction.SOUTH);
					break;
				case SOUTH:
					//player.move(player.getXCoord() * tileSize, (player.getYCoord() - 0) * tileSize);
					c.setDirection(Direction.NORTH);
					break;
				case WEST:
					//player.move((player.getXCoord() + 1) * tileSize, player.getYCoord() * tileSize);
					c.setDirection(Direction.EAST);
					break;
				}
			}
		}

		System.out.printf("characters.size(): %d\n", characters.size());
		System.out.printf("enterCoords.size(): %d\n", enterCoords.size());

		for(PCCharacter c : characters){
			ArrayList<Integer> temp = enterCoords.get(characters.indexOf(c));
			c.setXCoord(temp.get(0));
			c.setYCoord(temp.get(1));
		}

		for(ArrayList<Integer> list : enterCoords){
			System.out.printf("Enter coord: %d, %d\n", list.get(0), list.get(1));
		}
		System.out.printf("currentCharacter: %d, %d\n", currentCharacter.getXCoord(), currentCharacter.getYCoord());
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg){
		try {
			Mouse.setNativeCursor(visibleCursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		for(PCCharacter c : characters){
			enterCoords.get(characters.indexOf(c)).set(0, c.getXCoord());
			enterCoords.get(characters.indexOf(c)).set(1, c.getYCoord());
		}
	}

	/**
	 * Called to pause the level
	 */
	protected void pause(){
		gc.pause();
		try {
			Mouse.setNativeCursor(visibleCursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		paused = true;
	}

	/**
	 * Called to unpause the level
	 */
	protected void unpause(){
		gc.resume();
		try {
			emptyCursor = new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null);
			Mouse.setNativeCursor(emptyCursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		pauseMenu.hide();
		loadMenu.hide();
		paused = false;
	}

	public int getWidth(){
		return levelWidth;
	}

	public int getHeight(){
		return levelHeight;
	}

	public ArrayList<Entity> getEntities(){
		return world;
	}

	public ArrayList<Checkpoint> getCheckpoints(){
		return checkpoints;
	}

	/**
	 * Handles all inputs for the pause menu
	 * 
	 * @param button - the mouse button that was pressed
	 * @param x - the x coordinate of the mouse
	 * @param y - the y coordinate of the mouse
	 */
	protected void handlePauseMenuInputs(int button, int x, int y){

		String pauseMenuSelection = pauseMenu.handleMouseInput(x, y);

		if(button == 0){
			if(paused && 
					!warning.isShowing() && 
					!loadMenu.isShowing() && 
					!optionsMenu.isShowing()){
				if(pauseMenuSelection == "continue"){
					unpause();
					pauseMenu.hide();
				}else if(pauseMenuSelection == "mainMenu"){
					warning.setHeader("Return to Main Menu");
					warning.setBody("Are you sure you want to return to the Main Menu? Unsaved progress will be lost.");
					warning.show();
					goToMainMenu = true;
				}else if(pauseMenuSelection == "newGame"){
					warning.setHeader("Begin a New Game");
					warning.setBody("Are you sure you want to Begin a New Game? Unsaved progress will be lost.");
					warning.show();
					newGame = true;
				}else if(pauseMenuSelection == "loadGame"){
					loadMenu.show();
				}else if(pauseMenuSelection == "options"){
					optionsMenu.show();
				}else if(pauseMenuSelection == "quit"){
					warning.setHeader("Quit to desktop");
					warning.setBody("Are you sure you want to quit to your desktop? Unsaved progress will be lost.");
					warning.show();
					quit = true;
				}
			}
			if(warning.isShowing()){
				if(b1.handleMouseInput(x, y)){
					if(goToMainMenu){
						gc.resume();
						pauseMenu.reset();
						warning.hide();
						for(Entity e : world){
							e.reset();
						}
						sbg.enterState(Driver.MAIN_MENU, new FadeOutTransition(), new FadeInTransition());
					}else if(newGame){
						gc.resume();
						pauseMenu.reset();
						warning.hide();
						for(Entity e : world){
							e.reset();
						}
						sbg.enterState(Driver.LEVEL_0, new FadeOutTransition(), new FadeInTransition());
					}else if(quit){
						gc.resume();
						pauseMenu.reset();
						warning.hide();
						for(Entity e : world){
							e.reset();
						}
						gc.exit();
					}
				}else if(b2.handleMouseInput(x, y)){
					warning.hide();
				}
			}
		}
		if(paused){
			loadMenu.handleMouseInput(button, x, y);
			optionsMenu.handleMouseInput(button, x, y);
		}
		pauseMenuSelection = "";
	}

	/**
	 * Handles much of the autonomous and essential part of the Level
	 * eg. player death, drawing the pause menu, etc.
	 * 
	 * @param delta - time since the last frame
	 */
	protected void updateLevelEssentials(int mouseX, int mouseY, int delta, GameContainer gc){

		if(gc.getInput().isKeyDown(Input.KEY_TAB) && counter >= 50){
			currentCharacterIndex++;
			currentCharacterIndex %= characters.size();

			for(PCCharacter c : characters){
				c.setActive(false);
			}

			counter = 0;
		}else{
			counter++;
		}

		currentCharacter = characters.get(currentCharacterIndex);
		currentCharacter.setActive(true);

		//if the player leaves the level bounds, it dies
		if(currentCharacter.getY() > levelHeight || currentCharacter.getX() > levelWidth){
			currentCharacter.kill();
		}

		//if player is dead, reload the most recent save
		if(currentCharacter.isDead()){

			File folder = new File("savedGames/");
			File[] listOfFiles = folder.listFiles();
			Arrays.sort(listOfFiles);

			if(listOfFiles.length > 0){
				if(listOfFiles[listOfFiles.length - 1].getName().contains(".sav")){
					SaverLoader.loadGame(gc, "savedGames/" + listOfFiles[listOfFiles.length - 1].getName(), sbg);
				}
			}
			currentCharacter.revive();
		}
		if(!paused){
			currentCharacter.getInventory().update(gc, delta);
		}

		if(	!warning.isShowing() && 
				!loadMenu.isShowing() && 
				!optionsMenu.isShowing()){

			pauseMenu.hover(mouseX, mouseY);
		}
		pauseMenu.move(camera.getX() + gc.getWidth()/2 - pauseMenu.getWidth()/2, camera.getY() + gc.getHeight()/2 - pauseMenu.getHeight());

		warning.move(camera.getX() + gc.getWidth()/2 - warning.getWidth()/2, camera.getY() + gc.getHeight()/2 - warning.getHeight()/2);
		b1.hover(mouseX, mouseY);
		b2.hover(mouseX, mouseY);

		for(Checkpoint c : checkpoints){
			c.collide(gc);
		}

		currentCharacter.getInventory().move(camera.getX(), camera.getY());

		loadMenu.update(camera.getX(), camera.getY(), mouseX, mouseY, gc);
		optionsMenu.update(camera.getX(), camera.getY(), mouseX, mouseY, gc);
	}

	/**
	 * Called to draw all of the "essentials" that every level needs
	 * 
	 * @param g - the Graphics context
	 */
	protected void drawLevelEssentials(Graphics g){
		camera.update(gc, g, currentCharacter);

		for(Entity e : world){
			e.draw(g);
		}

		for(Checkpoint c : checkpoints){
			c.draw(g);
		}

		if(currentCharacter.getInventory() != null){
			currentCharacter.getInventory().draw(g);
		}

		pauseMenu.draw(g, font);

		warning.draw(g, font);
		warning.move(gc.getWidth()/2 - warning.getWidth()/2, gc.getHeight()/2 - warning.getHeight()/2);
		b1.hover(mouseX, mouseY);
		b2.hover(mouseX, mouseY);

		loadMenu.draw(g, font);
		optionsMenu.draw(g, font);
	}

	/**
	 * Helper function that resets everything in the level to its original state
	 */
	protected void reset(){
		for(Entity e : world){
			e.reset();
		}
		for(Checkpoint c : checkpoints){
			c.reset();
		}
		pauseMenu.reset();
		optionsMenu.hide();
		warning.hide();
		unpause();
	}

	@Override
	public abstract int getID();

}
