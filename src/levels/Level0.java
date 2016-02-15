package levels;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import utils.Level;
import utils.Tile;
import utils.TileEnum;
import entities.Entity;
import entities.Player;

/**
 * A simple test level designed to integrate all entities in one environment
 */
public class Level0 extends Level{
	
	Rectangle background;
	
	public Level0(GameContainer gc, int tileSize, int levelWidth, int levelHeight) {
		super(gc, tileSize, levelWidth, levelHeight);
		
		background = new Rectangle(0, 0, levelWidth, levelHeight);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		
		player = new Player(5, 5, tileSize, tileSize);
		player.setSprite("/Resources/testPlayerSprite.png", 50, 50);
		
		world.add(player);
		
		Tile testTile = new Tile(TileEnum.TEST, 50, 50);
		
		for(int i = 0; i < levelHeight / 50; i++){
			for(int j = 0; j < levelWidth / 50; j++){
				map.set(testTile, i, j);
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fill(background);
		
		map.draw(g);
		
		for(Entity e : world){
			e.draw(g);
		}
		
		drawLevelEssentials(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//make sure to add cameraX/cameraY to account for moving camera
		int mouseX = gc.getInput().getMouseX() + camera.getX();
		int mouseY = gc.getInput().getMouseY() + camera.getY();

		//make sure to call collide before update
		if(!paused){

			for(Entity e : world){
				
				//leave these commented out if you don't want to die
				//otherwise, uncomment whichever one you want to test
				
				//follower.collide(e, gc);
				//turret1.collide(e, gc);
				//turret2.collide(e, gc);
				//spikesUp.collide(e, gc);
				//spikesDown.collide(e, gc);
				//spikesLeft.collide(e, gc);
				//spikesRight.collide(e, gc);
				//aLaser.collide(e, gc);
				//sLaser.collide(e, gc);
				
				e.update(gc, delta, map);
			}
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
	}

	@Override
	public int getID() {
		return 6;
	}
}
