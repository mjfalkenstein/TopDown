package utils;

import java.util.Comparator;

import entities.PCCharacter;

public class CharacterComparator implements Comparator<PCCharacter>{
	@Override
	public int compare(PCCharacter o1, PCCharacter o2) {
		return o2.getStats().speed - o1.getStats().speed;
	}

}
