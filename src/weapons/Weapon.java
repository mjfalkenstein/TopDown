package weapons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import utils.Item;

public class Weapon extends Item{

	WeaponEnum type;
	
	public int damage;
	public int hitPenalty;
	
	public Weapon(WeaponEnum type){
		this.type = type;
		
		switch(type){
		case AXE:
			break;
		case BOW:
			break;
		case EARTH:
			break;
		case FIREBALL:
			break;
		case HEAL:
			break;
		case ICE_LANCE:
			break;
		case JAVELIN:
			break;
		case LANCE:
			break;
		case LIGHTNING:
			break;
		case PISTOL:
			break;
		case POLEARM:
			break;
		case RIFLE:
			break;
		case SHADOW:
			break;
		case SHOTGUN:
			break;
		case SWORD:
			createSword();
			break;
		}
	}
	
	private void createSword(){
		
		maxDurability = 40;
		currentDurability = maxDurability;
		damage = 6;
		hitPenalty = 0;
		
		try {
			sprite = new SpriteSheet("Resources/sword.png" , 10, 10);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		image = sprite.getSprite(currentX, currentY);
	}
}
