package events;

import org.newdawn.slick.state.StateBasedGame;

import tiles.TileMap;
import utils.Camera;
import utils.Region;

public class EnterBattleEvent extends Event{
	boolean active;
	Region region;
	TileMap map;

	public EnterBattleEvent(TileMap map){
		super(EventType.BATTLE);
		this.map = map;
		active = true;
	}

	public void act(StateBasedGame sbg, Camera camera) {
		if(active){
			active = false;
			camera.disable();
		}
		if(!active){
			camera.enable();
		}
	}
}