package entities;

import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

import utils.Dialogue;
import entities.Entity;
import tiles.TileMap;

/**
 * The player as it is represented on the screen
 */
public class Friendly extends Entity {

	float maxSpeed = 0.1f;
	float gravity = 0.03333333f;
	int time = 0;
	Dialogue dialogue;
	String text;
	int threshold = 1000;
	boolean wander = false;
	boolean canSpeak = false;
	boolean speaking = false;
	boolean onGround = false;
	Rectangle speakingBox;
	Color bg = Color.black;
	Color textColor = Color.lightGray;

	/**
	 * Constructor
	 * 
	 * @param boundingBox - a Rectangle representing the borders of the Platform
	 * @param velocity - the initial velocity
	 */
	public Friendly(int x, int y, float width, float height) {
		super(x, y, width, height);
	}

	/**
	 * Constructor
	 * 
	 * @param boundingBox - a Rectangle representing the borders of the Platform
	 * @param velocity - the initial velocity
	 * @param text - text for a speech bubble
	 */
	public Friendly(int x, int y, float width, float height, String text) {
		super(x, y, width, height);
		this.text = text;
		dialogue = new Dialogue(x, y, text, bg, textColor);
		speakingBox = new Rectangle(x - width, y, width * 3, height);
	}

	@Override
	public void update(GameContainer gc, int delta, TileMap map) {

		dialogue.move(x - dialogue.getWidth()/2 + width/2, y - dialogue.getHeight() - 10);

		if(!canSpeak){
			dialogue.hide();
			speaking = false;
		}

		handleInputs(gc);

		speakingBox.setLocation(x - width, y);
	}

	@Override
	public void move(float x, float y) {
		this.x = x;
		this.y = y;

		speakingBox.setLocation(x - width, y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fill(new Rectangle(x, y, width, height));
	}

	@Override
	public void reset(){
		move(startingX, startingY);
		dialogue.reset();
		onGround = false;
	}

	/**
	 * Called from update(), used to handle all inputs
	 * 
	 * @param gc - GameConatiner
	 */
	public void handleInputs(GameContainer gc){
		Input input = gc.getInput();

		if(input.isKeyPressed(Input.KEY_LSHIFT)){
			if(canSpeak && !dialogue.showing()){
				dialogue.show();
				speaking = true;
			}else if(dialogue.showing() && !gc.isPaused()){
				dialogue.advance();
			}
		}
	}
}
