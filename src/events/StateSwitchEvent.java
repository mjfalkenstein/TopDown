package events;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Camera;

public class StateSwitchEvent extends Event {
	
	int newState;

	public StateSwitchEvent(int newState) {
		super(EventType.STATE_SWITCH);
		this.newState = newState;
	}

	@Override
	public void act(StateBasedGame sbg, Camera camera) {
		sbg.enterState(newState, new FadeOutTransition(), new FadeInTransition());
	}
}
