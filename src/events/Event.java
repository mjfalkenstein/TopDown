package events;

import org.newdawn.slick.state.StateBasedGame;

public abstract class Event {
	
	public EventType type;
	
	public Event(EventType type){
		this.type = type;
	}
	
	public abstract void act(StateBasedGame sbg);
}
