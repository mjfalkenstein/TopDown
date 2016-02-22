package events;

import org.newdawn.slick.state.StateBasedGame;

import utils.Camera;

public abstract class Event {
	
	private EventType type;
	
	public Event(EventType type){
		this.type = type;
	}
	
	public abstract void act(StateBasedGame sbg, Camera camera);
	
	public EventType getType(){
		return type;
	}
}
