package events;

import org.newdawn.slick.state.StateBasedGame;

import utils.Camera;
import utils.Dialogue;

public class DialogueEvent extends Event{
	
	Dialogue dialogue;

	public DialogueEvent(Dialogue dialogue) {
		super(EventType.DIALOGUE);
		
		this.dialogue = dialogue;
	}

	@Override
	public void act(StateBasedGame sbg, Camera camera) {
		//do nothing
	}
	
	public Dialogue getDialogue(){
		return dialogue;
	}

}
