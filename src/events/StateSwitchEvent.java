package events;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StateSwitchEvent extends Event {
	
	int newState;

	public StateSwitchEvent(int newState) {
		super(EventType.STATE_SWITCH);
		this.newState = newState;
	}

	@Override
	public void act(StateBasedGame sbg) {
		sbg.enterState(newState, new FadeOutTransition(), new FadeInTransition());
	}
}
