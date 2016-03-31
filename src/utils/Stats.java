package utils;

public class Stats {	

	public int maxHealth,
			   strength,
			   intellect,
			   speed,
			   skill,
			   luck,
			   defense,
			   resistance;
	
	public Stats(int maxHealth, int strength, int intellect, int speed, int skill, int luck, int defense, int resistance) {
		this.maxHealth = maxHealth;
		this.strength = strength;
		this.intellect = intellect;
		this.speed = speed;
		this.skill = skill;
		this.luck = luck;
		this.defense = defense;
		this.resistance = resistance;
	}
}