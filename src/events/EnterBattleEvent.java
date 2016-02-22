package events;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

import entities.PCCharacter;
import utils.Camera;
import utils.Region;
import utils.TileMap;

public class EnterBattleEvent extends Event{

	private PCCharacter player;
	boolean active;
	Region region;
	TileMap map;

	public EnterBattleEvent(PCCharacter player, TileMap map){
		super(EventType.BATTLE);
		this.player = player;
		this.map = map;
		active = true;
	}

	public void act(StateBasedGame sbg, Camera camera) {
		if(active){
			
			active = false;
			
			float regX = player.getX();
			float regY = player.getY();
			
			region = new Region((int)regX / map.tileSize(), (int)regY / map.tileSize(), 5.0f, 5.0f, map.tileSize(), Color.yellow);
			
			camera.disable();
			
			System.out.println("Disabling camera");
			
			float camX = region.getX();
			float camY = region.getY();

			camera.move(camX, camY);
		}
		if(!region.contains(player) && !active){
			camera.enable();
		}
	}
}